package com.gmail.ribil39.service;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
