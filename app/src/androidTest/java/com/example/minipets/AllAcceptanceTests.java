package com.example.minipets;

import com.example.minipets.objects.IndividualTests.TestChangeBackground;
import com.example.minipets.objects.IndividualTests.TestChangingOutfit;
import com.example.minipets.objects.IndividualTests.TestCheckIfTokensUpdate;
import com.example.minipets.objects.IndividualTests.TestFeedingPet;
import com.example.minipets.objects.IndividualTests.TestPetCreation;
import com.example.minipets.objects.IndividualTests.TestPettingPet;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//import test class here

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //add integration tests here.
        TestPetCreation.class,
        TestPettingPet.class,
        TestChangeBackground.class,
        TestChangingOutfit.class,
        TestFeedingPet.class,
        TestCheckIfTokensUpdate.class
})

public class AllAcceptanceTests {
}
