package com.example.minipets.objects;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//Here is where we will import all of the tests that we will run (the different files).

import com.example.minipets.objects.IndividualTests.TestPet;
import com.example.minipets.objects.IndividualTests.TestShop;
import com.example.minipets.objects.IndividualTests.TestShopItem;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //add all of the above test files here
        TestShop.class,
        TestShopItem.class,
        TestPet.class
})


public class AllTests {
    //add nothing here
}
