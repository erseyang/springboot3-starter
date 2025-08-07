package com.yzkj.framework.utils;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class MessageUtils {

    public static String getMessages(String code, Object[] args) {
        return getMessages(code, args, null);
    }

    public static String getMessages(String code) {
        return getMessages(code, null, null);
    }

    public static String getMessages(String code, Object[] args, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        MessageSource messageSource = (MessageSource) SpringUtils.getBean("messageSource");
        return messageSource.getMessage(code, args, locale);
    }
}
