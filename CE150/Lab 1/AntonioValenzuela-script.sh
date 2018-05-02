#!/usr/bin/env bash
# AntonioValenzuela-script.sh
# Author: Antonio Valenzuela
# script that prints out all even lines for files in current directory
# anlvalen@ucsc.edu
# Jan 29, 2018
awk 'FNR%2==0 {print FILENAME" : "$0}' *