HACEP-playground
================

A small project to learn and explore HACEP using a command line.
This is the common base project.

Running HACEP-playground
========================

An example script is in the scripts directory. 

You can override HACEP behaviour passing -D options. Default values are:
* jgroups.configuration=jgroups-tcp.xml
* grid.mode=DIST_SYNC
* grid.owners=2
* grid.buffer=1000 (size of the facts session buffer)
* queue.url=tcp://localhost:61616
* queue.name=HACEP.FACT
* queue.prefetch=5
* queue.consumers=5
* session.compression=false


Please refer to the parent project Readme.md for more details

Usage
-----

Every node will have its own command line interface "attached", which you can use to play with HACEP.
Type 'help' on the command line to show a list of commands:

```shell

all
     List all valuesFromKeys.

get id
     Get an object from the grid.

put id value
     Put an object (id, value) in the grid.

putIfAbsent|putifabsent|pia id value
     Put an object (id, value) in the grid if not already present

locate id
     Locate an object in the grid.

loadtest
     Load example values in the grid

local
     List all local valuesFromKeys.

primary
     List all local valuesFromKeys for which this node is primary.

clear
     Clear all valuesFromKeys.

info
     Information on cache.

replica
    List all local valuesFromKeys for which this node is a replica.
    
routing
     Print routing table.

help
     List of commands.

exit|quit|q|x
     Exit the shell.
```
