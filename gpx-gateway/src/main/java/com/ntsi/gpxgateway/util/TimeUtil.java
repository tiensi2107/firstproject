package com.ntsi.gpxgateway.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class TimeUtil {
    public enum CF {
        ISO_8601_NO_MILLI {
            @Override
            public DateTimeFormatter getPrinter() {
                return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
            }

            @Override
            public DateTimeFormatter getParser() {
                return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[XXX][X]");
            }
        };

        public abstract DateTimeFormatter getPrinter();

        public abstract DateTimeFormatter getParser();
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return toDate(localDateTime, ZoneOffset.UTC);
    }

    public static Date toDate(LocalDateTime srcLocalDateTime, ZoneId timezone) {
        Objects.requireNonNull(srcLocalDateTime);
        Objects.requireNonNull(timezone);

        return Date.from(srcLocalDateTime.atZone(timezone).toInstant());
    }
}
