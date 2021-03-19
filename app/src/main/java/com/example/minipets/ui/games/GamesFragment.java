package com.example.minipets.ui.games;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.minipets.R;
import com.example.minipets.ui.fetch.FetchActivity;

public class GamesFragment extends Fragment {

    private GamesViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(GamesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_games, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        //create the button you push to play fetch
        Button play_fetch_button = (Button) getView().findViewById(R.id.play_fetch_button);

        //create click listener for that button
        play_fetch_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent play_fetch = new Intent(getContext() /*replacing getAplicationContext()*/, FetchActivity.class);
                //make start intent pass the pet image and whatever ball/toy the player has equiped if this is relevant
                startActivity(play_fetch);
            }
        });
    }
}