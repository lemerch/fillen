package com.github.shakal76.fillen.basic;

import com.github.shakal76.fillen.Fillen;
import com.github.shakal76.fillen.utils.Ingredients;
import com.github.shakal76.fillen.exception.user.UserDietException;
import com.github.shakal76.fillen.utils.FillenList;
import com.github.shakal76.fillen.utils.Generic;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CollectionDiet extends Fillen.Diet{
    @Override
    public Object menu(Ingredients ingredients) throws UserDietException {
        if (getAllInterfaces(ingredients.type).contains(Collection.class)) {
            Collection result = collectionGenerate(ingredients.type);
            Object callback = seeback(
                    ingredients.setType(ingredients.generic.get().get(0))
                            .setGeneric(ingredients.generic.next())
            );
            if (callback.getClass().isAssignableFrom(FillenList.class)) {
                result.addAll(((FillenList) callback).getList());
            }else {
                result.add(callback);
            }
            return result;
        }
        return null;
    }
    public Collection collectionGenerate(Class<?> type) throws UserDietException {
        if (type.isAssignableFrom(List.class)) {
            return new ArrayList();
        }else if (type.isAssignableFrom(Set.class)) {
            return new HashSet();
        }else if (type.isAssignableFrom(Queue.class)) {
            return new LinkedList();
        }
        try {
            return (Collection) type.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new UserDietException("I cant generate collection from type `" + type + "`", e);
        }
    }
}
