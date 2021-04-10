package com.example.minipets.objects.IndividualTests;

import com.example.minipets.objects.Stock;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestStock {

    @Test
    public void testShopItem(){
        Stock stock;

        System.out.println("\nStarting testShopItem");

        stock = new Stock("Food", 7);
        assertNotNull(stock);
        assertEquals("Food", stock.getName());
        assertEquals(7, stock.getCost());
        assertEquals(1, stock.getCount());

        System.out.println("Finished testShopItem");
    }

    @Test
    public void testAddCount(){
        Stock stock;

        System.out.println("\nStarting testAddCount");

        stock = new Stock("Food", 7);
        stock.addCount();
        assertEquals(2, stock.getCount());

        System.out.println("Finished testAddCount");
    }

    @Test
    public void testGetTotalCost(){
        Stock stock;

        System.out.println("\nStarting testGetTotalCost");
        stock = new Stock("Food", 7);
        stock.addCount();
        assertEquals(14, stock.getTotalCost());

        System.out.println("Finished testGetTotalCost");
    }

    @Test
    public void testToString(){
        Stock stock;

        System.out.println("\nStarting testToString");
        stock = new Stock("Food", 7);
        assertEquals("Food costs 7 and total price 7", stock.toString());

        System.out.println("Finished testToString");
    }
}
