package com.superpharm.test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class UserProfileMethods {

    public static void logIn(WebDriver wd, String userName, String passWord){
        wd.findElement(By.cssSelector("#loginAnchor")).click();

        fillData(wd, "#j_username", userName);

        fillData(wd, "#j_password", passWord);

        CommonMethods.delay(1000);

        wd.findElement(By.cssSelector("form[id=\"loginForm\"] div button[type=\"submit\"]")).click();

        String userOnSite = wd.findElement(By.cssSelector("#user-name")).getText();
        System.out.println("User " + userOnSite + " was logged in!");

    }

    public static void fillData(WebDriver wd, String selector, String data){

        wd.findElement(By.cssSelector(selector)).click();
        wd.findElement(By.cssSelector(selector)).clear();
        wd.findElement(By.cssSelector(selector)).sendKeys(data);

    }

    public static void logOut(WebDriver wd){

        String userOnSite = wd.findElement(By.cssSelector("#user-name")).getText();

        wd.findElement(By.id("profile-img-group")).click();
        CommonMethods.delay(1000);
        wd.findElement(By.cssSelector("[href=\"/logout\"]")).click();

        System.out.println("User " + userOnSite + " was logged out!");
        System.out.println();

    }

    public static void openCartPopup(WebDriver wd){

        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.findElement(By.cssSelector("[id=\"cart-group\"]")).click();
        CommonMethods.delay(2000);

    }

    public static void openCart(WebDriver wd){

        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.findElement(By.cssSelector("[href=\"/cart\"]")).click();
        wd.findElement(By.cssSelector("[class=\"dy-lb-close\"]")).click();

        String totalSum = wd.findElement(By.cssSelector("[class=\"total-price-wrap\"] div[class=\"item-price\"]")).getText();

        System.out.println("The cart was opened. The total price for all added items = " + totalSum + " NIS");
        CommonMethods.delay(2000);

    }

}
