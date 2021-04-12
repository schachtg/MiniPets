package com.example.minipets;

import com.example.minipets.objects.IndividualTests.IntegrationTestItem;
import com.example.minipets.objects.IndividualTests.IntegrationTestMisc;
import com.example.minipets.objects.IndividualTests.IntegrationTestPet;
import com.example.minipets.objects.IndividualTests.IntegrationTestShop;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//import test class here

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //add integration tests here.
        IntegrationTestPet.class,
        IntegrationTestShop.class,
        IntegrationTestMisc.class,
        IntegrationTestItem.class
})


public class AllIntegrationTests {
}
