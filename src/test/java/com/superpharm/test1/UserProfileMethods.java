package com.superpharm.test1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class UserProfileMethods {

    /*Login method*/
    public static void logIn(WebDriver wd, String userName, String passWord){

        //wd.findElement(By.cssSelector("#loginAnchor")).click();
        wd.findElement(By.xpath("//a[@id='loginAnchor']")).click();

        fillData(wd, "#j_username", userName);
        fillData(wd, "#j_password", passWord);

        CommonMethods.delay(1000);

       // wd.findElement(By.cssSelector("form[id='loginForm'] div button[type='submit']")).click();
        wd.findElement(By.xpath("//form[@id='loginForm']//button[@type='submit']")).click();

        //String userOnSite = wd.findElement(By.cssSelector("#user-name")).getText();
        String userOnSite = wd.findElement(By.xpath("//div[@id='user-name']")).getText();

        System.out.println("User " + userOnSite + " was logged in!");

    }

    /*Data filling method*/
    public static void fillData(WebDriver wd, String selector, String data){

        wd.findElement(By.cssSelector(selector)).click();
        wd.findElement(By.cssSelector(selector)).clear();
        wd.findElement(By.cssSelector(selector)).sendKeys(data);

    }

    /*Logout method*/
    public static void logOut(WebDriver wd){

        String userOnSite = wd.findElement(By.cssSelector("#user-name")).getText();

        wd.findElement(By.id("profile-img-group")).click();
        CommonMethods.delay(500);
       // wd.findElement(By.cssSelector("[href='/logout']")).sendKeys(Keys.ENTER);
        wd.findElement(By.xpath("//a[@href='/logout']")).click();

        System.out.println("User " + userOnSite + " was logged out!");

    }


     /*Open cart popup window method*/
    public static void openCartPopup(WebDriver wd){

        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.findElement(By.cssSelector("[id='cart-group']")).click();

    }

    /*Open full cart page method, read amount and total price of items in the cart*/
    public static void openCart(WebDriver wd){
        openCartPopup(wd);

        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.findElement(By.cssSelector("[href='/cart']")).click();
        wd.findElement(By.cssSelector("[class='dy-lb-close']")).click();

        //String totalSum = wd.findElement(By.cssSelector("[class='total-price-wrap'] div[class='item-price']")).getText();
        String totalSum = wd.findElement(By.xpath("//div[@class='total-price-wrap']//div[@class='item-price']")).getText();

        //String totalAmount = wd.findElement(By.cssSelector("[class='total-price-wrap'] span[class='total-count-wrap'] span[class='total-count']")).getText();
        String totalAmount = wd.findElement(By.xpath("//div[@class='bottom-left']//span[@class='total-count']")).getText();

        System.out.println("The cart was opened. There are " + totalAmount + " item(s) in the cart");
        System.out.println("The total price for all added items = " + totalSum + " NIS");
        CommonMethods.delay(2000);

    }


}
