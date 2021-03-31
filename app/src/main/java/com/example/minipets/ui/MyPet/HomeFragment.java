package com.example.minipets.ui.MyPet;

import android.database.Cursor;
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

import com.example.minipets.data_layer.SQLdb;
import com.example.minipets.objects.Pet;
import com.example.minipets.data_layer.PetFakeDatabase;
import com.example.minipets.R;

import java.sql.SQLException;

public class HomeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private HomeViewModel homeViewModel;
    private ImageView reactionImg;  // Shows the pet's reaction image
    private ImageView petImg;   // Shows the pet's image
    private ImageView outfitImg;    // Shows the pet's outfit
    private Pet thePet; // Object containing details about the pet
    Spinner inventoryList;  // Displays the user's inventory
    private CountDownTimer countDownTimer;
    private PetFakeDatabase DB = new PetFakeDatabase();
    private String[] inventory;
    private SQLdb db;
    MutableLiveData<String> listen = new MutableLiveData<>(); //listens for a change in outfit
    MutableLiveData<Integer> listen_background = new MutableLiveData<>(); //listens for a change in background
    private int bg_tracker = 0;

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

        // Create new pet if one doesn't exist in database
        db = new SQLdb(getActivity());
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (db.get_pet().getCount()>0){ //pet already exists
            Cursor cursor = db.get_pet();
            cursor.moveToFirst();
            thePet = new Pet(cursor.getString(0), cursor.getString(1), cursor.getInt(3), cursor.getString(2));
        }
        else {
            thePet = new Pet("Chester", "Cat");
            long insert = db.insert_pet(thePet.getName(), thePet.getType(), thePet.getOutfit(), thePet.getHappiness());
        }

        if (db.get_misc().getCount()>0){ //already exists
            Cursor cursor = db.get_pet();
            cursor.moveToFirst();
            bg_tracker = cursor.getInt(2);
        }
        else {
            long insert_bg = db.insert_misc(thePet.getDiff(), bg_tracker);
        }

        // Get attributes of current pet in the database
        // thePet = new Pet(newName, newType, newHappiness, newOutfit)
        inventory = new String[]{"Inventory", "Feed: Chicken", "Feed: Fish", "Feed: Beef", "Outfit: None", "Outfit: Cowboy Hat", "Outfit: Pirate Hat", "Background: Light", "Background: Dark", "Background: Purple"};

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

        if (thePet.getName().equals("")){
            try {
                throw new InvalidNameException("You must have at least one character for your pets name");
            } catch (InvalidNameException e) {
                e.printStackTrace();
            }
        }
        else {
            DB.newPet(reactionImg, petImg);
        }

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
        // Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

        listen.setValue(thePet.getOutfit());
        listen.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                int update = db.update_pet(1,thePet.getName(), thePet.getType(), thePet.getOutfit(), thePet.getHappiness());
                thePet.setLastLogin();
                //System.out.println("we changed your look for y-+ou");
            }
        });

        listen_background.setValue(bg_tracker);
        listen_background.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                thePet.setLastLogin();
                double update = db.update_misc(1, thePet.getDiff(), bg_tracker);
                System.out.println("background changed");
            }
        });

        if(!text.equals("Inventory"))
        {
            String[] splitText = text.split(": ", 2);

            if (splitText[0].equals("Outfit")) {
                thePet.setOutfit(splitText[1]);
            } else if (splitText[0].equals("Feed")) {
                thePet.feed(splitText[1], true);
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