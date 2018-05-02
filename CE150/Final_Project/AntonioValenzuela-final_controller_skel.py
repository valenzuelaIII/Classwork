# Author: Antonio Valenzuela
# CMPE 150 final project
# Date: March 11, 2018
# file: finalcontroller_skel.py
# description: implements topology of a small company's network as specified in the lab manual
# Lab Demo completed: Thursday March 15th, 2018 during the 10am-12pm session

from pox.core import core
import pox.openflow.libopenflow_01 as of
import pox.lib.packet as pkt

log = core.getLogger()

class Final (object):
    """
    A Firewall object is created for each switch that connects.
    A Connection object for that switch is passed to the __init__ function.
    """
    def __init__ (self, connection):
        # Keep track of the connection to the switch so that we can
        # send it messages!
        self.connection = connection

        # This binds our PacketIn event listener
        connection.addListeners(self)

    def do_final (self, packet, packet_in, port_on_switch, switch_id):

        msg = of.ofp_flow_mod()
        
        def drop ():
            msg.match = of.ofp_match.from_packet(packet)
            msg.idle_timeout = 30
            msg.hard_timeout = 30
            msg.data = packet_in
            self.connection.send(msg)
            
        def acceptip (x):
            msg.match = of.ofp_match.from_packet(packet)
            msg.idle_timeout = 30
            msg.hard_timeout = 30
            msg.data = packet_in
            msg.actions.append(of.ofp_action_output(port = x))
            self.connection.send(msg)
            
        def acceptflood ():
            msg.match = of.ofp_match.from_packet(packet)
            msg.idle_timeout = 30
            msg.hard_timeout = 30
            msg.data = packet_in
            msg.actions.append(of.ofp_action_output(port = of.OFPP_FLOOD))
            self.connection.send(msg)
                
        
        ipv4pkt = packet.find('ipv4')

        if ipv4pkt is not None: #if source IP is part of network:
            
                    #Switch 1
            if switch_id == 1: 
                if ipv4pkt.dstip == "10.1.1.10":
                    acceptip(8)
                else:
                    acceptip(1)

                    #Switch 2
            if switch_id == 2: 
                if ipv4pkt.dstip == "10.2.2.20":
                    acceptip(8)
                else:
                    acceptip(1)

                    #Switch 3
            if switch_id == 3: 
                if ipv4pkt.dstip == "10.3.3.30":
                    acceptip(8)
                else:
                    acceptip(1)

                    #Switch 4
            if switch_id == 4: 
                if port_on_switch == 4:
                    icmppkt = packet.find('icmp')
                    if icmppkt is not None: #is packet is an icmp packet
                        drop()
                    else: #else is if IP
                        if ipv4pkt.dstip == "10.1.1.10":
                            acceptip(1)
                        elif ipv4pkt.dstip == "10.2.2.20":
                            acceptip(2)
                        elif ipv4pkt.dstip == "10.3.3.30":
                            acceptip(3)
                        else:
                            drop()
                else: #update table based on 
                    if ipv4pkt.dstip == "10.1.1.10":
                        acceptip(1)
                    elif ipv4pkt.dstip == "10.2.2.20":
                        acceptip(2)
                    elif ipv4pkt.dstip == "10.3.3.30":
                        acceptip(3)
                    elif ipv4pkt.dstip == "10.5.5.50":
                        acceptip(5)
                    elif ipv4pkt.dstip == "123.45.67.89":
                        acceptip(4)
                    else:
                        drop()
                    
                #Switch 5
            if switch_id == 5: 
                if ipv4pkt.dstip == "10.5.5.50":
                    acceptip(8)
                else:
                    acceptip(1)
                    
        else: #if not an IP packet
        
                #Switch 1
            nonip = packet
            if switch_id == 1:
                if nonip.dst == "10.1.1.10":
                    acceptip(8)
                else:
                    acceptflood()
        
                #Switch 2
            if switch_id == 2:
                if nonip.dst == "10.2.2.20":
                    acceptip(8)
                else:
                    acceptflood()
       
                #Switch 3
            if switch_id == 3:
                if nonip.dst == "10.3.3.30":
                    acceptip(8)
                else:
                    acceptflood()
        
                #Switch 4
            if switch_id == 4:
                if nonip.dst == "123.45.67.89":
                        acceptip(4)
                else:
                    acceptflood()

                #Switch 5 
            if switch_id == 5:
                if nonip.dst == "10.5.5.50":
                    acceptip(8)
                else:
                    acceptflood()
            
                
    def _handle_PacketIn (self, event):
        """
        Handles packet in messages from the switch.
        """
        packet = event.parsed # This is the parsed packet data.
        if not packet.parsed:
            log.warning("Ignoring incomplete packet")
            return
        packet_in = event.ofp # The actual ofp_packet_in message.
        self.do_final(packet, packet_in, event.port, event.dpid)
        
def launch ():
    """
    Starts the component
    """
    def start_switch (event):
        log.debug("Controlling %s" % (event.connection,))
        Final(event.connection)
    core.openflow.addListenerByName("ConnectionUp", start_switch)