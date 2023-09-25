package com.devfm.offcialwebapp.costants;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

    // Method to generate the current date and time
    public Date generateCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return date;
    }
}