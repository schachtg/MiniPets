package com.example.minipets.logic;

import android.content.Context;
import android.database.Cursor;

import com.example.minipets.data_layer.SQLdb;
import com.example.minipets.enums.Outfits;
import com.example.minipets.enums.PetType;
import com.example.minipets.objects.Pet;

import java.sql.SQLException;

public class PetDBLogic {
    SQLdb db;
    Context context;

    public PetDBLogic(Context con){
        context = con;
        db = new SQLdb(con);
    }

    public Pet initPet(Pet thePet){
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (db.getPet().getCount()>0){ //pet already exists
            Cursor cursor = db.getPet();
            cursor.moveToFirst();
            System.out.println("This is the value before the init: " + cursor.getString(3));
            thePet = new Pet(cursor.getString(0), PetType.valueOf(cursor.getString(1)), cursor.getInt(2), Outfits.valueOf(cursor.getString(3)));
        }
        else {
            thePet = new Pet(thePet.getName(), thePet.getType(), 50, Outfits.NONE);
            long insert = db.insertPet(thePet.getName(), thePet.getType().toString(), thePet.getOutfit().toString(), thePet.getHappiness());
            System.out.println("are we actually hitting here?");
        }
        return thePet;
    }

    public void updatePet(String name, String type, int happy, String outfit){
        db.updatePet(1,name, type, happy, outfit);
    }

    public int getSize(){
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return db.getPet().getCount();}
}
