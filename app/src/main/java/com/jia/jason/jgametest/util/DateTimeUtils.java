//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package com.jia.jason.jgametest.util;

import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class DateTimeUtils {
    public static final int DATETIME_FIELD_REFERSH = 10;
    public static final long ONE_SECOND = 1000L;
    public static final long ONE_MINUTE = 60000L;
    public static final long ONE_HOUR = 3600000L;
    public static final long ONE_DAY = 86400000L;
    public static final String MM_Yue_dd_Ri = "MM月dd日";
    public static final String M_Yue_d_Ri = "M月d日";
    public static final String d_Ri = "d日";
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyy_MM = "yyyy-MM";
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String HH_mm = "HH:mm";
    public static final String yyyy_Nian_MM_Yue_dd_Ri = "yyyy年MM月dd日";
    public static final String yyyy_Nian_MM_Yue = "yyyy年MM月";
    public static final String MM_yy = "MM/yy";
    public static final String dd_MM = "dd/MM";
    public static final String MM_dd = "MM-dd";
    public static final String HH_mm_ss = "HH:mm:ss";
    private static final String[] PATTERNS = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyyMMdd"};
    public static final String KEY_TSLGAPM = "chaos.liu.tslgapm";
    public static boolean hasServerTime;
    public static long tslgapm;
    public static String tss;
    private static String[] weekdays = new String[]{"", "周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    private static String[] weekdays1 = new String[]{"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    public DateTimeUtils() {
    }

    public static void cleanCalendarTime(Calendar c) {
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
    }

    public static <T> Calendar getCalendar(T src, Calendar fallback) {
        if(src != null) {
            try {
                return getCalendar(src);
            } catch (Exception var3) {
                ;
            }
        }

        return (Calendar)fallback.clone();
    }

    public static <T> Calendar getCalendar(T src) {
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        if(src == null) {
            return null;
        } else {
            if(src instanceof Calendar) {
                calendar.setTimeInMillis(((Calendar)src).getTimeInMillis());
            } else if(src instanceof Date) {
                calendar.setTime((Date)src);
            } else if(src instanceof Long) {
                calendar.setTimeInMillis(((Long)src).longValue());
            } else {
                if(!(src instanceof String)) {
                    throw new IllegalArgumentException();
                }

                String nSrc = (String)src;
                if(TextUtils.isEmpty(nSrc)) {
                    return null;
                }

                try {
                    if(Pattern.compile("\\d{4}年\\d{1,2}月\\d{1,2}日").matcher(nSrc).find()) {
                        nSrc = fixDateString(nSrc);
                        return getCalendarByPattern(nSrc, "yyyy-MM-dd");
                    }

                    return getCalendarByPatterns(nSrc, PATTERNS);
                } catch (Exception var6) {
                    try {
                        calendar.setTimeInMillis(Long.valueOf(nSrc).longValue());
                    } catch (NumberFormatException var5) {
                        throw new IllegalArgumentException(var5);
                    }
                }
            }

            return calendar;
        }
    }

    private static String fixDateString(String date) {
        if(TextUtils.isEmpty(date)) {
            return date;
        } else {
            String[] dateArray = date.split("[年月日]");
            if(dateArray.length == 1) {
                dateArray = date.split("-");
            }

            for(int i = 0; i < 3; ++i) {
                if(dateArray[i].length() == 1) {
                    dateArray[i] = "0" + dateArray[i];
                }
            }

            return dateArray[0] + "-" + dateArray[1] + "-" + dateArray[2];
        }
    }

    public static Calendar getCalendarByPattern(String dateTimeStr, String patternStr) {
        try {
            SimpleDateFormat e = new SimpleDateFormat(patternStr, Locale.US);
            e.setLenient(false);
            Date d = e.parse(dateTimeStr);
            Calendar c = Calendar.getInstance();
            c.setLenient(false);
            c.setTimeInMillis(d.getTime());
            return c;
        } catch (Exception var5) {
            throw new IllegalArgumentException(var5);
        }
    }

    public static Calendar getCalendarByPatterns(String dateTimeStr, String[] patternStr) {
        String[] arr$ = patternStr;
        int len$ = patternStr.length;
        int i$ = 0;

        while(i$ < len$) {
            String string = arr$[i$];

            try {
                return getCalendarByPattern(dateTimeStr, string);
            } catch (Exception var7) {
                ++i$;
            }
        }

        throw new IllegalArgumentException();
    }

    public static Calendar getCurrentDateTime() {
        Calendar now = Calendar.getInstance();
        now.setLenient(false);
        if(hasServerTime) {
            now.setTimeInMillis(now.getTimeInMillis() + tslgapm);
        }

        return now;
    }

    public static Calendar getLoginServerDate() {
        return getCalendar(tss);
    }

    public static Calendar getDateAdd(Calendar start, int interval) {
        if(start == null) {
            return null;
        } else {
            Calendar c = (Calendar)start.clone();
            c.add(Calendar.DATE, interval);
            return c;
        }
    }

    public static long getIntervalTimes(Calendar from, Calendar to, long unit) {
        return from != null && to != null?Math.abs(from.getTimeInMillis() - to.getTimeInMillis()) / unit:0L;
    }

    public static int getIntervalDays(String startdate, String enddate, String pattern) {
        byte betweenDays = 0;
        if(startdate != null && enddate != null) {
            Calendar d1 = getCalendarByPattern(startdate, pattern);
            Calendar d2 = getCalendarByPattern(enddate, pattern);
            return getIntervalDays(d1, d2);
        } else {
            return betweenDays;
        }
    }

    public static <T> int getIntervalDays(T from, T to) {
        Calendar startdate = getCalendar(from);
        Calendar enddate = getCalendar(to);
        cleanCalendarTime(startdate);
        cleanCalendarTime(enddate);
        return (int)getIntervalTimes(startdate, enddate, 86400000L);
    }

    public static String getWeekDayFromCalendar(Calendar cal) {
        if(cal == null) {
            throw new IllegalArgumentException();
        } else {
            return weekdays[cal.get(Calendar.DAY_OF_WEEK)];
        }
    }

    public static String getWeekDayFromCalendar1(Calendar cal) {
        if(cal == null) {
            throw new IllegalArgumentException();
        } else {
            return weekdays1[cal.get(Calendar.DAY_OF_WEEK)];
        }
    }

    public static boolean isLeapyear(String date) {
        Calendar calendar = getCalendar(date);
        if(calendar == null) {
            return false;
        } else {
            int year = calendar.get(Calendar.YEAR);
            return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
        }
    }

    public static boolean isRefersh(long beforeTime) {
        return isRefersh(600000L, beforeTime);
    }

    public static boolean isRefersh(long gap, long beforeTime) {
        return (new Date()).getTime() - beforeTime >= gap;
    }

    public static String printCalendarByPattern(Calendar c, String patternStr) {
        if(null != c && null != patternStr) {
            SimpleDateFormat sdf = new SimpleDateFormat(patternStr, Locale.US);
            sdf.setLenient(false);
            return sdf.format(c.getTime());
        } else {
            return null;
        }
    }

    public static int compareCalendarIgnoreTime(Calendar c1, Calendar c2) {
        return c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR) ? Calendar.YEAR :
                (c1.get(Calendar.YEAR) < c2.get(Calendar.YEAR) ? -1 :
                        (c1.get(Calendar.MONTH) > c2.get(Calendar.MONTH) ? 1 :
                                (c1.get(Calendar.MONTH) < c2.get(Calendar.MONTH) ? -1 :
                                        (c1.get(Calendar.DATE) > c2.get(Calendar.DATE) ? 1 :
                                                (c1.get(Calendar.DATE) < c2.get(Calendar.DATE) ? -1 : 0)))));
    }

    public static void setTimeWithHHmm(Calendar src, String HH_mm) {
        if(!TextUtils.isEmpty(HH_mm) && null != src) {
            String[] s = HH_mm.split(":");
            if(s.length == 2) {
                try {
                    cleanCalendarTime(src);
                    src.set(Calendar.HOUR_OF_DAY, Integer.valueOf(s[0]).intValue());
                    src.set(Calendar.MINUTE, Integer.valueOf(s[1]).intValue());
                } catch (NumberFormatException var4) {
                    ;
                }

            }
        }
    }
}
