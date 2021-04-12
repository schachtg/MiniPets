package com.example.minipets.logic;

import android.app.Activity;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.minipets.objects.Pet;

public interface HomeInterface {
    public void refreshPet(Pet thePet, View newView, Activity newActivity, View.OnClickListener newListener);
    public void refreshInventory(View newView, Activity newActivity, View.OnClickListener newListener);
    public void updateBackground(int bg, View newView, Activity newActivity);
    public int selectItem(String item, Pet thePet, int bg);
    public void resetInventorySelection();
}
