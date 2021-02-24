MiniPets is a fairly simple concept, which allows us to organize our objects and classes in a fairly compact
manner. The main classes and objects within this project are listed as follows:


Pet
Shop
Feed
FetchGame
Groom
CreationScreen

These six classes, along with our fake database implementation, will be the main drivers of the entire application.
However, the main class out of all of them will be the Pet class, which will be imported to essentially every other
class mentioned above.

Below is a general architecture for MiniPets, in which we will go into slightly more detail after:



     +----------------------------------------+             +-----------------------------------------+                  +------------------------------------------+
     |               UI (visual) layer        |             |                 Logic Layer             |                  |                Data Layer                |
     |                                        |             |                                         |                  |                                          |
     | Visuals of the pet created             |             |  Was the pet "pet"                      |                  |                                          |
     |                                        |             |                                         |                  |                                          |
     | Design of the shop                     |             |  If something "good" happened, raise    |                  |    Name of pet                           |
     |                                        |             |  the "Happiness Meter"                  |                  |                                          |
     | Contents inside the shop (food, clothes|             |                                         |                  |    What the pet is currently wearing     |
     | toys for example)                      |             |  How far was the ball tossed when       |                  |                                          |
     |                                        |             |  playing fetch                          |                  |    How many Tokens the user has currently|
     | "Animations" when fed pet or groomed   |             |                                         |                  |                                          |
     |                                        |             |  Does the user have enough Tokens to    |                  |    Happiness level of the pet            |
     | Visual of the pet playing fetch        |             |  buy this item? How many Tokens does the|                  |                                          |
     |                                        |             |  user have after the purchase           |                  |    What items have been purchased by the |
     | Different choices when creating the    |             |                                         |                  |    user                                  |
     | pet                                    |             |  Is the pet hungry                      |                  |                                          |
     |                                        |             |                                         |                  |    Items that are in the shop            |
     | Amount of Tokens a user has            |             |  What items are in the shop for this    |                  |                                          |
     |                                        |             |  type of pet                            |                  |    What kind of pet is it                |
     | "Happiness Meter" bar showing if the   |             |                                         |                  |                                          |
     | users pet is happy                     |             |  How many Tokens were earned after being|                  |                                          |
     |                                        |             |  pet, groomed, fed or after play        |                  |                                          |
     | The pets outfit                        |             |                                         |                  |                                          |
     |                                        |             |  Is this an appropriate name for a pet  |                  |                                          |
     | The toy that is being used for fetch   |             |                                         |                  |                                          |
     |                                        |             |  Can this item be purchased more than   |                  |                                          |
     |                                        |             |  once (keep track of if already bought) |                  |                                          |
     |                                        |             |                                         |                  |                                          |
     +----------------------------------------+             +-----------------------------------------+                  +------------------------------------------+



To make our data storage as simple as possible, we have decided that the database will store the entire Pet object
instead of single entities. We thought this was a good solution because none of what we need to store on the
database is too heavy, with the largest files being the actual sprites of the pets and the items they may have purchased from the shop.
Having all of this information in the Pet class shortens the amount of code that is needed for the database,
which allows us to focus more on the logic and interface, rather than worrying about what packages need to be
added to the database. However, the Shop class will also have some information stored within the database also (items in the shop).

The Pet object includes the following information:  *NOTE* all of these variables may not be included within iteration one.

Pet name                 Happiness level               Type of pet                  Current Outfit/clothes obtained

Liked foods              Actual image of pet           Tokens


To summarize, the main package in MiniPets is the Pet object. All imporant information lies within the object,
and will be the package that will be imported to all other classes used in this project.
