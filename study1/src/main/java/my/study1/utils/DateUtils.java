package my.study1.utils;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
    private static final Logger LOGGER = Logger.getLogger(DateUtils.class);
    public static final String FORMAT_YYYYMMDD = "yyyy-MM-dd";

    public static String timeToString(Long time) {
        return timeToString(time, FORMAT_YYYYMMDD);
    }

    public static String timeToString(Long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(StringUtils.isEmpty(format) ? FORMAT_YYYYMMDD : format);
        return sdf.format(time);
    }

    public static Long stringToTime(String date) {
        return stringToTime(date, FORMAT_YYYYMMDD);
    }

    public static Long stringToTime(String date, String format) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            cal.setTime(sdf.parse(date));
        } catch (ParseException e) {
            LOGGER.error("Некорректный парсинг даты.", e);
        }

        return cal.getTimeInMillis();
    }
}
