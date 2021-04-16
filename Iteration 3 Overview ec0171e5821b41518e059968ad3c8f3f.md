# Iteration 3 Overview

This was one busy iteration. After we had quite a messy and rushed iteration 2, we were met with a huge wall to jump over moving to our final part. This is sort of a summary to go over how we went about tackling the wall of smells that were left from the previous iteration. 

The first thing we went over was all of the smells and other refactoring jobs that we had to do. Below is a list of things that we corrected within this iteration:

- Fixed all OCP violations by replacing with ENUMS. Along side this, those ENUMS were then stored and pulled from the database whenever values were needed to be checked.
- All hardcoded values (except for the one we accepted as technical debt which is listed in our issues tab somewhere) have been removed and are replaced with saved database values.
- Logic layer is finally here! We have taken all logic that was in the UI layer and spread it out. All code that was connecting the database directly to the UI has been moved to a proper logic layer.
- Naming conventions have been fixed, we are all using camel case now.
- Database interfaces have been merged into one, for sure a lot cleaner that way. Sadly the CRUD implementations are still different for each of the four tables since each table has different values being saved, but it isn't the worst thing in the world.
- Naming conventions for methods were hopefully covered also, as we did make everything camel case and the names describe what the method does

With those out of the way, we now fixed everything that smelled from iteration 2. Next was finishing the new features. Create a Pet and the simple Tutorial page was added. Create a Pet was added to allow users to create an absolutely adorable dog! This didn't change much in the code, which ensured we did something right. The only change there was to add a new logic method that searches the database to check what type of pet the user chose, so that when playing the fetch game the correct sprite will show up. 

On top of the new feature, the database went through a big improvement where every single item that could be changed in the entire game is saved automatically. In iteration two we only saved what we intended to save on that iteration, but now the database saves everything.

The fetch game is bug free! Finally after three iterations it is free of bugs. Though it isn't what we initially planned, Alex did a great job adapting to the difficulties that Android studio brings when creating actual "gameplay". 

Disposition was tuned up to fix a small little smell, disposition being the feature that tracks how long you leave your pet alone for. 

The shop was updated with correct items and prices, along with a new and improved test!

The biggest change might be the testing. Integration tests and Acceptance tests were added. Both tests covered all functionality that we could think of (minus the fetch layer, which was tested in unit form). The integration tests covered everything that the DBLogic layer classes utilize, which I hope is passable since unit tests weren't created for those logic layers since those logic layers use the database (which would make them integration tests which are tested?). 

Regardless, we had 1000% more tests written from before, which was a huge improvement. 

In short, we tried really hard here. Fixing all of the errors gave us new perspective on why we are being taught a lot of these practices. We agreed that fixing and refactoring all of the code was more timely than making some of the features. However, we are happy we took the time to fix all of the errors, and have learned a metric *curse word* ton from these past two weeks alone. 

Hopefully we didn't stink up the app while trying to fix things. To us this looks 500x more clean than from before, thanks for the great guidance! 

-Group 12 - MiniPet's dev team.