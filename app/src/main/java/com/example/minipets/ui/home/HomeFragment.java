package com.example.minipets.ui.home;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.minipets.Pet;
import com.example.minipets.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    private ImageView reactionImg;  // Shows the pet's reaction image
    private ImageView petImg;   // Shows the pet's image
    private Pet thePet; // Object containing details about the pet
    private CountDownTimer countDownTimer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        // Creates the pet
        reactionImg = (ImageView) getView().findViewById(R.id.reactionImage);
        reactionImg.setVisibility(View.GONE);
        petImg = (ImageView) getView().findViewById(R.id.petImage);
        petImg.setOnClickListener(this);    // Sets function for when the pet is clicked

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

        thePet = new Pet("Chester", "Cat", reactionImg, petImg, countDownTimer);
    }

    // Displays the pet's reaction when the user pets the pet
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.petImage:
                thePet.react("Happy");
                break;
        }
    }
}