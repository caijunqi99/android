package com.example.green.local_utils;


import com.tencent.mmkv.MMKV;

/**
 * @author yzzCool
 * @desc 使用的是shengzhigang的原类。
 * @emil yzz20130626@163.com
 * create at  2019-06-18
 */

public class SPUtils {
    public static final String KEY_USER_ID = "userId";//登录成功后用户ID
    public static final String KEY_USER_TOKEN = "Token";//登录成功后用户token
    public static final String KEY_USER_NAME = "username"; // 账号
    public static final String KEY_PASSWORD = "password"; // 密码


    public static final String KEY_INVITERID = "inviterId"; // 邀请人ID

    private static SPUtils sSpUtils;
    private MMKV mMMKV;

    private SPUtils() {
        mMMKV = MMKV.defaultMMKV();
    }

    public static SPUtils getInstance() {
        if (sSpUtils == null) {
            synchronized (SPUtils.class) {
                if (sSpUtils == null) {
                    sSpUtils = new SPUtils();
                }
            }
        }
        return sSpUtils;
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key
     * @param defaultObject
     * @return
     */
    public <T> T getValue(String key, T defaultObject) {
        if (defaultObject == null) {
            defaultObject = (T) "";
        }
        if (defaultObject instanceof String) {
            String value = mMMKV.decodeString(key, (String) defaultObject);
            return (T) value;
        } else if (defaultObject instanceof Integer) {
            Integer value = mMMKV.decodeInt(key, (Integer) defaultObject);
            return (T) value;
        } else if (defaultObject instanceof Boolean) {
            Boolean value = mMMKV.decodeBool(key, (Boolean) defaultObject);
            return (T) value;
        } else if (defaultObject instanceof Float) {
            Float value = mMMKV.decodeFloat(key, (Float) defaultObject);
            return (T) value;
        } else if (defaultObject instanceof Long) {
            Long value = mMMKV.decodeLong(key, (Long) defaultObject);
            return (T) value;
        }
        return null;
    }

    public <T> void setValue(String key, T value) {
        if (value == null) {
            value = (T) "";
        }
        if (value instanceof String) {
            mMMKV.encode(key, (String) value);
        } else if (value instanceof Integer) {
            mMMKV.encode(key, (Integer) value);
        } else if (value instanceof Boolean) {
            mMMKV.encode(key, (Boolean) value);
        } else if (value instanceof Float) {
            mMMKV.encode(key, (Float) value);
        } else if (value instanceof Long) {
            mMMKV.encode(key, (Long) value);
        }
    }

    public void removeValue(String key) {
        mMMKV.removeValueForKey(key);
    }

    public void removeValue(String[] keys) {
        mMMKV.removeValuesForKeys(keys);
    }
}
