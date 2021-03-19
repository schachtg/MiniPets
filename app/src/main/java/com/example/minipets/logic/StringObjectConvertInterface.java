package com.example.minipets.logic;

import com.example.minipets.objects.ShopItem;

public interface StringObjectConvertInterface {

    public String object_to_string(ShopItem item);
    public ShopItem string_to_object(String item);
    public String array_to_string(ShopItem[] items);

}
