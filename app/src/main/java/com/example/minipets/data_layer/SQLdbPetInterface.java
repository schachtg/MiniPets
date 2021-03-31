package com.example.minipets.data_layer;

import android.database.Cursor;

public interface SQLdbPetInterface {

    public long insert_pet(String name, String type, String outfit, int happy);
    public Cursor get_pet();
    public int update_pet(long id, String name, String type, String outfit, int happy);
    public void delete_pet_all();

}
