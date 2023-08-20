package com.github.shakal76.fillen.base;

import com.github.shakal76.fillen.Fillen;
import com.github.shakal76.fillen.Ingredients;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BaseDiet {

    public static Fillen.Diet diet = new Fillen.Diet() {
        @Override
        public Object menu(Ingredients ingredients) {
            if(isTypesEquals(ingredients.type, String.class)) {
                List<Object> list = new ArrayList<>();
                list.add(UUID.randomUUID().toString().replaceAll("-", ""));
                list.add(UUID.randomUUID().toString().replaceAll("-", ""));
                list.add(UUID.randomUUID().toString().replaceAll("-", ""));
                return list;
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
            }else {
                return null;
            }
        }
    };

}
