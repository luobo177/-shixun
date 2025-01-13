package cn.cuit.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    
    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT));
    }
    
    public static String formatDate(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
    }
    
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT));
    }
    
    public static LocalDateTime parseDate(String dateStr) {
        return LocalDateTime.parse(dateStr + " 00:00:00", DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT));
    }
} 