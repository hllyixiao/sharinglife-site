package cn.com.sharinglife.util;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created by hell on 2018/2/12
 */
public class DataTransUtil {

    private static final String COMPANY_QIAN = "K";
    private static final String COMPANY_WAN = "W";
    private static final String COMPANY_DEFAULT = "0.0";
    private static final String TIME_DISPLAY_JUST_NOW = "刚刚";
    private static final String TIME_DISPLAY_MINUTE = "分钟前";
    private static final String TIME_DISPLAY_HOUR = "小时前";
    private static final String TIME_DISPLAY_YESTERDAY = "昨天";
    private static final String TIME_DISPLAY_YESTERDAY_BEFOR = "前天";
    private static final long RANGE_SECOND = 60;
    private static final long RANGE_MINUTE = 3600;
    private static final long RANGE_HOUR = 86400;
    private static final long RANGE_YESTERDAY = 172800;
    private static final long RANGE_YESTERDAY_BEFOR = 259200;

    public static String getChineseNum(final Integer index) {
        switch (index) {
            case 0:
                return "零";
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "七";
            case 8:
                return "八";
            case 9:
                return "九";
            default:
                return "零";
        }
    }

    /**
     * 用于向前端提供点击量的展示数据
     *
     * @param number
     * @return
     */
    public static String numberToCompany(Double number) {
        DecimalFormat df = new DecimalFormat("#.00");
        if (Objects.nonNull(number)) {
            if (number > 100000) {
                return df.format(number / 10000) + COMPANY_WAN;
            } else if (number > 1000) {
                return df.format(number / 1000) + COMPANY_QIAN;
            }
            return number.toString();
        }
        return COMPANY_DEFAULT;
    }

    /**
     * 用于向前端提供时间数据展示
     *
     * @param date
     * @return
     */
    public static String dateToDisplay(Date date) {
        Date currentDate = new Date();
        if (Objects.nonNull(date)) {
            long seconds = DateUtil.diffSecond(currentDate, date);
            if (seconds < RANGE_SECOND) {
                return TIME_DISPLAY_JUST_NOW;
            } else if (seconds >= RANGE_SECOND && seconds < RANGE_MINUTE) {
                return DateUtil.diffMinute(currentDate, date) + TIME_DISPLAY_MINUTE;
            } else {
                Date todayDate = DateUtil.todayZeroDate(currentDate);
                int h = DateUtil.diffHour(todayDate, date);
                if (h > 0) {
                    if (h <= 24) {
                        return TIME_DISPLAY_YESTERDAY;
                    } else if (h <= 48) {
                        return TIME_DISPLAY_YESTERDAY_BEFOR;
                    } else {
                        return DateUtil.formatTimesTampDate(date);
                    }
                } else {
                    return DateUtil.diffHour(currentDate, todayDate) + h + TIME_DISPLAY_HOUR;
                }
            }
        } else {
            return DateUtil.formatTimesTampDate(currentDate);
        }
    }
}
