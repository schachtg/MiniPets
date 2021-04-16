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
import com.example.minipets.logic.BackgroundDBLogic;
import com.example.minipets.logic.IHomeLogic;
import com.example.minipets.logic.HomeLogic;
import com.example.minipets.data_layer.PetFakeDatabase;
import com.example.minipets.data_layer.SQLdb;
import com.example.minipets.logic.InventoryDBLogic;
import com.example.minipets.logic.PetDBLogic;
import com.example.minipets.objects.Pet;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    protected HomeViewModel homeViewModel;
    protected Pet thePet; // Object containing details about the pet
    private PetFakeDatabase DB = new PetFakeDatabase();
    private ArrayList<String> inventory;
    private SQLdb db;
    MutableLiveData<String> listen = new MutableLiveData<>(); //listens for a change in outfit
    MutableLiveData<Integer> listen_background = new MutableLiveData<>(); //listens for a change in background
    protected int bg_tracker = 0;
    PetDBLogic petDBLogic;
    protected IHomeLogic homeLogic;
    InventoryDBLogic inventoryDBLogic;
    BackgroundDBLogic backgroundDBLogic;

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
        backgroundDBLogic = new BackgroundDBLogic(getActivity());
        thePet = petDBLogic.initPet(thePet);

        // Sets up the inventory
        //TODO Make this pull from database
        inventory = new ArrayList<String>();
        inventory = inventoryDBLogic.initInventory();
        homeLogic.refreshInventory(getView(), getActivity(), this);

        // Creates the pet images
        homeLogic.refreshPet(thePet, getView(), getActivity(), this);
        bg_tracker = backgroundDBLogic.initBackground();
        homeLogic.updateBackground(bg_tracker, getView(), getActivity());

        // Checks if user hasn't logged in recently, and penalizes if necessary
        thePet.calcLastLogin();

        thePet.setOutfit(thePet.getOutfit());   // Re displays the outfit
    }

    // Displays the pet's reaction when the user pets the pet
    @Override
    public void onClick(View v) {
        thePet.setLastLogin();
        switch(v.getId())
        {
            case R.id.imgPet:
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
                petDBLogic.updatePet(thePet.getName(), thePet.getType().toString(), thePet.getHappiness(), thePet.getOutfit().toString());
                thePet.setLastLogin();
            }
        });

        bg_tracker = homeLogic.selectItem(text, thePet, bg_tracker);
        backgroundDBLogic.updateBackground((double)thePet.getHappiness(), bg_tracker);
        homeLogic.updateBackground(bg_tracker, getView(), getActivity());

        if(!text.equals("Inventory")) {
            homeLogic.resetInventorySelection();
            homeLogic.refreshInventory(getView(), getActivity(), this);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}