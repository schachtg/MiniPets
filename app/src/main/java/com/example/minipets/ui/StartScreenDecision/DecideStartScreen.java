package com.example.minipets.ui.StartScreenDecision;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minipets.logic.PetDBLogic;
import com.example.minipets.ui.CreatePet.CreatePet;
import com.example.minipets.ui.MyPet.MainActivity;

public class DecideStartScreen extends AppCompatActivity {
    PetDBLogic dbLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbLogic = new PetDBLogic(this);
        if (dbLogic.getSize() != 0){
            nextActivity(MainActivity.class);
        }
        else{
            nextActivity(CreatePet.class);
        }
    }


    public void nextActivity(Class c)
    {
        Intent newActivity = new Intent(this, c);
        startActivity(newActivity);
    }
}
