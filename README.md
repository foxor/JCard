JCard
=====

A framework for the creation of web-based card and board games


Project Organization
====================

The project consists of 2 main parts: a Java backend, and a javascript frontend.


Frontend
========

The frontend should be as lightweight as possible, dealing primarily with clicking and dragging components created by the backend.

There is one extra responsibility for the frontend: dealing with GEML files.

GEMLs are used for submitting a game.  To alley copyright concerns and encourage hacking, all game rules, and content will be stored in a csv presented by the user at the beginning of the game.
GEMLs are also used to preserve game state.  At any point, the user may save their current progress as a GEML.

When a player logs in:
1.  They are asked to enter a username that nobody else is using right now
2.  They send the GEML for their game
3.  They pick either 'name game' or 'load'
4.  If they pick 'load' they send a GEML 


Backend
=======

The backend processes the game rules and synchronizes state between players in a game.

The rules for a game are submitted by a user in a GEML file and processed as a domain specific language, yet to be named.


GEML
====

GEML stands for Game Expression Markup Language.  GEML is a superset of YAML where some new classes have been added that extend the !!.

Essentially, to design a new game, the designer will create an AST of the game's rules in YAML syntax, using the GEML expressions.

The GEML expressions themselves are documented in the javadoc for the server code

API
===

*   api/1/startgame PUT raw GEML
    *   Process the file submitted as a rules specification GEML.
    *   Sucessful:
        *   Return 200 with YAML {"gameid": gameid}
    *   Error:
        *   Return error code with YAML {"error": internal code, "message": client message}
*   api/1/{gameid} POST raw GEML
    *   Lookup gameid, load GEML, process
    *   Return GEML file with instructions for front end.

Implementation Plan
===================

1.  A game with a single card starting on the left side of the screen, that moves to the right side of the screen when clicked.  Then you win.
2.  ...
3.  Blackjack
4.  Chess
5.  Magic: the gathering
6.  Dominion