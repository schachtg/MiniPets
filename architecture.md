MiniPets is a fairly simple concept, which allows us to organize our objects and classes in a compact
manner. The main classes and objects within this project are listed as follows:

Pet 
Shop
ShopItem
PetDatabase
ShopDatabase
Target (used for the fetch mini game) and FetchLogic

Below is a general architecture for MiniPets for iteration one (more to be added in future iterations).

*NOTE* Open in Web IDE to actually see the boxes (was having issues with this, sometimes it does not look normal)



+------------------------------------+            +------------------------------------+         +------------------------------------+
|           UI (visual) layer        |            |          Logic Layer               |         |          Data Layer                |
|                                    |            |                                    |         |                                    |
| Visuals of the pet that was created|            |  Was the pet "pet"                 |         |    Entire Pet object:              |
|                                    |            |                                    |         |    Name of pet                     |
| Contents of the shop (name of items|            |  Where was the ball tossed when    |         |    type of pet                     |
| cost)                              |            |  playing fetch                     |         |    sprite images for pet           |
|                                    |            |                                    |         |                                    |
| Reactions to being pet             |            |  Did the pet catch the ball in the |         |    Amount of Tokens the user has   |
|                                    |            |  fetch game                        |         |                                    |
| Visuals of pet playing fetch       |            |                                    |         |    Items the user has purchased    |
|                                    |            |  Does the user have enough Tokens  |         |                                    |
| Amount of Tokens a user has        |            |  to purchase an item? How many     |         |    Items in the shop               |
|                                    |            |  Tokens does the user have after   |         |                                    |
|                                    |            |  the purchase                      |         |                                    |
|                                    |            |                                    |         |                                    |
|                                    |            |  What items are in the shop        |         |                                    |
|                                    |            |                                    |         |                                    |
|                                    |            |  Can this item be purchased more   |         |                                    |
|                                    |            |  than once.                        |         |                                    |
|                                    |            |                                    |         |                                    |
|                                    |            |  How long is the pets reaction to  |         |                                    |
|                                    |            |  being pet                         |         |                                    |
|                                    |            |                                    |         |                                    |
|                                    |            |                                    |         |                                    |
|                                    |            |                                    |         |                                    |
|                                    |            |                                    |         |                                    |
|                                    |            |                                    |         |                                    |
|                                    |            |                                    |         |                                    |
|                                    |            |                                    |         |                                    |
|                                    |            |                                    |         |                                    |
+------------------------------------+            +------------------------------------+         +------------------------------------+











