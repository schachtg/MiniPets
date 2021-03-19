# Worksheet 2

1) 

In phase 1 of the projects, one of our planned features was a game where you could “play fetch” with your pet. We assumed that with our familiarity using java, we would have no major issues in programming something like a target-shotting game complete with animations. This feature ultimately had to be pushed to iteration 2 due to buggy and undesired behavior. We made some progress on fixing the feature in iteration 2, but not enough; it was decided that the scope of the feature would need to be simplified in order to be completed in time for the release.
We decided to more-or-less start over, we were running low on time meaning we wouldn’t have as grand of a game as we hoped, but we got things working

[https://code.cs.umanitoba.ca/3350-winter-2021-a03/minipets-comp3350-a03-group12/-/commit/9156ed0f21423964aec3a03276f07c7a1c536366](https://code.cs.umanitoba.ca/3350-winter-2021-a03/minipets-comp3350-a03-group12/-/commit/9156ed0f21423964aec3a03276f07c7a1c536366)

This debt is an example of Reckless and Inadvertent technical debt, but it encroaches on Deliberate. We started programming activities without really understanding how they worked. What mostly makes this Inadvertent was how were planning on implementing the logic; we tried to use basic commands to move objects and calculate trajectories instead of spending hours to learn and understand the complex, but appropriate tools. What makes this Technical debt edge towards Reckless and Deliberate is the fact that we were getting close to the due date for iteration 1 when we made many of these decisions.

Another example of technical debt would be by updating the database to an actual SQLite implementation. This would have been an example of deliberate debt since we were required to start with a fake database. The way we tackled this debt was by first making a working generic SQL database that could insert, delete, update and get information for a single table. The first thing that was tackled was the tokens that are used to purchase items from the shop. From there, the integration of more tables for different parts of the game were introduced, and slowly all of the fake database logic was swapped out for the real deal. 

linked below is when the tokens were complete.

[https://code.cs.umanitoba.ca/3350-winter-2021-a03/minipets-comp3350-a03-group12/-/commit/2c41170505bf83d1bff22c13ae79b2c16a3bcb96](https://code.cs.umanitoba.ca/3350-winter-2021-a03/minipets-comp3350-a03-group12/-/commit/2c41170505bf83d1bff22c13ae79b2c16a3bcb96)

2) 

link to issue created: [https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/41](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/41)

link to where the violation is [https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/presentation/UpdateUserInfoActivity.java](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/presentation/UpdateUserInfoActivity.java)

3) For starters, we addressed the issue of communication being lacking at the start of our previous phase. We did this by having several meetings very early on in order to sort out what everyone needed to do. We also improved our communication by creating more channels in our communication service in order to keep things more organized. As for changes in time estimating and testing, they weren't addressed in our retrospective. However, we will be sure to take another look at these points during the next retrospective so that we may examine issues we didn't previously think of.

4) Interfaces were used for the database implementations to make it easier to have a test and actual database. This would be called an "adapter". 

Observers were also used whenever the outfit was changed for the pet. The listener event would listen to whenever a value is changed on the pet, then would update the values in stored in the database so that the pet would have the same outfit on when exited out of the app. 

link to interface

[https://code.cs.umanitoba.ca/3350-winter-2021-a03/minipets-comp3350-a03-group12/-/tree/shopmix/app/src/main/java/com/example/minipets/data_layer](https://code.cs.umanitoba.ca/3350-winter-2021-a03/minipets-comp3350-a03-group12/-/tree/shopmix/app/src/main/java/com/example/minipets/data_layer)

link to observer 

[https://code.cs.umanitoba.ca/3350-winter-2021-a03/minipets-comp3350-a03-group12/-/blob/master/app/src/main/java/com/example/minipets/ui/MyPet/HomeFragment.java](https://code.cs.umanitoba.ca/3350-winter-2021-a03/minipets-comp3350-a03-group12/-/blob/master/app/src/main/java/com/example/minipets/ui/MyPet/HomeFragment.java)

5) [https://code.cs.umanitoba.ca/3350-winter-2021-a03/minipets-comp3350-a03-group12/-/issues/24](https://code.cs.umanitoba.ca/3350-winter-2021-a03/minipets-comp3350-a03-group12/-/issues/24)

The issue was that we didn't supply any files into our actual logic layer was the issue. We finished a mini game, which added content to our logic layer. 

Commit link

[https://code.cs.umanitoba.ca/3350-winter-2021-a03/minipets-comp3350-a03-group12/-/commit/d97b118494c0df8e14421081ff961529ba03b2a8](https://code.cs.umanitoba.ca/3350-winter-2021-a03/minipets-comp3350-a03-group12/-/commit/d97b118494c0df8e14421081ff961529ba03b2a8)