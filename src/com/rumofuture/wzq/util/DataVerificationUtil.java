package com.rumofuture.wzq.util;

import java.util.Objects;
import java.util.regex.Pattern;

public class DataVerificationUtil {

    private final static Pattern mobilePhoneNumberPattern = Pattern.compile("^(1[0-9])\\d{9}$");

    public static boolean isNullObject(Object object) {
        if (null == object) {
            return true;
        }
        return false;
    }

    public static boolean isEmptyString(String string) {
        if (null == string || string.equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isNullDataList(Object... objectList) {
        for (int index = 0; index < objectList.length; index++) {
            if (null == objectList[index]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmptyStringList(String... stringList) {
        for (int index = 0; index < stringList.length; index++) {
            if (null == stringList[index] || stringList[index].equals("")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMobilePhoneNumber(String mobilePhoneNumber) {
        if (isEmptyString(mobilePhoneNumber) || 11 != mobilePhoneNumber.length()) {
            return false;
        }
        return mobilePhoneNumberPattern.matcher(mobilePhoneNumber).matches();
    }

    public static boolean isPassword(String password) {
        if (password.length() < 6) {
            return false;
        }
        return true;
    }
}
