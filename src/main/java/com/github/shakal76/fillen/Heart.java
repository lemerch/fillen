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
package com.github.shakal76.fillen;

import com.github.shakal76.fillen.exception.BadLootException;
import com.github.shakal76.fillen.exception.service.logical.IgnoreFieldNotFoundException;
import com.github.shakal76.fillen.exception.service.logical.SetFieldNotFoundException;
import com.github.shakal76.fillen.exception.service.specialized.HeartException;
import com.github.shakal76.fillen.exception.user.GenericException;
import com.github.shakal76.fillen.exception.user.UserDietException;
import com.github.shakal76.fillen.utils.FillenList;
import com.github.shakal76.fillen.utils.Generic;
import com.github.shakal76.fillen.utils.Ingredients;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * <h3>This class chew the heart of the whole application</h3>
 *
 * <p>
 *     This class contains only one method, but it performs the main function of this library.
 * </p>
 * <p>It takes the class type and context as input</p>
 * <p>
 *     At the output, it returns an already filled object.
 * This method guarantees that all fields will be filled, i.e. in extreme cases you will get null,
 * if it should not be ignored - {@link Flight}
 * </p>
 * <p>
 *     The whole process of work can be decomposed into:
 *     <ul>
 *         <li>creating an object - there must be a default constructor</li>
 *         <li>search for fields that should have a specific value (from the setFiled method)</li>
 *         <li>if the fields should not be initialized, then call custom type handlers</li>
 *         <li>checks the received value from user handlers for the FillenList type - if it is, then takes the first value and assigns it to the field</li>
 *         <li>set the value of the corresponding field</li>
 *     </ul>
 * </p>
 */
class Heart {


    /**
     * <p>This is the central method of this project, that fill your class's fields</p>
     * @param type class that need to fill
     * @param context type that allowed this method get {@code ignorelist, settinglist and bag}
     * @return type that you send in @param type
     * @param <T> filled class that was selected in @param type
     * @throws BadLootException as common exception
     */
    public static<T> T dinner(Class<T> type, Context context) throws BadLootException {

        T invoked = create(type);

        for (Field field : type.getDeclaredFields()) {
            if (field.getType().isAssignableFrom(FillenList.class)) {
                throw new HeartException("FillenList is service class, please use another container in your class :)");
            }
            String setterName = formatSetter(field.getName());

            if (context.settinglist.containsKey(field.getName())) {
                context.settingCounter+=1;
                set(type, setterName, field, invoked, context.settinglist.get(field.getName()));
            }else if (! context.ignoringlist.contains(field.getName())) {
                Object result = handle(field, context.bag);
                set(type, setterName, field, invoked, result);
            }else {
                context.ignoreCounter+=1;
            }
        }
        return invoked;
    }
    private static<T> T create(Class<T> type) throws HeartException {
        try {
            return type.getConstructor().newInstance();
        }catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new HeartException("I cant find default constructor in class `" + type.getName() + "`", e);
        }
    }
    private static Object handle(Field field, Bag bag) throws UserDietException {
        Object result = null;

        Ingredients ingredients = new Ingredients(
                field.getType(), field.getName(),
                new Generic(field.getGenericType()),
                field.getDeclaredAnnotations(),
                field.getModifiers()
        );
        for (Fillen.Diet diet : bag.get()) {
            Object timed = diet.menu(ingredients);
            if (timed != null) {
                result = timed;
            }
        }
        if (result != null && result.getClass().isAssignableFrom(FillenList.class)) {
            result = ((FillenList) result).getList().get(0);
        }
        return result;
    }
    private static void set(Class<?> type, String setterName, Field field, Object invoked, Object result) throws HeartException {
        try {
            type.getDeclaredMethod(setterName, field.getType()).invoke(invoked, result);
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new HeartException("I cant set value through the setter of field `" + field.getName() +
                    "` in class `" + type + "`", e);
        }catch (IllegalArgumentException e) {
            throw new HeartException("Incorrect type of result in field `" + field.getName() +
                    "` in class `" + type + "`", e);
        }
    }

    private static String formatSetter(String fieldName) {
        return "set" + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
    }

    public static void restedChecker(Class<?> type, Context context) throws BadLootException {
        // SettingCheck
        if (context.settingCounter != context.settinglist.size()) {
            for (String name : context.settinglist.keySet()) {
                int smallCounter = 0;
                for (Field field : type.getDeclaredFields()) {
                    if (context.settinglist.containsKey(field.getName())) {
                        smallCounter++;
                    }
                }
                if (smallCounter == 0) {
                    throw new SetFieldNotFoundException("Field `" + name + "` not found in class " + type);
                }
            }
        }
        // IngoreCheck
        if (context.ignoreCounter != context.ignoringlist.size()) {
            for (String fieldName : context.ignoringlist) {
                int smallCounter = 0;
                for (Field field : type.getDeclaredFields()) {
                    if (field.getName() == fieldName) {
                        smallCounter++;
                    }
                }
                if (smallCounter == 0) {
                    throw new IgnoreFieldNotFoundException("Field `" + fieldName + "` not found in class " + type);
                }
            }
        }
    }


}
