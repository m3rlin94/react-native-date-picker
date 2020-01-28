package com.henninghall.date_picker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class LocaleUtils {


    public static String getFullPattern(Locale locale){
        DateFormat df4 = DateFormat.getDateInstance(DateFormat.FULL, locale);
        return ((SimpleDateFormat)df4)
                .toLocalizedPattern()
                .replace(",", "");
    }

    private static ArrayList<String> getFullPatternPieces(Locale locale){
        return Utils.splitOnSpace(getFullPattern(locale));
    }

    public static String getPatternIncluding(String format, Locale locale) {
        for (String piece: getFullPatternPieces(locale)){
            if(piece.contains(format)) {
                return piece;
            }
        }
        return null;
    }

    public static int getPatternPos(String format, Locale locale) {
        ArrayList<String> pieces = getFullPatternPieces(locale);
        for (String piece: pieces){
            if(piece.contains(format)) {
                return pieces.indexOf(piece);
            }
        }
        return -1;
    }
}
