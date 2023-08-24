package com.github.shakal76.fillen.utils;

import com.github.shakal76.fillen.Fillen;
import com.github.shakal76.fillen.Ingredients;
import com.github.shakal76.fillen.exception.BadLootException;
import com.github.shakal76.fillen.exception.ConverterException;
import com.github.shakal76.fillen.exception.UserDietException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Diet extends Fillen.Diet {


    // name - [type, value]
    Map<String, Object[]> from;
    @Override
    public Object menu(Ingredients ingredients) throws UserDietException {
        for (String name : from.keySet()) {
            if(ingredients.name == name) {
                Object[] info = from.get(name);

                String typeString = (String) info[0];
                Object value = info[1];

                if(ingredients.type.getName() == typeString) {
                    return value;
                }
            }
        }
        return null;
    }
}
public class Converter {

    public<T> T fromTo(Object from, Class<T> to) throws ConverterException {
        Diet diet = new Diet();

        Map<String, Object[]> frominfo = new HashMap<>();
        List<Object> fromValues = new ArrayList<>();

        Class<?> clazz = from.getClass();
        for (Field declaredField : clazz.getDeclaredFields()) {
            Object[] prepare = new Object[2];
            prepare[0] = declaredField.getType().getName();

            String getterName = "get" + declaredField.getName().toUpperCase().charAt(0) + declaredField.getName().substring(1);
            try {
                prepare[1] = clazz.getDeclaredMethod(getterName).invoke(from, null);
            }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new ConverterException(e.getMessage());
            }
            frominfo.put(declaredField.getName(), prepare);
        }
        diet.from = frominfo;

        Fillen fillen = new Fillen(diet);
        try {
            return fillen.dinner(to);
        }catch (BadLootException e) {
            throw new ConverterException(e.getMessage());
        }
    }
}
