package com.henninghall.date_picker.wheels;

import android.graphics.Paint;

import java.text.*;
import java.util.*;
import com.henninghall.date_picker.*;

public class MonthWheel extends Wheel
{
    public MonthWheel(final PickerView pickerView, final int id) {
        super(pickerView, id);
    }


    @Override
    public ArrayList<String> getValues(Calendar initialDate) {
        ArrayList<String> values = new ArrayList<>();
        for (int i = 0; i <= 11; ++i) {
            values.add(getUsString(initialDate));
            initialDate.add(Calendar.MONTH, 1);
        }
        return values;
    }

    @Override
    public boolean visible() {
        return this.pickerView.mode == Mode.date;
    }

    @Override
    public String getFormatTemplate() {
        return "LLLL";
    }

    @Override
    public Paint.Align getTextAlign() {
        return Paint.Align.LEFT;
    }

    private String getUsString(Calendar cal) {
        return getString(cal, Locale.US);
    }

    private String getLocaleString(Calendar cal) {
        return getString(cal, this.pickerView.locale);
    }

    private String getString(Calendar cal, Locale locale){
        return getFormat(locale).format(cal.getTime());
    }

    private SimpleDateFormat getFormat(Locale locale) {
        return new SimpleDateFormat(this.getFormatTemplate(), locale);
    }
}