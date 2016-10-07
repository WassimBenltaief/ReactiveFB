package com.beltaief.reactivefb.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;


import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class Utils {
    private static final String EMPTY = "";
    public static final String CHARSET_NAME = "UTF-8";
    private static List<DateFormat> formats = new ArrayList<>();

    private Utils() {
    }

    private static SimpleDateFormat createDateFormat(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat;
    }

    public String getFacebookSDKVersion() {
        String sdkVersion = null;
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            Class<?> cls = classLoader.loadClass("com.facebook.FacebookSdkVersion");
            Field field = cls.getField("BUILD");
            sdkVersion = String.valueOf(field.get(null));
        } catch (ClassNotFoundException |
                NoSuchFieldException |
                IllegalArgumentException |
                IllegalAccessException e) {

            e.printStackTrace();
        }
        return sdkVersion;
    }

    public static String getHashKey(Context context) {
        // Add code to print out the key hash
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.DEFAULT);
            }
        } catch (NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        return null;
    }

    /**
     * <p>
     * Joins the elements of the provided {@code Iterator} into a single String
     * containing the provided elements.
     * </p>
     * <p>
     * <p>
     * No delimiter is added before or after the list. Null objects or empty
     * strings within the iteration are represented by empty strings.
     * </p>
     *
     * @param iterator  the {@code Iterator} of values to join together, may be null
     * @param separator the separator character to use
     * @return the joined String, {@code null} if null iterator input
     */
    public static String join(Iterator<?> iterator, String separator) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return first == null ? EMPTY : first.toString();
        }
        StringBuilder buf = new StringBuilder(256);
        if (first != null) {
            buf.append(first);
        }
        while (iterator.hasNext()) {
            buf.append(separator);
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    /**
     * <p>
     * Joins the elements of the provided {@code Iterator} into a single String
     * containing the provided elements.
     * </p>
     * <p>
     * <p>
     * No delimiter is added before or after the list. Null objects or empty
     * strings within the iteration are represented by empty strings.
     * </p>
     *
     * @param <T>
     * @param iterator  the {@code Iterator} of values to join together, may be null
     * @param separator the separator character to use
     * @return the joined String, {@code null} if null iterator input
     */
    public static <T> String join(Iterator<T> iterator, String separator, Process<T> process) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        T first = iterator.next();
        if (!iterator.hasNext()) {
            return first == null ? EMPTY : process.process(first);
        }
        StringBuilder buf = new StringBuilder(256);
        if (first != null) {
            buf.append(process.process(first));
        }
        while (iterator.hasNext()) {
            buf.append(separator);
            T obj = iterator.next();
            if (obj != null) {
                buf.append(process.process(obj));
            }
        }
        return buf.toString();
    }

    public static String join(Map<?, ?> map, char separator, char valueStartChar, char valueEndChar) {

        if (map == null) {
            return null;
        }
        if (map.size() == 0) {
            return EMPTY;
        }
        StringBuilder buf = new StringBuilder(256);
        boolean isFirst = true;
        for (Entry<?, ?> entry : map.entrySet()) {
            if (isFirst) {
                buf.append(entry.getKey());
                buf.append(valueStartChar);
                buf.append(entry.getValue());
                buf.append(valueEndChar);
                isFirst = false;
            } else {
                buf.append(separator);
                buf.append(entry.getKey());
                buf.append(valueStartChar);
                buf.append(entry.getValue());
                buf.append(valueEndChar);
            }
        }

        return buf.toString();
    }

    public static Bundle getBundle(String mFields, int limit) {
        Bundle bundle = new Bundle();
        if (mFields != null) {
            bundle.putString("fields", mFields);
        }
        if (limit > 0) {
            bundle.putString("limit", String.valueOf(limit));
        }
        return bundle;
    }

    static Date formatDate(String stringDate) {
        Exception le = null;
        if (formats.isEmpty()) {
            formats = new ArrayList<DateFormat>();
            formats.add(createDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
            formats.add(createDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
            formats.add(createDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"));
            formats.add(createDateFormat("yyyy-MM-dd"));
        }

        for (DateFormat dateFormat : formats) {
            try {
                return dateFormat.parse(stringDate);
            } catch (Exception e) {
                Logger.logError(Date.class, "Exception on deserialize", e);
                le = e;
            }
        }
        throw new RuntimeException(le);
    }

    public static class DataResult<T> {
        public List<T> data;
    }

    public static class SingleDataResult<T> {
        public T data;

        @Override
        public String toString() {
            if (data != null) {
                return data.toString();
            }
            return super.toString();
        }
    }


    public interface Process<T> {
        String process(T t);
    }

    @SuppressWarnings("resource")
    public static String encode(String key, String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            mac.init(secretKey);
            byte[] bytes = mac.doFinal(data.getBytes());
            StringBuilder sb = new StringBuilder(bytes.length * 2);
            Formatter formatter = new Formatter(sb);
            for (byte b : bytes) {
                formatter.format("%02x", b);
            }
            return sb.toString();
        } catch (Exception e) {
            Logger.logError(Utils.class, "Failed to create sha256", e);
            return null;
        }
    }
}
