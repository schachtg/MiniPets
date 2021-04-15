package com.example.minipets;

import com.example.minipets.objects.IndividualTests.CheckTokenUpdate;
import com.example.minipets.objects.IndividualTests.TestBuyingAndWearingCowboyHat;
import com.example.minipets.objects.IndividualTests.TestChangeBackground;
import com.example.minipets.objects.IndividualTests.TestFeedingPet;
import com.example.minipets.objects.IndividualTests.TestPetCreation;
import com.example.minipets.objects.IndividualTests.TestPettingPet;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//import test class here

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //add integration tests here.
        CheckTokenUpdate.class,
        TestBuyingAndWearingCowboyHat.class,
        TestPettingPet.class,
        TestChangeBackground.class,
        TestFeedingPet.class,
        TestPetCreation.class
})

public class AllAcceptanceTests {
}
