package com.henninghall.date_picker;

import com.henninghall.date_picker.wheels.Wheel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class DateOrder {

    private Map<Character, Wheel> wheelsPerCharacter = new HashMap<>();
    private char[] charOrder = new char[3];

    DateOrder(PickerView view){
        wheelsPerCharacter.put('y', view.yearWheel);
        wheelsPerCharacter.put('M', view.monthWheel);
        wheelsPerCharacter.put('d', view.dateWheel);
        this.updateOrder(view.locale);
    }

    void updateOrder(Locale locale){
        try {
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
            String pattern = ((SimpleDateFormat)df).toLocalizedPattern();

            charOrder[0] = getFirstYmdCharacter(pattern);
            pattern = pattern.replace(charOrder[0] + "","");
            charOrder[1] = getFirstYmdCharacter(pattern);
            pattern = pattern.replace(charOrder[1] + "","");
            charOrder[2] = getFirstYmdCharacter(pattern);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private char getFirstYmdCharacter(String pattern) throws Exception {
        char[] chars = pattern.toCharArray();
        for (char ch : chars) {
            if("yMd".contains("" + ch)) return ch;
        }
        throw new Exception("Could not find any ymd character in string " + pattern);
    }

    Wheel getWheel(int index){
        return this.wheelsPerCharacter.get(charOrder[index]);
    }

}
