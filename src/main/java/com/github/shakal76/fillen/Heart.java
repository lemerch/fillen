package com.github.shakal76.fillen;

import com.github.shakal76.fillen.exception.BadLootException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

class Heart {
    public static<T> T dinner(Class<T> type,
                              List<Object> ignoringlist,
                              Map<String, Object> settinglist,
                              Bag bag) throws BadLootException {

        Object invoked = null;
        try {
            invoked = type.getConstructor().newInstance();
        }catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new BadLootException("I cant find default constructor in " + type.getName() + "\n" + e.getMessage());
        }

        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            String filedName = field.getName();
            Ingredients ingredients = new Ingredients(
                    field.getType(), field.getName(),
                    field.getDeclaredAnnotations(), field.getModifiers());

            if (settinglist.containsKey(filedName)) {
                try {
                    field.set(invoked, settinglist.get(filedName));
                }
                catch (Exception e) {}
            }else if (! ignoringlist.contains(filedName)) {
                try {
                    Object result = null;

                    // ARRAYS
                    if (field.getType().isArray()) {
                        if (field.getType().getComponentType().isArray()) {
                            Object array = Array.newInstance(field.getType().getComponentType(), 1);
                            Object[] lastInfo = handling(array, field.getType().getComponentType());
                            // get size
                            Ingredients newingredients = new Ingredients(
                                    ((Class<?>) lastInfo[1]), field.getName(),
                                    field.getDeclaredAnnotations(), field.getModifiers());
                            for (Fillen.Diet diet : bag.get()) {
                                Object timed = diet.menu(newingredients);
                                if (timed != null) {
                                    result = timed;
                                }
                            }
                            if (result.getClass().isAssignableFrom(ArrayList.class)) {
                                List<?> list = (List<?>) result;
                                Object finalArray = Array.newInstance((Class<?>) lastInfo[1], list.size());
                                Array.set(lastInfo[0], 0, finalArray);
                                int i = 0;
                                for (Object o : list) {
                                    Array.set(finalArray, i, o);
                                    i++;
                                }
                                result = array;
                            } else {
                                Object finalArray = Array.newInstance((Class<?>) lastInfo[1], 1);
                                Array.set(lastInfo[0], 0, finalArray);
                                Array.set(finalArray, 0, result);
                                result = array;
                            }
                        } else {
                            // get size
                            Ingredients newingredients = new Ingredients(
                                    field.getType().getComponentType(), field.getName(),
                                    field.getDeclaredAnnotations(), field.getModifiers());

                            for (Fillen.Diet diet : bag.get()) {
                                Object timed = diet.menu(newingredients);
                                if (timed != null) {
                                    result = timed;
                                }
                            }
                            if (result.getClass().isAssignableFrom(ArrayList.class)) {
                                List<?> list = (List<?>) result;
                                Object array = Array.newInstance(field.getType().getComponentType(), list.size());
                                int i = 0;
                                for (Object o : list) {
                                    Array.set(array, i, o);
                                    i++;
                                }
                                result = array;
                            } else {
                                Object array = Array.newInstance(field.getType().getComponentType(), 1);
                                Array.set(array, 0, result);
                                result = array;
                            }
                        }
                    }else {
                        for (Fillen.Diet diet : bag.get()) {
                            Object timed = diet.menu(ingredients);
                            if (timed != null) {
                                result = timed;
                            }
                        }
                    }
                    field.set(invoked, result);
                }catch (IllegalAccessException e) {
                    throw new BadLootException(e.getMessage());
                }
            }
            field.setAccessible(false);
        }
        return (T) invoked;
    }
    public static Object[] handling(Object array, Class<?> currentType) {
        if (currentType.getComponentType().isArray()) {
            Object timed = Array.newInstance(currentType.getComponentType(), 1);
            currentType = currentType.getComponentType();
            Array.set(array, 0, timed);
            return handling(timed, currentType);
        }else {
            Object[] res = new Object[2];
            res[0] = array;
            res[1] = currentType.getComponentType();
            return res;
        }
    }

}
