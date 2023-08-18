package com.github.shakal76.fillen.base;

import com.github.shakal76.fillen.Fillen;
import com.github.shakal76.fillen.exception.BadLootException;

import java.lang.reflect.Type;
import java.util.UUID;

public class BaseDietWrappers {
    public Fillen.Diet diet = new Fillen.Diet() {
        @Override
        public Object menu(Class<?> field, Type[] generics) throws BadLootException {
            if(isTypesEquals(field, String.class)) {
                return UUID.randomUUID().toString().replaceAll("-", "");
            }else if(isTypesEquals(field, byte.class) || isTypesEquals(field, Byte.class)) {
                return 5 + (byte)(Math.random() * ((10 - 5) + 1));
            }else if(isTypesEquals(field, short.class) || isTypesEquals(field, Short.class)) {
                return 5 + (short)(Math.random() * ((10 - 5) + 1));
            }else if(isTypesEquals(field, int.class) || isTypesEquals(field, Integer.class)) {
                return 5 + (int)(Math.random() * ((10 - 5) + 1));
            }else if(isTypesEquals(field, long.class) || isTypesEquals(field, Long.class)) {
                return 5 + (long)(Math.random() * ((10 - 5) + 1));
            }else if(isTypesEquals(field, Float.class) || isTypesEquals(field, Float.class)) {
                return 5 + (float)(Math.random() * ((10 - 5) + 1));
            }else if(isTypesEquals(field, double.class) || isTypesEquals(field, Double.class)) {
                return 5 + (Math.random() * ((10 - 5) + 1));
            }else {
                return null;
            }
        }
    };
}
