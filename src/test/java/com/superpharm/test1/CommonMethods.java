package com.superpharm.test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class CommonMethods {

    public static void delay(int timeout) {

        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static int getRandomValue(int min, int max){

        int randomValue = min + (int) (Math.random() * (max + 1 - min));
        return randomValue;

    }

    public static int searchItemPosition(WebDriver wd, String itemName){

        wd.findElement(By.id("search-field")).click();
        wd.findElement(By.id("search-field")).clear();
        wd.findElement(By.id("search-field")).sendKeys(itemName);
        wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wd.findElement(By.cssSelector("[href=\"/search/?av=" + itemName + "\"]")).click();

        String itemsNumFound = wd.findElement(By.cssSelector("[id=\"resultsCounter\"]")).getText();
        System.out.println("Search of " + itemName.toUpperCase() + " is completed. There are: " + itemsNumFound + " items");

        int min = 1;
        int max = Integer.parseInt(itemsNumFound);
        if (max > 30){
            max = 30;
        }

        return getRandomValue(1, max);

    }

    public static void addItemToCart(WebDriver wd, String itemPosition){

        wd.findElement(By.cssSelector("[class=\"col-xs-8 add-to-basket\"][data-position=\"" + itemPosition + "\"]")).click();
        System.out.println("Item was added to cart");

    }

}
