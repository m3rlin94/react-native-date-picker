package com.henninghall.date_picker;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.henninghall.date_picker.wheelFunctions.AnimateToDate;
import com.henninghall.date_picker.wheels.Wheel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class WheelChangeListenerImpl implements WheelChangeListener {

    private final PickerView pickerView;

    public WheelChangeListenerImpl(PickerView pickerView) {
        this.pickerView = pickerView;
    }

    @Override
    public void onChange(Wheel picker) {
        WritableMap event = Arguments.createMap();
        TimeZone timeZone = pickerView.timeZone;
        SimpleDateFormat dateFormat = pickerView.getDateFormat();
        Calendar minDate = pickerView.getMinimumDate();
        Calendar maxDate = pickerView.getMaximumDate();
        try {
            dateFormat.setTimeZone(timeZone);
            Calendar date = Calendar.getInstance(timeZone);
            String toParse = this.pickerView.getDateString();


            DateFormat df1 = DateFormat.getDateInstance(DateFormat.MEDIUM, pickerView.locale);
            DateFormat df2 = DateFormat.getDateInstance(DateFormat.SHORT, pickerView.locale);
            DateFormat df3 = DateFormat.getDateInstance(DateFormat.LONG, pickerView.locale);
            DateFormat df4 = DateFormat.getDateInstance(DateFormat.FULL, pickerView.locale);
//            dateFormat = new SimpleDateFormat(getDateFormatTemplate(), Locale.US);
            Date newDate = dateFormat.parse(toParse);
            date.setTime(newDate);

            //
            // 20 Fri 24 Jan 23 05


            if (minDate != null && date.before(minDate)) pickerView.applyOnVisibleWheels(
                    new AnimateToDate(minDate)
            );
            else if (maxDate != null && date.after(maxDate)) pickerView.applyOnVisibleWheels(
                    new AnimateToDate(maxDate)
            );
            else {
                event.putString("date", Utils.dateToIso(date));
                DatePickerManager.context.getJSModule(RCTEventEmitter.class).receiveEvent(pickerView.getId(), "dateChange", event);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
