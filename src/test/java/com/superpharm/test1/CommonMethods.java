package com.superpharm.test1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.security.Key;
import java.util.concurrent.TimeUnit;

public class CommonMethods {

    /*Break in commands execution*/
    public static void delay(int timeout) {

        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /*Random value getting method*/
    public static int getRandomValue(int min, int max){

        int randomValue = min + (int) (Math.random() * (max + 1 - min));
        return randomValue;

    }

    /*Search item position in search results - it is needed for adding item to the cart*/
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
        if (max > 30){ //I've got the problems if there are more than 1 page (30 items) in search result
            max = 30;  //So for now I limited search results with the only first page
        }

        return getRandomValue(min, max);

    }

    /*Add chosen item to cart method*/
    public static void addItemToCart(WebDriver wd, String itemPosition){

        wd.findElement(By.cssSelector("[class=\"col-xs-8 add-to-basket\"][data-position=\"" + itemPosition + "\"]")).click();
        System.out.println("Item was added to cart");

    }

    /*Empty cart method.
    * Items are sequentially removed from the basket using the only one css selector*/
    public static void emptyCart(WebDriver wd){

        int totalAmount = Integer.parseInt(wd.findElement(By.cssSelector("[class=\"total-price-wrap\"] span[class=\"total-count-wrap\"] span[class=\"total-count\"]")).getText());
        for(int i = 1; i <= totalAmount; i++){
            CommonMethods.delay(1000);
            wd.findElement(By.cssSelector("ul[class=\"cart-items\"] div[class=\"cart-item\"] button[class=\"delete-item\"]")).click();
        }
        System.out.println("The cart is empty, " + totalAmount + " items were deleted");
        CommonMethods.delay(2000);

    }

}
