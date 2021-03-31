package com.example.minipets.logic;

import android.content.Context;
import android.database.Cursor;

import com.example.minipets.data_layer.SQLdb;
import com.example.minipets.objects.Pet;

import java.sql.SQLException;

public class PetDBLogic {
    SQLdb db;
    Context context;

    public PetDBLogic(Context con){
        context = con;
        db = new SQLdb(con);
    }

    public void init_pet(Pet thePet){
        try {
            db.open();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (db.get_pet().getCount()>0){ //pet already exists
            Cursor cursor = db.get_pet();
            cursor.moveToFirst();
            thePet = new Pet(cursor.getString(0), cursor.getString(1), cursor.getInt(3), cursor.getString(2));
            System.out.println(thePet.getOutfit());
        }
        else {
            thePet = new Pet("Chester", "Cat");
            long insert = db.insert_pet(thePet.getName(), thePet.getType(), thePet.getOutfit(), thePet.getHappiness());
        }
    }

    public void update_pet(Pet thePet){
        db.update_pet(1,thePet.getName(), thePet.getType(), thePet.getOutfit(), thePet.getHappiness());
        System.out.println(thePet.getOutfit());
    }
}
