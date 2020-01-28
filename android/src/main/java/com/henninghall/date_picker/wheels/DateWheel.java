package com.henninghall.date_picker.wheels;

import android.graphics.Paint;

import java.util.*;
import com.henninghall.date_picker.*;

public class DateWheel extends Wheel
{
    public DateWheel(final PickerView pickerView, final int id) {
        super(pickerView, id);
    }


    @Override
    public ArrayList<String> getValues(Calendar initialCal) {
        ArrayList<String> values = new ArrayList<>();
        final int maxDate = 31;
        final int minDate = 1;
        final String initialDate = this.format.format(initialCal.getTime());

        for (int i = minDate; i <= maxDate; ++i) {
            final int currentDate = (Integer.valueOf(initialDate) + i) % maxDate + 1;
            final String currentDateString = String.valueOf(currentDate);
            values.add(currentDateString);
        }
        return values;
    }

    @Override
    public boolean visible() {
        return this.pickerView.mode == Mode.date;
    }

    @Override
    public String getFormatTemplate() {
        return "d";
    }

    @Override
    public Paint.Align getTextAlign() {
        return Paint.Align.RIGHT;
    }

}
