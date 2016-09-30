package com.beltaief.reactivefb.util;

import com.beltaief.reactivefb.models.Photo;
import com.bluelinelabs.logansquare.typeconverters.DateTypeConverter;
import com.bluelinelabs.logansquare.typeconverters.StringBasedTypeConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by wassim on 9/30/16.
 */
public interface Converters {

    final class BackDatetimeGranularityConverter
            extends StringBasedTypeConverter<Photo.BackDatetimeGranularity> {
        @Override
        public Photo.BackDatetimeGranularity getFromString(String s) {
            return Photo.BackDatetimeGranularity.valueOf(s);
        }

        @Override
        public String convertToString(Photo.BackDatetimeGranularity object) {
            return object.toString();
        }

    }

    final class TimeOnlyConverter extends DateTypeConverter {

        private DateFormat mDateFormat;

        public TimeOnlyConverter() {
            mDateFormat = new SimpleDateFormat("HH:mm");
        }

        public DateFormat getDateFormat() {
            return mDateFormat;
        }

    }

    final class DateOnlyConverter extends DateTypeConverter {

        private DateFormat mDateFormat;

        public DateOnlyConverter() {
            mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }

        public DateFormat getDateFormat() {
            return mDateFormat;
        }
    }

    final class DateTimeConverter extends DateTypeConverter {

        private DateFormat mDateFormat;

        public DateTimeConverter() {
            mDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        }

        public DateFormat getDateFormat() {
            return mDateFormat;
        }
    }

    final class DateTimeZoneConverter extends DateTypeConverter {

        private DateFormat mDateFormat;

        public DateTimeZoneConverter() {
            mDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        }

        public DateFormat getDateFormat() {
            return mDateFormat;
        }
    }

    final class DateTimeMilliZoneConverter extends DateTypeConverter {

        private DateFormat mDateFormat;

        public DateTimeMilliZoneConverter() {
            mDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        }

        public DateFormat getDateFormat() {
            return mDateFormat;
        }
    }

}
