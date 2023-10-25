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
package com.github.lemerch.fillen;

import com.github.lemerch.fillen.exception.BadLootException;
import com.github.lemerch.fillen.exception.service.logical.IgnoreFieldNotFoundException;
import com.github.lemerch.fillen.exception.service.logical.SetFieldNotFoundException;
import com.github.lemerch.fillen.exception.service.specialized.HeartException;
import com.github.lemerch.fillen.exception.user.UserDietException;
import com.github.lemerch.fillen.utils.FillenList;
import com.github.lemerch.montelo.utils.Bag;
import com.github.lemerch.montelo.utils.Generic;
import com.github.lemerch.montelo.utils.Ingredients;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * <h3>This class chew the heart of the whole application</h3>
 *
 * <p>
 *     This class essentially consists of two methods {@link Heart#dinner(Class, Context)} and {@link Heart#restedChecker(Class, Context)}
 * </p>
 */
class Heart {


    /**
     * <p>
     *     The whole process of work can be decomposed into:
     *     <ul>
     *         <li>creating an object - there must be a default constructor</li>
     *         <li>search for fields that should have a specific value (from the setFiled method)</li>
     *         <li>if the fields should not be initialized, then call custom type handlers</li>
     *         <li>checks the received value from user handlers for the {@link FillenList} type - if it is, then takes the first value and assigns it to the field</li>
     *         <li>set the value of the corresponding field</li>
     *     </ul>
     * </p>
     * <p>It takes the class type and context as input</p>
     *
     * <p>
     *     At the output, it returns an already filled object.
     * This method guarantees that all fields will be filled, i.e. in extreme cases you will get null,
     * if it should not be ignored - {@link Flight}
     * </p>
     *
     * <p>to implement his work, he uses auxiliary methods
     * <ul>
     *     <li>{@link Heart#create(Class)}</li>
     *     <li>{@link Heart#set(Class, String, Field, Object, Object)}</li>
     *     <li>{@link Heart#handle(Field, Bag)}</li>
     *     <li>{@link Heart#formatSetter(String)}</li>
     * </ul>
     * </p>
     * @param type class that need to fill
     * @param context type that allowed this method get {@link Flight#ignore(String...)}, {@link Flight#set(String, Object)} and {@link Bag}}
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
                context.settinglist.get(field.getName()).setFlag(true);
                set(type, setterName, field, invoked, context.settinglist.get(field.getName()).getVal() );
            }else if (! context.ignoringlist.containsKey(field.getName())) {
                Object result = handle(field, context.bag);
                set(type, setterName, field, invoked, result);
            }else {
                context.ignoringlist.put(field.getName(), true);
            }
        }
        return invoked;
    }

    /**
     * This method creates a class object via the default constructor
     *
     * @param type
     * @return invoked class -> object
     * @param <T>
     * @throws HeartException
     */
    private static<T> T create(Class<T> type) throws HeartException {
        try {
            return type.getConstructor().newInstance();
        }catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new HeartException("I cant find default constructor in class `" + type.getName() + "`", e);
        }
    }

    /**
     *
     * This method creates {@link Ingredients} based on field and passes
     * it to {@link Fillen.Diet} the value of which he saves in case
     * there are no later options not equal to null.
     *
     * If at the end of the crawl {@link Fillen.Diet} he gets {@link FillenList}, then he takes his first cell.
     *
     * @param field
     * @param bag
     * @return result of work {@link Fillen.Diet}
     * @throws UserDietException
     */
    private static Object handle(Field field, Bag<Fillen.Diet> bag) throws UserDietException {
        Object result = null;

        Ingredients ingredients = new Ingredients(
                field.getType(), field.getName(),
                new Generic(field.getGenericType()),
                field.getDeclaredAnnotations(),
                field.getModifiers()
        );
        for (Fillen.Diet diet : bag) {
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

    /**
     * This method will try to set the value of the field through its setter.
     *
     * @param type
     * @param setterName
     * @param field
     * @param invoked
     * @param result
     * @throws HeartException
     */
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

    /**
     * This method simply converts the field name to its setter
     * @param fieldName
     * @return method name of setter
     */
    private static String formatSetter(String fieldName) {
        return "set" + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
    }

    /**
     * This method will probably help many developers to avoid many mistakes.
     * Because it will show you exactly where you made a mistake when assigning `set` or `ignore`.
     *
     * And in case of inconsistency, he will begin an investigation - which field was not in the class
     *
     * @param type
     * @param context
     * @throws BadLootException
     */
    public static void restedChecker(Class<?> type, Context context) throws BadLootException {
        // SettingCheck

        for (String key : context.settinglist.keySet()) {
            if (!context.settinglist.get(key).isFlag())
                throw new SetFieldNotFoundException("Field `" + key + "` not found in class " + type);
        }

        // IngoreCheck
        for (String key : context.ignoringlist.keySet()) {
            if (!context.ignoringlist.get(key))
                throw new IgnoreFieldNotFoundException("Field `" + key + "` not found in class " + type);
        }
    }


}
