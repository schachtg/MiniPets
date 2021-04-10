package com.example.minipets.objects.IndividualTests;

import android.content.Context;

import com.example.minipets.objects.Shop;
import com.example.minipets.objects.Stock;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestShop {
    Context context;
    @Test
    public void testShop(){
        Shop shop;

        System.out.println("\nStarting testShop");

        shop = new Shop();
        assertNotNull(shop);
        assertNotNull(shop.getAvailableItems());
        assertNotNull(shop.getBoughtItems());

        assertEquals("Chicken costs 3 and total price 3", shop.getAvailableItems()[0].toString());
        assertEquals("Fish costs 4 and total price 4", shop.getAvailableItems()[1].toString());
        assertEquals("Beef costs 3 and total price 3", shop.getAvailableItems()[2].toString());
        assertEquals("Frisbee costs 5 and total price 5", shop.getAvailableItems()[3].toString());
        assertEquals("Ball costs 3 and total price 3", shop.getAvailableItems()[4].toString());

        System.out.println("Finished testShop");
    }

    @Test
    public void testItemList(){
        Shop shop;

        System.out.println("\nStarting testItemList");

        shop = new Shop();
        String[] items = {"Chicken", "Fish", "Beef", "Frisbee", "Ball"};
        for(int i = 0; i < items.length; i++){
            assertEquals(items[i], shop.itemsList()[i]);
        }

        System.out.println("Finished testItemList");
    }

    @Test
    public void testAddBoughtItems(){
        Shop shop;
        Stock stock;

        System.out.println("\nStarting testAddBoughtItems");
        shop = new Shop();
        stock = shop.getAvailableItems()[1];

        //testing adding the first item
        shop.addBoughtItems(stock);
        assertEquals("Fish", shop.getBoughtItems().get(0).getName());

        //testing adding multiple items
        shop.addBoughtItems(stock);
        shop.addBoughtItems(stock);
        assertEquals(3, shop.getBoughtItems().get(0).getCount());

        System.out.println("Finished testAddBoughtItems");
    }
}
