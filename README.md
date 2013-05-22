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


API
===

*   api/1/startgame PUT raw YAML
    *   Process the file submitted as a rules specification YAML.
    *   Sucessful:
        *   Return 200 with JSON {"gameid": <gameid>}
    *   Error:
        *   Return error code with JSON {"error": <internal code>, "message": <client message>}
*   api/1/<gameid> POST raw YAML
    *   Lookup gameid, load YAML, process
    *   Return YAML file with instructiosn for front end.

Implementation Plan
===================

1.  A game with a single card starting on the left side of the screen, that moves to the right side of the screen when clicked.  Then you win.
2.  ...
3.  Blackjack
4.  Chess
5.  Magic: the gathering
6.  Dominion
