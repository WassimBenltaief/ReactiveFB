package com.beltaief.reactivefbexample.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by wassim on 10/10/16.
 */

public class GsonDateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

    private static List<DateFormat> formats;

    {
        formats = new ArrayList<DateFormat>();
        formats.add(createDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        formats.add(createDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        formats.add(createDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"));
        formats.add(createDateFormat("yyyy-MM-dd"));
    }

    private static DateFormat createDateFormat(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat;
    }

    public GsonDateTypeAdapter() {
    }

    @Override
    public synchronized JsonElement serialize(Date date, Type type,
                                              JsonSerializationContext jsonSerializationContext) {
        for (DateFormat dateFormat : formats) {
            try {
                return new JsonPrimitive(dateFormat.format(date));
            } catch (Exception e) {
            }
        }

        return null;
    }

    @Override
    public synchronized Date deserialize(JsonElement jsonElement, Type type,
                                         JsonDeserializationContext jsonDeserializationContext) {
        Exception le = null;
        String dateString = jsonElement.getAsString();
        for (DateFormat dateFormat : formats) {
            try {
                return dateFormat.parse(dateString);
            } catch (Exception e) {
                le = e;
            }
        }
        throw new JsonParseException(le);
    }
}

