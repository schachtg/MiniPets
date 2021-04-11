package com.example.minipets.ui.MyPet;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.minipets.R;
import com.example.minipets.data_layer.PetFakeDatabase;
import com.example.minipets.data_layer.SQLdb;
import com.example.minipets.enums.Outfits;
import com.example.minipets.enums.PetType;
import com.example.minipets.logic.HomeInterface;
import com.example.minipets.logic.HomeLogic;
import com.example.minipets.logic.PetDBLogic;
import com.example.minipets.objects.Pet;

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
    MutableLiveData<String> listen = new MutableLiveData<>(); //listens for a change in outfit
    MutableLiveData<Integer> listen_background = new MutableLiveData<>(); //listens for a change in background
    protected int bg_tracker = 0;
    PetDBLogic petDBLogic;
    protected HomeInterface homeLogic;

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
        thePet = petDBLogic.init_pet(thePet);

        // Sets up the inventory
        //TODO Make this pull from database
        inventory = new String[]{"Inventory", "Feed: Chicken", "Feed: Fish", "Feed: Beef", "Outfit: None", "Outfit: Cowboy Hat", "Outfit: Pirate Hat", "Background: Light", "Background: Dark", "Background: Purple"};
        homeLogic.refreshInventory(getView(), getActivity(), this);

        // Creates the pet images
        homeLogic.refreshPet(thePet, getView(), getActivity(), this);
        homeLogic.updateBackground(bg_tracker, getView());

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

        listen.setValue(thePet.getOutfit());
        listen.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                petDBLogic.update_pet(thePet.getName(), thePet.getType(), thePet.getHappiness(), thePet.getOutfit());
                thePet.setLastLogin();
            }
        });

        bg_tracker = homeLogic.selectItem(text, thePet, bg_tracker);
        homeLogic.updateBackground(bg_tracker, getView());
        homeLogic.resetInventorySelection();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}