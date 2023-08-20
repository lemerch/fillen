package com.github.shakal76.fillen.base;

import com.github.shakal76.fillen.Fillen;
import com.github.shakal76.fillen.Generic;
import com.github.shakal76.fillen.exception.BadLootException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BaseDietWrappers {
    public Fillen.Diet diet = new Fillen.Diet() {
        @Override
        public Object menu(Class<?> type, String name, Generic generic, Annotation[] annotations) throws BadLootException {
            if(isTypesEquals(type, String.class)) {
                return UUID.randomUUID().toString().replaceAll("-", "");
            }else if(isTypesEquals(type, byte.class) || isTypesEquals(type, Byte.class)) {
                return 5 + (byte)(Math.random() * ((10 - 5) + 1));
            }else if(isTypesEquals(type, short.class) || isTypesEquals(type, Short.class)) {
                return 5 + (short)(Math.random() * ((10 - 5) + 1));
            }else if(isTypesEquals(type, int.class) || isTypesEquals(type, Integer.class)) {
                return 5 + (int)(Math.random() * ((10 - 5) + 1));
            }else if(isTypesEquals(type, long.class) || isTypesEquals(type, Long.class)) {
                return 5 + (long)(Math.random() * ((10 - 5) + 1));
            }else if(isTypesEquals(type, Float.class) || isTypesEquals(type, Float.class)) {
                return 5 + (float)(Math.random() * ((10 - 5) + 1));
            }else if(isTypesEquals(type, double.class) || isTypesEquals(type, Double.class)) {
                return 5 + (Math.random() * ((10 - 5) + 1));
            }else if(isTypesEquals(type, List.class)) {
                List<Object> obj = new ArrayList<>();
                Object result = heart(generic.get(), name, generic.remove(), annotations);
                if (result == null) {
                    return null;
                }else {
                    obj.add(result);
                    return obj;
                }
            }else {
                return null;
            }
        }
    };
}
