package com.metromeds.app.service.converters;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class stringConverter {
    public static Object convert(Class<?> dataType, String value) throws ParseException {
        PropertyEditor editor = PropertyEditorManager.findEditor(dataType);
        if (dataType == Date.class) {
            Date date = new Date(Long.parseLong(value.toString()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date formattedDate = sdf.parse(sdf.format(date));
            return formattedDate;
        }
        else if (dataType == Long.class) {
            return (long)Double.parseDouble(value);
        }
        else {
            editor.setAsText(value);
            return editor.getValue();
        }
    }
}
