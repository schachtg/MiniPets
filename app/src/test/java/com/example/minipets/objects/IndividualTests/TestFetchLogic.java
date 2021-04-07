package com.example.minipets.objects.IndividualTests;

import com.example.minipets.logic.FetchLogic;
import com.example.minipets.ui.fetch.UiFetchDirective;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestFetchLogic {

    private static final int SCREEN_WIDTH = 500;
    private static final int SCREEN_HEIGHT = 1000;
    private static final int VALID_PET_WIDTH = 25;
    private static final int VALID_PET_HEIGHT = 50;
    private static final int INVALID_PET_WIDTH_ONE = -4;
    private static final int INVALID_PET_WIDTH_TWO = 600;
    private static final int INVALID_PET_HEIGHT_ONE = -8;
    private static final int INVALID_PET_HEIGHT_TWO = 1100;
    private static final int VALID_PET_XPOS = 50;
    private static final int VALID_PET_YPOS = 50;
    private static final int INVALID_PET_XPOS_ONE = -2;
    private static final int INVALID_PET_XPOS_TWO = 499;
    private static final int INVALID_PET_YPOS_ONE = -2;
    private static final int INVALID_PET_YPOS_TWO = 999;

    private static int pet_width;
    private static int pet_height;
    private static int pet_x_pos;
    private static int pet_y_pos;
    private static FetchLogic myLogic;
    private static UiFetchDirective old_directive;
    private static UiFetchDirective cur_directive;

    private static int pet_center_x;    //click here will...
    private static int pet_center_y;    //...be click on pet

    private static int miss_x;      //click here will not...
    private static int miss_y;      //...be a click on pet

    private static int min_x;
    private static int max_x;
    private static int min_y;
    private static int max_y;

    //TODO commented out tests require exceptions that i have not implimented in the Fetch Logic

    @Before
    public void setUp(){
        myLogic = new FetchLogic(SCREEN_WIDTH, SCREEN_HEIGHT);
        old_directive = null;
        cur_directive = null;
    }

    //TODO asking for a directive returns a directive if the pet has a valid state
    @Test
    public void getDirectiveTest(){
        //set valid pet state
        setValidPetState();

        //ask for directive
        cur_directive = myLogic.getNewDirective();

        //verify passed directive is not null
        assertNotNull("Pet in valid state has received no directive from logic.", cur_directive);
    }

    //TODO directives do not generate invalid positions for the pet
    @Test
    public void directivePositionsTest(){
        boolean validPos;

        //set valid pet state
        setValidPetState();

        //loop * 20
        for(int i = 0; i < 20; i++) {
            validPos = true;

            //ask for directive
            cur_directive = myLogic.getNewDirective();
            validPos = validPos && (min_x <= cur_directive.getPetPositionX());
            validPos = validPos && (cur_directive.getPetPositionX() <= max_x);
            validPos = validPos && (min_y <= cur_directive.getPetPositionY());
            validPos = validPos && (cur_directive.getPetPositionY() <= max_y);

            //verify that directive gives a valid position where the pet will not be off the screen
            assertTrue("game logic places pet partially/completely out of bounds", validPos);
        }
    }

    //TODO clicking on the space the pet occupies is read as clicking on the pet
    @Test
    public void clickPetTest(){
        //set valid pet state
        setValidPetState();

        //update game state
        retrieveAndUnpackNextDirective();

        //verify clicking a position the pet occupies counts as clicking the pet
        getClickOnPetLocation();
        assertTrue("clicking on a location the pet occupies was not detected as clicking on the pet", myLogic.clickDetected(pet_center_x, pet_center_y));

    }

    //TODO clicking on the space the pet does not occupy is not read as clicking on the pet
    @Test
    public void missPetTest(){
        //set valid pet state
        setValidPetState();

        //verify clicking on a space unocupied by the pet does not count as clicking the pet
        getMissPetLocation();
        assertFalse("clicking on space not occupied by pet was detected as clicking on pet", myLogic.clickDetected(miss_x,miss_y));
    }

    //TODO clicking the pet in it's inital position is valid
    //this would just be a duplicate of clickPetTest

    //TODO clicking on the pet results in a point boost
    @Test
    public void clickPetPointTest(){
        //set valid pet state
        setValidPetState();

        //ask for directive
        retrieveAndUnpackNextDirective();   //we don't actually need to unpack it, but that's okay

        //click on same area as pet
        getClickOnPetLocation();
        myLogic.clickDetected(pet_center_x, pet_center_y);

        //ask for directive
        retrieveAndUnpackNextDirective();

        //verify that new directive has higher point value
        assertTrue("clicking on the pet successfully does not increase points total",cur_directive.getPoints() > old_directive.getPoints());
    }

    //TODO not clicking on the pet does not result in a point boost
    @Test
    public void missPetPointTest(){
        //set valid pet state
        setValidPetState();

        //ask for directive
        retrieveAndUnpackNextDirective();

        //click on a spot the pet does not occupy
        getMissPetLocation();
        myLogic.clickDetected(miss_x, miss_y);

        //ask for a new directive
        retrieveAndUnpackNextDirective();

        //verify that the new directive does not have more points than the old one
        assertEquals("clicking, but missing the pet resulted in a points increase", old_directive.getPoints(), cur_directive.getPoints());
    }

    //TODO clicking on an invalid location (like x = -1) will throw an error
//    @Test
//    public void invalidLocationClickTest(){
//        //set valid pet state
//        //verify clicking on an area that is outside of the defined screen throws an exception (-ive location)  [TouchPositionException]
//        //verify clicking on an area """" (+ive but too large location) throws an exception                     [TouchPositionException]
//    }

    //TODO clicking on the pet, getting a new location, then clicking on the pet again will be detected propperly [position updates propperly]
    @Test
    public void updateLocationTest(){
        //set valid pet state
        setValidPetState();

        //ask for directive
        retrieveAndUnpackNextDirective();

        //click on the pet
        getClickOnPetLocation();
        assertTrue("first click not detected as clicking pet", myLogic.clickDetected(pet_center_x, pet_center_y));

        //ask for directive
        retrieveAndUnpackNextDirective();
        getClickOnPetLocation();

        //verify clicking on the pet is detected as clicking on the pet again
        assertTrue("second click on the pet was not detected", myLogic.clickDetected(pet_center_x, pet_center_y));
    }

    //TODO clicking on the pet, not getting a new location, then clicking in the same spot will only give you points for the first click.
    @Test
    public void ignorePetClickTest(){
        //set valid pet state
        setValidPetState();

        //click on the pet
        getClickOnPetLocation();
        assertTrue("clicking on pet was not detected", myLogic.clickDetected(pet_center_x, pet_center_y));

        //verify that clicking on the pet again returns false
        assertFalse("clicking on pet a second time without getting a new directive counted as clicking on the pet (this is bad)", myLogic.clickDetected(pet_center_x, pet_center_y));
    }

    //TODO invalid pet dimensions throw an error (x2)
//    @Test
//    public void invalidPetDimensions(){
//        //verify invalid pet state with negative dimensions throws exception        [PetSizeException]
//        //verify invalid pet state with too large dimensions throws exception       [PetSizeException]
//    }

    //TODO invalid initial pet positions throw an error (x2)
//    @Test
//    public void invalidPetPosition(){
//        //verify invalid negative initial pet position throws exception                                         [PetPositionException]
//        //verify invalid positive inital pet position (one that puts part of pet off screen) throws exception   [PetPositionException]
//    }

    //TODO invalid initial pet positions, and clicking anywhere throws an error
//    @Test
//    public void invalidPosAnyClickTest(){
//        //ignoring error, set invalid pet state (invalid position)
//        //verify that clicking on a space the pet does not occupy throws an exception       [PetStateException]
//        //verify that clicking on a space the pet does occupy throws an exception           [PetStateException]
//    }

    //TODO invalid pet dimensions and clicking anywhere throws an error
//    @Test
//    public void invalidDimensionsAnyClickTest(){
//        //ignoring exception, set invalid pet state (invalid dimensions, too wide)
//        //verify that clicking anywhere throws an exception                             [PetStateException]
//    }

    //TODO asking for a directive without initialising the pet throws an exception.
//    @Test
//    public void uninitialisedPetTest(){
//        //verify asking for a directive throws an exception                             [PetStateException]
//    }

    //TODO 5 randomly generated pet positions will result in at-least one location that isn't a duplicate (random test)
    //TODO getting new directives will get new pet locations (unless the random number generation has somehow generated the same pair of numbers)
    @Test
    public void randomPositionTest(){
        UiFetchDirective[] dir_list = new UiFetchDirective[5];
        boolean different_detected = false; //did the position portion of directives change when we asked for a new directive

        //initialise the pet in a valid state
        setValidPetState();

        //get first directive
        //get second directive
        //get third directive
        //get fourth directive
        //get fifth directive
        for(int i = 0; i < 5; i++){
            //assuming no exception is thrown
            dir_list[i] = myLogic.getNewDirective();
        }

        //verify that atleast one of the directives has a different location than the others. (if 5 directives have the same location then something is probably broken)
        for(int i = 0; i < 4 && !different_detected; i++){
            if( (dir_list[i].getPetPositionX() != dir_list[i+1].getPetPositionX()) ||
                    (dir_list[i].getPetPositionY() != dir_list[i+1].getPetPositionY())){
                different_detected = true;
            }
        }

        assertTrue("getting new directives does not change the position of the pet",different_detected);
    }


    @After
    public void tearDown(){
        old_directive = null;
        cur_directive = null;
    }

    private void setValidPetState(){
        //TODO need to include try catch here once we have error handling
        myLogic.definePetState(VALID_PET_WIDTH, VALID_PET_HEIGHT, VALID_PET_XPOS, VALID_PET_YPOS);
        min_x = 0;
        max_x = SCREEN_WIDTH - VALID_PET_WIDTH;
        min_y = 0;
        max_y = SCREEN_HEIGHT - VALID_PET_HEIGHT;
        pet_x_pos = VALID_PET_XPOS;
        pet_y_pos = VALID_PET_YPOS;
        pet_width = VALID_PET_WIDTH;
        pet_height = VALID_PET_HEIGHT;
    }

    //it is expected that no errors will be thrown when this is called
    private void retrieveAndUnpackNextDirective(){
        old_directive = cur_directive;
        cur_directive = myLogic.getNewDirective();
        pet_x_pos = cur_directive.getPetPositionX();
        pet_y_pos = cur_directive.getPetPositionY();
    }


    //auto detemines where the screen can be clicked so that the middle of the pet is clicked
    private void getClickOnPetLocation(){
        int pet_centre_x = pet_x_pos + (pet_width / 2);
        int pet_center_y = pet_y_pos + (pet_height / 2);
    }

    //assumes there is a spot on the display that the pet does not occupy
    private void getMissPetLocation() {
        miss_x = 0;
        miss_y = 0;

        if(0 < pet_x_pos){
            miss_x = pet_x_pos / 2;
        }
        else if((pet_x_pos + pet_width) < SCREEN_WIDTH){
            miss_x = (SCREEN_WIDTH + pet_x_pos + pet_width)/2;
        }

        if(0 < pet_y_pos){
            miss_y = pet_y_pos / 2;
        }
        else if((pet_y_pos + pet_height) < SCREEN_HEIGHT){
            miss_y = (SCREEN_HEIGHT + pet_y_pos + pet_height)/2;
        }
    }
}
