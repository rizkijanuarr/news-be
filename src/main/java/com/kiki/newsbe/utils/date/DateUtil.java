package com.kiki.newsbe.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String DATE_PATTERN_1 = "dd-MM-yyyy"; // 05-09-2024
    public static String DATE_PATTERN_2 = "yyyy-MM-dd"; // 2024-09-05
    public static String DATE_PATTERN_3 = "yyyy-MM"; // 2024-09
    public static String DATE_PATTERN_4 = "dd MMMM"; // 05 September
    public static String DATE_PATTERN_5 = "dd MMM yyyy"; // 05 Sep 2024
    public static String DATE_PATTERN_6 = "EEEE, dd MMMM yyyy"; // Kamis, 05 September 2024
    public static String DATE_PATTERN_7 = "EEE, dd MMM yyyy"; // Kam, 05 Sep 2024
    public static String DATE_PATTERN_8 = "dd/MM/yyyy"; // 05/09/2024
    public static String DATE_PATTERN_9 = "MMMM yyyy"; // September 2024
    public static String DATE_PATTERN_10 = "yyyy"; // 2024

    public static String TIME_PATTERN_1 = "HH:mm"; // 14:30
    public static String TIME_PATTERN_2 = "HH:mm:ss"; // 14:30:45
    public static String TIME_PATTERN_3 = "hh:mm a"; // 02:30 PM
    public static String TIME_PATTERN_4 = "HH:mm:ss z"; // 14:30:45 WIB
    public static String TIME_PATTERN_5 = "HH:mm:ss Z"; // 14:30:45 +0700
    public static String TIME_PATTERN_6 = "HH:mm:ss.SSS"; // 14:30:45.123

    public static String UTC_IDN = "+7"; // (WIB)
    public static String UTC = "UTC"; // (Waktu Universal)

    // BOSS!
    public static String convertDateToString(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat p = new SimpleDateFormat(pattern, new Locale("id", "ID"));
        return p.format(date);
    }

    // "05-09-2024"
    public static String formatShortDate(Date date) {
        return convertDateToString(date, DATE_PATTERN_1);
    }

    // "Kamis, 05 September 2024"
    public static String formatLongDateTime(Date date) {
        return convertDateToString(date, DATE_PATTERN_6);
    }

    // "2024-09-05 14:30"
    public static String formatWithTime(Date date) {
        return convertDateToString(date, DATE_PATTERN_2 + " " + TIME_PATTERN_1);
    }

}


