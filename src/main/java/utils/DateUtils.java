/*
 * Copyright (C) 2016 The yuhaiyang Android Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 */
@SuppressWarnings("unused,WeakerAccess")
public class DateUtils {
    /**
     * 1秒
     */
    public static final long SECOND_1 = 1000L;
    /**
     * 30秒
     */
    public static final long SECOND_30 = 30L * SECOND_1;
    /**
     * 1 分钟
     */
    public static final long MINUTE_1 = 60L * SECOND_1;
    /**
     * 1 小时
     */
    public static final long HOUR_1 = 60L * MINUTE_1;
    /**
     * 1 天
     */
    public static final long DAY_1 = 24L * HOUR_1;
    /**
     * 7 天
     */
    public static final long DAY_7 = 7L * DAY_1;
    /**
     * 一年
     */
    public static final long ONE_YEAR = 365L * 24L * 60L * 60L * 1000L;

    /**
     * 英文简写如：2016
     */
    public static String FORMAT_YEAR = "yyyy";

    /**
     * 英文简写如：12:01
     */
    public static String FORMAT_HM = "HH:mm";

    /**
     * 英文简写如：1-12 12:01
     */
    public static String FORMAT_MDHM = "MM-dd HH:mm";

    /**
     * 英文简写（默认）如：2016-12-01
     */
    @SuppressWarnings("WeakerAccess")
    public static String FORMAT_YMD = "yyyy-MM-dd";

    /**
     * 英文全称  如：2016-12-01 23:15
     */
    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

    /**
     * 英文全称  如：2016-12-01 23:15:06
     */
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 中文简写  如：2016年12月01日
     */
    public static String FORMAT_YMD_CN = "yyyy年MM月dd日";

    /**
     * 中文简写  如：2016年12月01日  12时12分
     */
    public static String FORMAT_YMDHM_CN = "yyyy年MM月dd日 HH时mm分";

    /**
     * 中文全称  如：2016年12月01日  23时15分06秒
     */
    public static String FORMAT_YMDHMS_CN = "yyyy年MM月dd日  HH时mm分ss秒";


    /**
     * format 时间字符串
     *
     * @param time        当前时间的字符串
     * @param targetModel 目标时间模型 例如 yyyy-MM-dd
     * @param nowModel    当前时间模型 例如: MM-dd
     */
    public static String format(String time, String targetModel, String nowModel) {
        return format(time, targetModel, nowModel, Locale.getDefault());
    }

    /**
     * format 时间字符串
     *
     * @param time        当前时间的字符串
     * @param targetModel 目标时间模型 例如 yyyy-MM-dd
     * @param nowModel    当前时间模型 例如: MM-dd
     * @param locale      区域
     */
    public static String format(String time, String targetModel, String nowModel, Locale locale) {
        try {
            SimpleDateFormat nowFormatter = new SimpleDateFormat(nowModel, locale);
            SimpleDateFormat taggetFormatter = new SimpleDateFormat(targetModel, locale);
            Date data = nowFormatter.parse(time);
            time = taggetFormatter.format(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 把字符串转成long
     *
     * @param time     输入的时间
     * @param nowModel 时间模型 例如 yyyy-MM-dd
     */
    public static long formatTolong(String time, String nowModel) {
        return formatTolong(time, nowModel, Locale.getDefault());
    }

    /**
     * 把字符串转成long
     *
     * @param time     输入的时间
     * @param nowModel 时间模型 例如 yyyy-MM-dd
     * @param locale   区域
     */
    public static long formatTolong(String time, String nowModel, Locale locale) {

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(nowModel, locale);
            Date data = formatter.parse(time);
            return data.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * format时间
     * 默认区域是中国
     *
     * @param time     时间
     * @param tagModel 时间模型 例如 yyyy-MM-dd
     * @return format后的字符串
     */
    public static String format(long time, String tagModel) {
        Date date = new Date(time);
        return format(date, tagModel);
    }

    /**
     * format时间
     *
     * @param time     时间
     * @param tagModel 时间模型 例如 yyyy-MM-dd
     * @param locale   地区
     * @return format后的字符串
     */
    public static String format(long time, String tagModel, Locale locale) {
        Date data = new Date(time);
        return format(data, tagModel, locale);
    }


    /**
     * format时间
     * 默认区域是中国
     *
     * @param date     时间
     * @param tagModel 时间模型 例如 yyyy-MM-dd
     * @return format后的字符串
     */
    public static String format(Date date, String tagModel) {
        return format(date, tagModel, Locale.getDefault());
    }

    /**
     * format时间
     *
     * @param date     时间
     * @param tagModel 时间模型 例如 yyyy-MM-dd
     * @param locale   地区
     * @return format后的字符串
     */
    public static String format(Date date, String tagModel, Locale locale) {
        SimpleDateFormat formatter = new SimpleDateFormat(tagModel, locale);
        return formatter.format(date);
    }


    /**
     * 获取一个long型的时间
     */
    public static long getTime(int year, int month, int daya) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, daya);
        return calendar.getTimeInMillis();
    }


    /**
     * 移除时分秒
     */
    public static long removeHourMinSec(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        //设置当前时刻的时钟为0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //设置当前时刻的分钟为0
        c.set(Calendar.MINUTE, 0);
        //设置当前时刻的秒钟为0
        c.set(Calendar.SECOND, 0);
        //设置当前的毫秒钟为0
        c.set(Calendar.MILLISECOND, 0);

        return c.getTimeInMillis();
    }

    /**
     * 判断是不是同一天
     */
    public static boolean isSameDay(long time1, long time2) {
        time1 = removeHourMinSec(time1);
        time2 = removeHourMinSec(time2);
        return time1 == time2;
    }


    /**
     * 获取当前时间移除 时分秒
     */
    public static long getCurrentTimeWithoutHourMinSec() {
        return removeHourMinSec(System.currentTimeMillis());
    }


}
