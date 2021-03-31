package com.example.minipets.objects.IndividualTests;

import com.example.minipets.objects.ShopItem;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestShopItem {

    @Test
    public void testShopItem(){
        ShopItem shopItem;

        System.out.println("\nStarting testShopItem");

        shopItem = new ShopItem("Food", 7);
        assertNotNull(shopItem);
        assertEquals("Food", shopItem.getName());
        assertEquals(7, shopItem.getCost());
        assertEquals(1, shopItem.getCount());

        System.out.println("Finished testShopItem");
    }

    @Test
    public void testAddCount(){
        ShopItem shopItem;

        System.out.println("\nStarting testAddCount");

        shopItem = new ShopItem("Food", 7);
        shopItem.addCount();
        assertEquals(2, shopItem.getCount());

        System.out.println("Finished testAddCount");
    }

    @Test
    public void testGetTotalCost(){
        ShopItem shopItem;

        System.out.println("\nStarting testGetTotalCost");
        shopItem = new ShopItem("Food", 7);
        shopItem.addCount();
        assertEquals(14, shopItem.getTotalCost());

        System.out.println("Finished testGetTotalCost");
    }

    @Test
    public void testToString(){
        ShopItem shopItem;

        System.out.println("\nStarting testToString");
        shopItem = new ShopItem("Food", 7);
        assertEquals("Food costs 7 and total price 7", shopItem.toString());

        System.out.println("Finished testToString");
    }
}
