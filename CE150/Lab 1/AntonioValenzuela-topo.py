#!/usr/bin/python
"""
AntonioValenzuela-topo.py
Author: Antonio Valenzuela
anlvalen@ucsc.edu
1420837
January 29, 2018
"""
from mininet.topo import Topo
from mininet.net import Mininet
from mininet.cli import CLI
class MyTopology(Topo):
  """
  Modified version of the default configuration to have 4 hosts connected to a switch
  """
  def __init__(self):
    Topo.__init__(self)
    # Set Up Topology Here

    switch = self.addSwitch('s1') ## Adds a Switch

    # Add Hosts
    host1 = self.addHost('h1')
    host2 = self.addHost('h2') 
    host3 = self.addHost('h3') 
    host4 = self.addHost('h4') 

    # Add links
    self.addLink(host1, switch) 
    self.addLink(host2, switch) 
    self.addLink(host3, switch) 
    self.addLink(host4, switch) 

if __name__ == '__main__':
  """
  to run executable script
    $ chmod +x AntonioValenzuela_topo.py
    $ sudo ./AntonioValenzuela_topo.py
  CLI commands to be used in order for Questions 2 and 3:
    mininet > net
    mininet > pingall 
    mininet > dump
    mininet > iperf
  """

  topo = MyTopology() ## Creates the topology
  net = Mininet( topo=topo ) ## Loads the topology
  net.start() ## Starts Mininet
  # Commands here will run on the simulated topology
  CLI(net)
  net.stop() ## Stops Mininet