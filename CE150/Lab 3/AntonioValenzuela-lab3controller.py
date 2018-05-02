# lab3controller.py
# Author: Antonio Valenzuela <anlvalen@ucsc.edu>
# CMPE 150L - Monday March 5, 2018 - Spring Quarter 2018
#
# Based on of_tutorial by James McCauley

from pox.core import core
import pox.openflow.libopenflow_01 as of
import pox.lib.packet as pkt

log = core.getLogger()

class Firewall (object):
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

  def do_firewall (self, packet, packet_in):
    # The code in here will be executed for every packet.
    
    def drop ():
        msg.match = of.ofp_match.from_packet(packet)
        msg.in_port = packet_in
        self.connection.send(msg)
        
    def accept (x, y):
        msg.match = of.ofp_match.from_packet(packet)
        msg.idle_timeout = x
        msg.hard_timeout = y
        msg.data = packet_in
        msg.actions.append(of.ofp_action_output(port = of.OFPP_ALL))
        self.connection.send(msg)
    
    msg = of.ofp_flow_mod() #prepares outbound packet
    ipv4pkt = packet.find('ipv4')
    
    if ipv4pkt is not None:
        tcppkt = packet.find('tcp')
        if tcppkt is not None: #if iv4p & TCP
            print "TCP Accept"
            accept(15, 45)
        else: #if IP & ~TCP
            print "IPV4 Failed"
            drop()
            
    else:
        arppkt = packet.find('arp') 
        if arppkt is not None: #if ARP
            print "ARP Accept"
            accept(15, 45)
        else:
            print "Failed"
            drop() #if ~ARP

  def _handle_PacketIn (self, event):
    """
    Handles packet in messages from the switch.
    """

    packet = event.parsed # This is the parsed packet data.
    if not packet.parsed:
      log.warning("Ignoring incomplete packet")
      return

    packet_in = event.ofp # The actual ofp_packet_in message.
    self.do_firewall(packet, packet_in)

def launch ():
  """
  Starts the component
  """
  def start_switch (event):
    log.debug("Controlling %s" % (event.connection,))
    Firewall(event.connection)
  core.openflow.addListenerByName("ConnectionUp", start_switch)