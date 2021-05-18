package com.javaproject.checkingforrequests.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UrlCheckController {
    private final String IS_IT_UP = "The site is up!";
    private final String IS_IT_DOWN = "The site is down!";
    private final String INCORRECT_URL = "The url is incorrrect";
    
    @GetMapping("/check_request")
    public String getStatus(@RequestParam String url) {
        String returnMessage = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            
            int responseCategory = conn.getResponseCode() / 100;
            
            if ( responseCategory != 2 || responseCategory != 3) {
                returnMessage = IS_IT_DOWN;
            }
            else {
                returnMessage = IS_IT_UP;
            }
        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            returnMessage = IS_IT_DOWN;
        }

        return returnMessage;
    }
}
