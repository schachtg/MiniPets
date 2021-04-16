package com.example.minipets.ui.CreatePet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minipets.R;
import com.example.minipets.enums.PetType;
import com.example.minipets.logic.CreatePetLogic;
import com.example.minipets.logic.ICreatePetLogic;
import com.example.minipets.logic.PetDBLogic;
import com.example.minipets.objects.Pet;
import com.example.minipets.ui.tutorial.TutorialActivity;

public class CreatePet extends AppCompatActivity {

    protected ICreatePetLogic createPetLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pet);

        createPetLogic = new CreatePetLogic(this, (ImageView) findViewById(R.id.imgPet), findViewById(R.id.nameText));

    }

    public void onClickCat(View v)
    {
        createPetLogic.changePetButton(PetType.CAT, R.drawable.cat);
    }

    public void onClickDog(View v)
    {
        createPetLogic.changePetButton(PetType.DOG, R.drawable.dog);
    }

    public void onClickSubmit(View v)
    {
        createPetLogic.submitButton();
    }
}