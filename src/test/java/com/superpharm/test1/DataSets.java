package com.superpharm.test1;

public class DataSets {

    /*User details for login workflow
     available dataType: "email", "password"
     available testType: "Positive", "Negative"*/
    public static String userDetails(String testType, String dataType){

        if(testType == "Positive"){
            if(dataType == "email"){
                return "sher130820@gmail.com";
            }else{
                return "J20i12s13";
            }
        }else{
            if(dataType == "email"){
                return "wrongemail";
            }else{
                return "wrongpassword";
            }
        }
    }

    /*Item names for search and adding to cart.
    * I've got problems with Hebrew - encoding problems*/
    public static String itemNameForSearch(){

        String[] itemNameArr = new String[3];
        itemNameArr[0] = "nivea";
        itemNameArr[1] = "dove";
        itemNameArr[2] = "ricci";

        return itemNameArr[CommonMethods.getRandomValue(0,2)];
    }

}
