JCard
=====

A framework for the creation of web-based card and board games


Project Organization
====================

The project consists of 2 main parts: a Java backend, and a javascript frontend.


Frontend
========

The frontend should be as lightweight as possible, dealing primarily with clicking and dragging components created by the backend.

There is one extra responsibility for the frontend: dealing with YAML files.

YAMLs are used for submitting a game.  To alley copyright concerns and encourage hacking, all game rules, and content will be stored in a csv presented by the user at the beginning of the game.
YAMLs are also used to preserve game state.  At any point, the user may save their current progress as a YAML.

When a player logs in:
1.  They are asked to enter a username that nobody else is using right now
2.  They send the yaml for their game
3.  They pick either 'name game' or 'load'
4.  If they pick 'load' they send a yaml 


Backend
=======

The backend processes the game rules and synchronizes state between players in a game.

The rules for a game are submitted by a user in a YAML file and processed as a domain specific language, yet to be named.
