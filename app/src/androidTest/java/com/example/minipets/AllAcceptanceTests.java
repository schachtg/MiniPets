package com.example.minipets;

import com.example.minipets.objects.IndividualTests.CheckTokenUpdate;
import com.example.minipets.objects.IndividualTests.TestBuyingAndWearingCowboyHat;
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
        TestFeedingPet.class
})

public class AllAcceptanceTests {
}
