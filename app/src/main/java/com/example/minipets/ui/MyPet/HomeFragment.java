package com.example.minipets.ui.MyPet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.minipets.R;
import com.example.minipets.enums.Outfits;
import com.example.minipets.enums.PetType;
import com.example.minipets.logic.HomeInterface;
import com.example.minipets.logic.HomeLogic;
import com.example.minipets.data_layer.PetFakeDatabase;
import com.example.minipets.data_layer.SQLdb;
import com.example.minipets.logic.InventoryDBLogic;
import com.example.minipets.logic.PetDBLogic;
import com.example.minipets.objects.Pet;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    protected HomeViewModel homeViewModel;
    //protected ImageView reactionImg;  // Shows the pet's reaction image
    //protected ImageView petImg;   // Shows the pet's image
    //protected ImageView outfitImg;    // Shows the pet's outfit
    protected Pet thePet; // Object containing details about the pet
    //Spinner inventoryList;  // Displays the user's inventory
    //protected CountDownTimer countDownTimer;
    protected String[] inventory;
    //protected SQLdb db;
    private HomeViewModel homeViewModel;
    private ImageView reactionImg;  // Shows the pet's reaction image
    private ImageView petImg;   // Shows the pet's image
    private ImageView outfitImg;    // Shows the pet's outfit
    private Pet thePet; // Object containing details about the pet
    Spinner inventoryList;  // Displays the user's inventory
    private CountDownTimer countDownTimer;
    private PetFakeDatabase DB = new PetFakeDatabase();
    private ArrayList<String> inventory;
    private SQLdb db;
    MutableLiveData<String> listen = new MutableLiveData<>(); //listens for a change in outfit
    MutableLiveData<Integer> listen_background = new MutableLiveData<>(); //listens for a change in background
    protected int bg_tracker = 0;
    PetDBLogic petDBLogic;
    protected HomeInterface homeLogic;
    InventoryDBLogic inventoryDBLogic;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //yes this is hardcoded. Iteration one is just the start *100 emoji*

        homeLogic = new HomeLogic();

        thePet = new Pet("Chester", PetType.CAT, 50, Outfits.NONE);
        petDBLogic = new PetDBLogic(getActivity());
        inventoryDBLogic = new InventoryDBLogic(getActivity());
        thePet = petDBLogic.init_pet(thePet);

        // Sets up the inventory
        //TODO Make this pull from database
        inventory = new String[]{"Inventory", "Feed: Chicken", "Feed: Fish", "Feed: Beef", "Outfit: None", "Outfit: Cowboy Hat", "Outfit: Pirate Hat", "Background: Light", "Background: Dark", "Background: Purple"};
        homeLogic.refreshInventory(getView(), getActivity(), this);

        // Creates the pet images
        homeLogic.refreshPet(thePet, getView(), getActivity(), this);
        homeLogic.updateBackground(bg_tracker, getView());
        // Get attributes of current pet in the database
        // thePet = new Pet(newName, newType, newHappiness, newOutfit)
        //inventory = new String[]{"Inventory", "Feed: Chicken", "Feed: Fish", "Feed: Beef", "Outfit: None", "Outfit: Cowboy Hat", "Outfit: Pirate Hat", "Background: Light", "Background: Dark", "Background: Purple"};
        inventory = new ArrayList<String>();
        inventory = inventoryDBLogic.init_inventory();

        // Creates the pet images
        reactionImg = (ImageView) getView().findViewById(R.id.reactionImage);
        reactionImg.setVisibility(View.GONE);
        petImg = (ImageView) getView().findViewById(R.id.petImage);
        petImg.setOnClickListener(this);    // Sets function for when the pet is clicked
        outfitImg = (ImageView) getView().findViewById(R.id.outfitImage);

        // Sets up the inventory
        inventoryList = getView().findViewById(R.id.inv_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, inventory);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inventoryList.setAdapter(adapter);
        inventoryList.setOnItemSelectedListener(this);
        System.out.println("SIZE OF ADAPTER: " + adapter.getCount());
        // Checks if user hasn't logged in recently, and penalizes if necessary
        thePet.calcLastLogin();

        countDownTimer = new CountDownTimer(1000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                reactionImg.setVisibility(View.GONE);
            }
        };

        thePet.setViews(reactionImg, petImg, outfitImg, countDownTimer);
        thePet.setOutfit(thePet.getOutfit());   // Re displays the outfit

        // Updates background
        if (bg_tracker == 0)
            this.getView().setBackgroundColor(Color.WHITE);
        else if (bg_tracker == 1)
            this.getView().setBackgroundColor(Color.rgb(47, 60, 79));
        else if (bg_tracker == 2)
            this.getView().setBackgroundColor(Color.rgb(102, 34, 212));

        DB.newPet(reactionImg, petImg);


    }

    // Displays the pet's reaction when the user pets the pet
    @Override
    public void onClick(View v) {
        thePet.setLastLogin();
        switch(v.getId())
        {
            case R.id.petImage:
                thePet.react(thePet.getMood());
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        listen.setValue(thePet.getOutfit().toString());
        listen.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                petDBLogic.update_pet(thePet.getName(), thePet.getType().toString(), thePet.getHappiness(), thePet.getOutfit().toString());
                thePet.setLastLogin();
            }
        });

        bg_tracker = homeLogic.selectItem(text, thePet, bg_tracker);
        homeLogic.updateBackground(bg_tracker, getView());
        homeLogic.resetInventorySelection();
        if(!text.equals("Inventory"))
        {
            String[] splitText = text.split(": ", 2);

            if (splitText[0].equals("Outfit")) {
                thePet.setOutfit(splitText[1]);
            } else if (splitText[0].equals("Feed")) {
                thePet.feed(splitText[1], true);
                inventoryDBLogic.decrease_count(text);
            } else if (splitText[0].equals("Background")) {
                if(splitText[1].equals("Light")) {
                    bg_tracker = 0;
                }
                else if(splitText[1].equals("Dark")) {
                    bg_tracker = 1;
                }
                else if(splitText[1].equals("Purple")) {
                    bg_tracker = 2;
                }
            }
        }
        if (bg_tracker == 0)
            this.getView().setBackgroundColor(Color.WHITE);
        else if (bg_tracker == 1)
            this.getView().setBackgroundColor(Color.rgb(47, 60, 79));
        else if (bg_tracker == 2)
            this.getView().setBackgroundColor(Color.rgb(102, 34, 212));
        inventoryList.setSelection(0);  // Resets list to display the word "Inventory"
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}