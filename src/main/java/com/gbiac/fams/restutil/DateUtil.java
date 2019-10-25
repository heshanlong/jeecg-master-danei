package com.gbiac.fams.restutil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

@Slf4j
public class DateUtil {

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    private static final String DATABASE_DATE_TIME_PATTERN = "yyyyMMddHHmmss";

    public static Date parseDate(String dateString) {
        return parseDate(dateString, DEFAULT_PATTERN);
    }

    public static Date parseDatabaseDatetime(Long databaseDatetime) {
        return parseDate(databaseDatetime.toString(), DATABASE_DATE_TIME_PATTERN);
    }

    public static Long formatDatabaseDatetime(Date date) {
        String dateString = formatDate(date, DATABASE_DATE_TIME_PATTERN);
        return Long.parseLong(dateString);
    }

    /**
     * 根据传入的 pattern 解析日期字符串
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static Date parseDate(String dateString, String pattern) {
        if (StringUtils.isNotBlank(dateString) && StringUtils.isNotBlank(pattern)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            try {
                return simpleDateFormat.parse(dateString);
            } catch (ParseException e) {
//                e.printStackTrace();
                log.error("解析日期字符串出错：dateString[{}], pattern[{}]", dateString, pattern);
            }
        }
        return null;
    }

    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_PATTERN);
    }

    /**
     * 根据传入的 pattern 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        if (null != date && StringUtils.isNotBlank(pattern)) {
            try {
                return new SimpleDateFormat(pattern).format(date);
            } catch (Exception e) {
//                e.printStackTrace();
                log.error("格式化日期出错：date[{}], pattern[{}]", date, pattern);
            }
        }
        return null;
    }


    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        Objects.requireNonNull(date);
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static LocalDate dateToLocalDate(Date date) {
        Objects.requireNonNull(date);
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * 获取昨天开始时间
     *
     * @return
     */
    public static Date getYesterdayStartDate() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDateTime endDateTime = LocalDateTime.of(yesterday, LocalTime.MIN);

        return DateUtil.localDateTimeToDate(endDateTime);
    }

    /**
     * 获取昨天结束时间
     *
     * @return
     */
    public static Date getYesterdayEndDate() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDateTime endDateTime = LocalDateTime.of(yesterday, LocalTime.MAX);

        return DateUtil.localDateTimeToDate(endDateTime);
    }

    /**
     * 根据Date获取一天开始时间
     *
     * @param date
     * @return
     */
    public static Date getStartDate(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDate startDate = instant.atZone(zoneId).toLocalDate();

        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);

        return DateUtil.localDateTimeToDate(startDateTime);
    }

    /**
     * 根据Date获取一天结束时间
     *
     * @param date
     * @return
     */
    public static Date getEndDate(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDate endDate = instant.atZone(zoneId).toLocalDate();

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);

        return DateUtil.localDateTimeToDate(endDateTime);
    }

    /**
     * 根据日期减去天数
     *
     * @param date
     * @param minusDays
     * @return
     */
    public static Date minusDays(Date date, Integer minusDays) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate().minusDays(minusDays);
        LocalDateTime result = LocalDateTime.of(localDate, LocalTime.MIN);

        return DateUtil.localDateTimeToDate(result);
    }

    public static Boolean isToday(Date startTime) {
        if (startTime == null) {
            return false;
        }
        Date today = getTodayStartDate();

        return startTime.equals(today);

    }


    public static Boolean isContainToday(Date startTime, Date endTime) {
        if (startTime == null || endTime == null) {
            return false;
        }
        Date today = getTodayStartDate();
        if ((startTime.before(today) || startTime.equals(today)) && endTime.after(today)) {
            return true;
        }

        return false;

    }

    public static Date getTodayStartDate() {
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MIN);
        return DateUtil.localDateTimeToDate(localDateTime);
    }

    public static Date getTodayEndDate() {
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MAX);
        return DateUtil.localDateTimeToDate(localDateTime);
    }
}
