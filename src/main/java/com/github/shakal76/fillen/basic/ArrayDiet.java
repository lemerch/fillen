package com.github.shakal76.fillen.basic;

import com.github.shakal76.fillen.Fillen;
import com.github.shakal76.fillen.utils.Ingredients;
import com.github.shakal76.fillen.exception.user.UserDietException;
import com.github.shakal76.fillen.utils.FillenList;

import java.util.List;
import java.lang.reflect.Array;

public class ArrayDiet extends Fillen.Diet {
    @Override
    public Object menu(Ingredients ingredients) throws UserDietException {
        if(ingredients.type.isArray()) {
            Object result = null;
            if (ingredients.type.getComponentType().isArray()) {
                Object obj = seeback(ingredients.setType(ingredients.type.getComponentType()));
                result = Array.newInstance(ingredients.type.getComponentType(), 1);
                Array.set(result, 0, obj);
            }else {
                Object callback = seeback(ingredients.setType(ingredients.type.getComponentType()));
                if (callback.getClass().isAssignableFrom(FillenList.class)) {
                    List<Object> list = ((FillenList) callback).getList();
                    result = Array.newInstance(ingredients.type.getComponentType(), list.size());
                    for (int i = 0; i < list.size(); i++) {
                        Array.set(result, i, list.get(i));
                    }
                }else {
                    result = Array.newInstance(ingredients.type.getComponentType(), 1);
                    Array.set(result, 0, callback);
                }
            }

            return result;
        }
        return null;
    }
}
