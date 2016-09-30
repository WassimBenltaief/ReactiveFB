package com.beltaief.reactivefb.util;

public final class JsonUtils {

    /*private JsonUtils() {
    }

    private static Gson buildGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();
    }

    *//**
     * Parse Object to String in JSON format
     *
     * @param obj
     * @return String in JSON format
     *//*
    private static String toJson(Object obj) {
        Gson gson = buildGson();
        return gson.toJson(obj);
    }

    *//**
     * Get JSON string and convert to T (Object) you need
     *
     * @param json
     * @return Object filled with JSON string data
     *//*
    private static <T> T fromJson(String json, Class<T> cls) {
        Gson gson = buildGson();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);

        // check if the Class type is array but the Json is an object
        if (cls != null && cls.isArray() && !(element instanceof JsonArray)) {
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(element);

            Type listType = new TypeToken<T>() {
            }.getType();
            return gson.fromJson(jsonArray, listType);
        }

        return gson.fromJson(json, cls);
    }

    *//**
     * Get JSON string and convert to T (Object) you need
     *
     * @param json
     * @return Object filled with JSON string data
     *//*
    private static <T> T fromJson(byte[] json, Class<T> cls) {
        try {
            String decoded = new String(json, "UTF-8");
            return fromJson(decoded, cls);
        } catch (Exception e) {
            Logger.logError(cls, "Exception", e);
            return null;
        }
    }

    *//**
     * Get JSON string and convert to T (Object) you need
     *
     * @return Object filled with JSON string data
     *//*
    private static <T> T fromJson(byte[] bytes, Type type) {
        try {
            String decoded = new String(bytes, "UTF-8");
            return fromJson(decoded, type);
        } catch (Exception e) {
            return null;
        }
    }

    *//**
     * Get JSON string and convert to T (Object) you need. <br>
     * <br>
     *
     * @param json
     * @return Object filled with JSON string data
     *//*
    public static <T> T fromJson(String json, Type type) {
        Gson gson = buildGson();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);

        return gson.fromJson(element, type);
    }

    *//**
     * Build object from json and inoke to fields that are marked with @Expose
     * annotation
     *
     * @param json
     * @param cls
     * @return
     *//*
    private static <T> T fromJsonExcludeFields(String json, Class<T> cls) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);

        // check if the Class type is array but the Json is an object
        if (cls.isArray() && !(element instanceof JsonArray)) {
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(element);

            Type listType = new TypeToken<T>() {
            }.getType();
            return gson.fromJson(jsonArray, listType);
        }

        return gson.fromJson(json, cls);
    }

    private static class DateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

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

        private DateTypeAdapter() {
        }

        @Override
        public synchronized JsonElement serialize(Date date, Type type,
                                                  JsonSerializationContext jsonSerializationContext) {
            for (DateFormat dateFormat : formats) {
                try {
                    return new JsonPrimitive(dateFormat.format(date));
                } catch (Exception e) {
                    Logger.logError(DateTypeAdapter.class, "Exception on serialize", e);
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
                    Logger.logError(DateTypeAdapter.class, "Exception on deserialize", e);
                    le = e;
                }
            }
            throw new JsonParseException(le);
        }
    }*/

}
