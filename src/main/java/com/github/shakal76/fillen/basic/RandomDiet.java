package com.github.shakal76.fillen.basic;

import com.github.shakal76.fillen.Fillen;
import com.github.shakal76.fillen.utils.Ingredients;
import com.github.shakal76.fillen.exception.user.UserDietException;

import java.util.UUID;

public class RandomDiet extends Fillen.Diet {
    @Override
    public Object menu(Ingredients ingredients) throws UserDietException {
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
        }
        return null;
    }
}
