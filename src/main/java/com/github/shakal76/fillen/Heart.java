package com.github.shakal76.fillen;

import com.github.shakal76.fillen.exception.BadLootException;
import com.github.shakal76.fillen.utils.Generic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

class Heart {
    public static<T> T dinner(Class<T> type,
                              List<String> ignoringlist,
                              Map<String, Object> settinglist,
                              Bag bag) throws BadLootException {


        T invoked = null;
        try {
            invoked = type.getConstructor().newInstance();
        }catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new BadLootException("I cant find default constructor in " + type.getName() + "\n" + e.getMessage());
        }
        boolean wasExist = false;
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
                    Object result = null;

                    Ingredients ingredients = new Ingredients(
                            field.getType(), field.getName(),
                            new Generic(field.getGenericType()),
                            field.getDeclaredAnnotations(),
                            field.getModifiers()
                    );
                    for (Fillen.Diet diet : bag.get()) {
                        result = diet.menu(ingredients);
                        if (result != null) {
                            wasExist = true;
                            field.set(invoked, result);
                        }
                    }
                    if (!wasExist) {
                        field.set(invoked, null);
                    }
                }catch (IllegalAccessException e) {}
            }
            field.setAccessible(false);
        }

        T x = null;
        try {
            x = invoked;
        }catch (ClassCastException e) {
        }
        return x;
    }
}
