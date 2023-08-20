package com.github.shakal76.fillen.base;

import com.github.shakal76.fillen.Fillen;
import com.github.shakal76.fillen.Ingredients;
import com.github.shakal76.fillen.exception.BadLootException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BaseDietWrappers {
    public Fillen.Diet diet = new Fillen.Diet() {
        @Override
        public Object menu(Ingredients ingredients) throws BadLootException {
            if(isTypesEquals(ingredients.type, String.class)) {
                return UUID.randomUUID().toString().replaceAll("-", "");
            }else if(isTypesEquals(ingredients.type, byte.class) || isTypesEquals(ingredients.type, Byte.class)) {
                return 5 + (byte)(Math.random() * ((10 - 5) + 1));
            }else if(isTypesEquals(ingredients.type, short.class) || isTypesEquals(ingredients.type, Short.class)) {
                return 5 + (short)(Math.random() * ((10 - 5) + 1));
            }else if(isTypesEquals(ingredients.type, int.class) || isTypesEquals(ingredients.type, Integer.class)) {
                return 5 + (int)(Math.random() * ((10 - 5) + 1));
            }else if(isTypesEquals(ingredients.type, long.class) || isTypesEquals(ingredients.type, Long.class)) {
                return 5 + (long)(Math.random() * ((10 - 5) + 1));
            }else if(isTypesEquals(ingredients.type, Float.class) || isTypesEquals(ingredients.type, Float.class)) {
                return 5 + (float)(Math.random() * ((10 - 5) + 1));
            }else if(isTypesEquals(ingredients.type, double.class) || isTypesEquals(ingredients.type, Double.class)) {
                return 5 + (Math.random() * ((10 - 5) + 1));
            }else if(isTypesEquals(ingredients.type, List.class)) {
                List<Object> obj = new ArrayList<>();
                Ingredients newIngredients = new Ingredients(
                        ingredients.generic.get(),
                        ingredients.name, ingredients.generic.remove(),
                        ingredients.declaredAnnotations, ingredients.modifier
                );
                Object result = heart(newIngredients);

                obj.add(result);
                return obj;
            }else if(ingredients.type.isArray()) {
                List<Class<?>> list = getListArray(ingredients.type);
                Class<?> newType = list.get(list.size()-1);
                Ingredients newIngredients = new Ingredients(
                        newType, newType.getName(), null,
                        newType.getDeclaredAnnotations(),
                        newType.getModifiers()
                );
                Object result = heart(newIngredients);

                Object array = null;
                if (list.size() > 2) {
                    array = Array.newInstance(ingredients.type.getComponentType(), 1);
                    Object[] info = handling(array, ingredients.type.getComponentType());

                    if (result.getClass().isAssignableFrom(ArrayList.class)) {
                        List<?> userlist = (List<?>) result;
                        Object finalArray = Array.newInstance((Class<?>) info[1], userlist.size());
                        Array.set(info[0], 0, finalArray);
                        int i = 0;
                        for (Object o : userlist) {
                            Array.set(finalArray, i, o);
                            i++;
                        }
                        result = array;
                    } else {
                        Object finalArray = Array.newInstance((Class<?>) info[1], 1);
                        Array.set(info[0], 0, finalArray);
                        Array.set(finalArray, 0, result);
                        result = array;
                    }
                }else {
                    if (result.getClass().isAssignableFrom(ArrayList.class)) {
                        List<?> userlist = (List<?>) result;
                        array = Array.newInstance(list.get(1), userlist.size());
                        int i = 0;
                        for (Object o : userlist) {
                            Array.set(array, i, o);
                            i++;
                        }
                        result = array;
                    } else {
                        array = Array.newInstance(list.get(1), 1);
                        Array.set(array, 0, result);
                        result = array;
                    }
                }

                return result;
            }else {
                return null;
            }
        }
    };

    // TODO: make from it MAP
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
