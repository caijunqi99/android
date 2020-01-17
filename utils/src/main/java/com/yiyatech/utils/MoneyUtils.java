package com.yiyatech.utils;

import java.text.DecimalFormat;
import java.util.Locale;

public class MoneyUtils {

    /**
     * 计算金额 保留两位小数
     * 小数点后面不是0 就保留两位小数
     * 小数点后面是0，就保留整数
     *
     * @param money
     * @return
     */
    public static String calculateMoneyTwo(int money) {
        return doubleTrans(money / 100f);
    }

    /**
     * 计算金额 保留两位小数
     * 小数点后面不是0 就保留两位小数
     * 小数点后面是0，就保留整数
     *
     * @param money
     * @return
     */
    public static String calculateMoneyKeepTwo(int money) {
        return String.format(Locale.CHINA, "%.2f", money / 100f);
    }

    /**
     * 计算金额 保留两位1数
     *
     * @param money
     * @return
     */
    public static String calculateMoneyOne(int money) {
        return String.format(Locale.CHINA, "%.1f", money / 100f);
    }


    /**
     * 计算金额 不保留
     *
     * @param money
     * @return
     */
    public static String calculateMoneyNone(int money) {
        return String.valueOf(money / 100);
    }


    /**
     * 计算金额 保留两位小数
     *
     * @param money
     * @return
     */
    public static String calculateMoneyTwo(long money) {
        return String.format(Locale.CHINA, "%.2f", money / 100f);
    }

    /**
     * 计算金额 保留整数
     *
     * @param money
     * @return
     */
    public static String calculateMoneyZero(long money) {
        return String.format(Locale.CHINA, "%.0f", money / 100f);
    }

    /**
     * 计算金额 保留两位小数
     *
     * @param money
     * @return
     */
    public static String calculateMoneyDoubleTwo(double money) {
        return String.format(Locale.CHINA, "%.2f", money / 100f);
    }


    /**
     * double类型如果小数点后为零显示整数否则保留 返回String
     *
     * @param num
     * @return
     */
    public static String doubleTrans(float num) {
        return String.valueOf(new DecimalFormat("#0.##").format(num));
    }


    /**
     * 计算金额 保留两位小数
     *
     * @param money
     * @return
     */
    public static String calculatePointTwo(String money) {
        double v = Double.parseDouble(money);
        DecimalFormat df = new DecimalFormat("0%");
        return df.format(v);
    }


    /**
     * 计算金额 不保留
     *
     * @param money
     * @return
     */
    public static String calculateDoubleNone(double money) {
        String format = new DecimalFormat("#0").format(money);
        return String.valueOf(format);
    }

}
