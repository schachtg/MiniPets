package com.example.minipets.logic;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.minipets.R;
import com.example.minipets.enums.PetType;
import com.example.minipets.objects.Pet;
import com.example.minipets.ui.CreatePet.CreatePet;
import com.example.minipets.ui.tutorial.TutorialActivity;

public class CreatePetLogic implements ICreatePetLogic {
    protected ImageView petImg;
    protected EditText petName;
    protected PetType type = PetType.CAT;
    protected Pet thePet;
    protected PetDBLogic dbLogic;
    protected Context context;

    public CreatePetLogic(Context c, ImageView newPetImg, EditText newPetName)
    {
        context = c;
        dbLogic = new PetDBLogic(context);
        petImg = newPetImg;
        petName = newPetName;
    }

    public void changePetButton(PetType newPetType, int newImage)
    {
        petImg.setImageResource(newImage);
        type = newPetType;
    }

    public void submitButton()
    {
        if(!petName.getText().toString().equals("") && !petName.getText().toString().equals("Name")) {
            thePet = new Pet(petName.getText().toString(), type);
            dbLogic.initPet(thePet);
            changeActivity(TutorialActivity.class);
        }
        else
            Toast.makeText(context,"Please Enter a name", Toast.LENGTH_SHORT).show();
    }

    public void changeActivity(Class newActivity)
    {
        Intent nextActivity = new Intent(context, newActivity);
        context.startActivity(nextActivity);
    }
}
