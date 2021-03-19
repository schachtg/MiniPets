package com.example.minipets.logic;

import com.example.minipets.objects.ShopItem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StringObjectConvert implements StringObjectConvertInterface {

    public StringObjectConvert(){

    }
    @Override
    public String object_to_string(ShopItem item, String converted_object) {
        try {
            ByteArrayOutputStream b_out = new ByteArrayOutputStream();
            ObjectOutputStream ob_out = new ObjectOutputStream(b_out);
            ob_out.writeObject(item);
            ob_out.flush();
            converted_object = b_out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return converted_object;
    }

    @Override
    public ShopItem string_to_object(ShopItem item, String converted_object) {
        try {
            byte b[] = converted_object.getBytes();
            ByteArrayInputStream bi = new ByteArrayInputStream(b);
            ObjectInputStream si = new ObjectInputStream(bi);
            item = (ShopItem) si.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public String array_to_string(ShopItem[] items) {
        return null;
    }
}
