package com.example.minipets;

import com.example.minipets.objects.IndividualTests.IntegrationTestPet;
import com.example.minipets.objects.IndividualTests.IntegrationTestShop;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//import test class here

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //add integration tests here.
        IntegrationTestPet.class,
        IntegrationTestShop.class
})


public class AllIntegrationTests {
}
