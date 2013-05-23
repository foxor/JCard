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

These are the GEML expressions, and how to use them:

*  All:
   *  Params:
      *  ofClass: expression that returns a string
   *  Server: Return all objects of that class
   *  Client: Not used
*  Card:
   *  Params: None
   *  Server: Create a card object, then check for any triggers and propogate
   *  Client: Create a card object, then create DOM representation is necessary
*  Equals:
   *  Params:
      *  Required:
         *  test: an array of expressions.  If all of them are the same, call then, otherwise call else.
   *  Server: Used for boolean logic
   *  Client: Not used
*  If:
   *  Params:
      *  Required:
         *  condition: a boolean expression which is evaluated
      *  Optional:
         *  then: an expression to evaluate if condition is true
         *  else: an expression to evaluate if condition is false
*  MoveTo:
   *  Params:
      *  Required:
         *  card: the card to move
         *  zone: the zone to move the card to
   *  Server: Move the card, then check for any triggers and propogate
   *  Client: Move the card
*  On:
   *  Params:
      *  Required:
         *  target: an expression that returns the object on which to place the event listener
         *  event: a string describing the event to listen to
         *  callback: an expression that is evaluated when the event occurs
      *  Server: Register the event listener
      *  Client: Not used
*  Property:
   *  Params:
      *  Required:
         *  name: the name of the local variable to retrieve
   *  Server: Used during callbacks when there is a specific context on which to call a get function.  The string literal "this" returns the local context object itself.
   *  Client: Not used
*  Zone:
   *  Params:
      *  Optional:
         *  x: the x coordinate of the left edge, expressed as a portion of the width of the game area
         *  y: the y coordinate of the top edge, expressed as a portion of the height of the game area
         *  width: the width of the zone, expressed as a portion of the width of the game area
         *  height: the height of the zone, expressed as a portion of the height of the game area
   *  Server: Create a zone object, then check for any triggers and propogate
   *  Client: Create a zone object, then create DOM representation is necessary


API
===

*   api/1/startgame PUT raw GEML
    *   Process the file submitted as a rules specification GEML.
    *   Sucessful:
        *   Return 200 with JSON {"gameid": <gameid>}
    *   Error:
        *   Return error code with JSON {"error": <internal code>, "message": <client message>}
*   api/1/<gameid> POST raw GEML
    *   Lookup gameid, load GEML, process
    *   Return GEML file with instructiosn for front end.

Implementation Plan
===================

1.  A game with a single card starting on the left side of the screen, that moves to the right side of the screen when clicked.  Then you win.
2.  ...
3.  Blackjack
4.  Chess
5.  Magic: the gathering
6.  Dominion
