package com.beltaief.reactivefb.util;

import android.os.Bundle;
import java.util.Formatter;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class Utils {
    private Utils() {
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
