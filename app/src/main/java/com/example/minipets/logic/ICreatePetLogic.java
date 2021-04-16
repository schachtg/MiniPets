package com.example.minipets.logic;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.example.minipets.enums.PetType;
import com.example.minipets.objects.Pet;

public interface ICreatePetLogic {
    public void submitButton();
    public void changePetButton(PetType type, int newImage);
    public void changeActivity(Class c);
}
