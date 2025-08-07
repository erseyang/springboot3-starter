package com.yzkj.framework.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

/**
 * 时间组件包
 */
@SuppressWarnings("unused")
public class DateUtil {

    /**
     * 日期时间格式化
     */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 格式化时间
     *
     * @param date string
     * @return LocalDate
     */
    public static LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(date, formatter);
    }

    /**
     * 获取当前日期
     *
     * @return LocalDate
     */
    public static LocalDate localNow() {
        return LocalDate.now();
    }

    /**
     * 获取当前日期当前日期
     *
     * @return 当前日期时间
     */
    public static LocalDateTime localDataTimeNow() {
        return LocalDateTime.now();
    }

    /**
     * 把localDate转为date
     *
     * @return Date对象
     */
    public static Date localDate2Date(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date localDateTime2Date(LocalDateTime localDate) {
        return Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
    }
    /**
     * 获取当前月
     *
     * @return int
     */
    public static Integer getCurrentMonth() {
        return LocalDate.now().getMonthValue();
    }

    /**
     * 获取下一个月
     *
     * @return int
     */
    public static Integer getNextMonth() {
        return LocalDate.now().plusMonths(1).getMonthValue();
    }

    /**
     * 获取当前的年份
     *
     * @return int
     */
    public static Integer getCurrentYear() {
        return LocalDate.now().getYear();
    }

    /**
     * 获取下一年的年份
     *
     * @return int
     */
    public static Integer getNextYear() {
        return LocalDate.now().plusYears(1).getYear();
    }

    /**
     * 组装新的日期
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return LocalDate
     */
    public static LocalDate createLocalDt(Integer year, Integer month, Integer day) {
        return LocalDate.of(year, month, day);
    }

    /**
     * 把localdt 转为 date
     *
     * @param localDate 日期
     * @return date
     */
    public static Date localDt2Date(LocalDate localDate) {
        return java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 格式化时间
     *
     * @param dt 时间格式字符串(yyyy-MM-dd 格式)
     * @return Date
     */
    public static Date string2Date(String dt) {
        LocalDate date = formatDate(dt);
        return localDate2Date(date);
    }

    /**
     * 日期转为string
     *
     * @param dt ''
     * @return ''
     */
    public static String date2String(Date dt) {
        return Optional.ofNullable(dt)
                .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .map(localDate -> localDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .orElse(null);
    }

    /**
     * 获取昨天的日期
     *
     * @return 当前日期的前一天
     */
    public static LocalDate getYesterday() {
        return LocalDate.now().minusDays(1);
    }

    /**
     * 把localdate转为string字符串
     *
     * @param localDate LocalDate
     * @return l
     */
    public static String localDate2String(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * 日期时间格式转为String
     *
     * @param dt 时间
     * @return 字符串
     */
    public static String dateTime2String(Date dt) {
        return Optional.ofNullable(dt)
                .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .map(localDate -> localDate.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .orElse(null);
    }

    /**
     * 日期时间字符串转为Date格式
     *
     * @param dateTime 日期时间字符串
     * @return Date
     */
    public static Date dateTime2Date(String dateTime) {
        LocalDate localDate = LocalDate.parse(dateTime, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        return localDate2Date(localDate);
    }
}
