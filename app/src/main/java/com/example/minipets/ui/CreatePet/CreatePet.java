package com.example.minipets.ui.CreatePet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minipets.R;
import com.example.minipets.enums.Outfits;
import com.example.minipets.enums.PetType;
import com.example.minipets.logic.PetDBLogic;
import com.example.minipets.objects.Pet;
import com.example.minipets.ui.tutorial.TutorialActivity;

public class CreatePet extends AppCompatActivity {

    protected ImageView petImg;
    protected EditText petName;
    protected PetType type = PetType.CAT;
    protected Pet thePet;
    PetDBLogic dbLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pet);

        // TODO If pet is already created then nextActivity(MainActivity.class);
        dbLogic = new PetDBLogic(this);
        petImg = (ImageView) findViewById(R.id.imgPet);
        petName = (EditText) findViewById(R.id.nameText);

    }

    public void onClickCat(View v)
    {
        petImg.setImageResource(R.drawable.cat);
        type = PetType.CAT;
    }

    public void onClickDog(View v)
    {
        petImg.setImageResource(R.drawable.dog);
        type = PetType.DOG;
    }

    public void onClickSubmit(View v)
    {
        if(!petName.getText().toString().equals("") && !petName.getText().toString().equals("Name")) {
            // TODO Store new pet attributes (Name: petName.getText().toString(), Type: type)
            thePet = new Pet(petName.getText().toString(), type);
            if (dbLogic.getSize() != 0){
                dbLogic.updatePet(petName.getText().toString(), type.toString(), 50, Outfits.NONE.toString());
            }
            else {
                dbLogic.initPet(thePet);
            }
            nextActivity(TutorialActivity.class);
        }
        else
            Toast.makeText(getApplicationContext(),"Please Enter a name", Toast.LENGTH_SHORT).show();
    }

    public void nextActivity(Class c)
    {
        Intent newActivity = new Intent(this, c);
        startActivity(newActivity);
    }
}