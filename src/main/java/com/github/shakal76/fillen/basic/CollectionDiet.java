/**
 * Copyright 2023 Dmitry Terakov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ⠀⠀⠀⠀⠀⠀⠀
 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⣶⣾⣿⣿⣿⣿⣿⣶⡆⠀⠀⠀⠀⠀⠀
 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡏⢤⡎⣿⣿⢡⣶⢹⣧⠀⠀⠀⠀⠀⠀
 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣶⣶⣇⣸⣷⣶⣾⣿⠀⠀⠀⠀⠀⠀
 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢨⣿⣿⣿⢟⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀
 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⡏⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀
 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣜⠿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀
 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⣷⣿⡿⣷⣮⣙⠿⣿⣿⣿⣿⣿⡄⠀
 * ⠀⠀⠠⢄⣀⡀⠀⠀⠀⠀⠀⠈⠫⡯⢿⣿⣿⣿⣶⣯⣿⣻⣿⣿⠀
 * ⠀⠀⠤⢆⠆⠈⠉⠳⠤⣄⡀⠀⠀⠀⠙⢻⣿⣿⠿⠿⠿⢻⣿⠙⠇
 *  ⠠⠤⣉⣁⣢⣄⣀⣀⣤⣿⠷⠦⠤⣠⡶⠿⣟⠀⠀⠀⠀⠻⡀⠀
 * ⠀⠀⠔⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠃⠃⠉⠉⠛⠛⠿⢷⡶⠀
 */
package com.github.shakal76.fillen.basic;

import com.github.shakal76.fillen.Fillen;
import com.github.shakal76.fillen.utils.Ingredients;
import com.github.shakal76.fillen.exception.user.UserDietException;
import com.github.shakal76.fillen.utils.FillenList;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * <h3>CollectionDiet processes all implementation of {@link java.util.Collection}, f.e: ArrayList, LinkedList, PriorityQueue...
 * ,including nested</h3>
 * <p>In addition, if this diet comes across a List, Queue or Set, it will pack them into an ArrayList, LinkedList, TreeSet</p>
 * <p>Info about the logic of filling is here - {@link api#base}</p>
 */
public class CollectionDiet extends Fillen.Diet{
    @Override
    public Object menu(Ingredients ingredients) throws UserDietException {
        if (getAllInterfaces(ingredients.type).contains(Collection.class)) {
            Collection result = collectionGenerate(ingredients.type);
            Object callback = seeback(
                    ingredients.setType(ingredients.generic.get().get(0))
                            .setGeneric(ingredients.generic.next())
            );
            if (callback != null && callback.getClass().isAssignableFrom(FillenList.class)) {
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
            return new TreeSet();
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
