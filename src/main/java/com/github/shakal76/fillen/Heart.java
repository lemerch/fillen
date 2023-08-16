package com.github.shakal76.fillen;

import com.github.shakal76.fillen.exception.BadLootException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

class Heart {
    public static<T> T dinner(Class<T> type,
                              List<Object> ignoringlist,
                              Map<String, Object> settinglist,
                              List<Fillen.UserType> configuration) throws BadLootException {

        Object invoked = null;
        try {
            invoked = type.getConstructor().newInstance();
        }catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new BadLootException("I cant find default constructor in " + type.getName() + "\n" + e.getMessage());
        }

        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            String filedName = field.getName();
            if (settinglist.containsKey(filedName)) {
                try {
                    field.set(invoked, settinglist.get(filedName));
                }
                catch (Exception e) {}
            }else if (! ignoringlist.contains(filedName)) {
                try {
                    if (field.getType().isAssignableFrom(String.class)) {
                        field.set(invoked, UUID.randomUUID().toString().replaceAll("-", ""));
                    }else if (field.getType().isAssignableFrom(Integer.class)) {
                        field.set(invoked, 5 + (int)(Math.random() * ((10 - 5) + 1)));
                    }else if (field.getType().isAssignableFrom(Float.class) || field.getType().isAssignableFrom(Double.class)) {
                        field.set(invoked, 5 + (Math.random() * ((10 - 5) + 1)));
                    }else {
                        for (Fillen.UserType userType : configuration) {
                            field.set(invoked, userType.handler(field));
                        }
                    }
                }catch (Exception e) {}
            }
            field.setAccessible(false);
        }
        return (T) invoked;
    }
}
