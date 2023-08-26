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

import com.github.shakal76.fillen.enums.Priority;
import com.github.shakal76.fillen.exception.BadLootException;
import com.github.shakal76.fillen.exception.IgnoreFieldNotFoundException;
import com.github.shakal76.fillen.exception.SetFieldNotFoundException;
import com.github.shakal76.fillen.utils.FillenList;
import com.github.shakal76.fillen.utils.Generic;

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
// TODO: add exception to setField
// TODO: add info about setters into readme and javadoc here
// TODO: create more effective method to get Setter name from Fieldname
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


        T invoked;
        try {
            invoked = type.getConstructor().newInstance();
        }catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new BadLootException("I cant find default constructor in `" + type.getName() + "`\n" + e.getMessage());
        }


        for (Field field : type.getDeclaredFields()) {
            String setterName = "set" + field.getName().toUpperCase().charAt(0) + field.getName().substring(1);

            if (context.settinglist.containsKey(field.getName())) {

                context.settingCounter+=1;

                try {
                    type.getDeclaredMethod(setterName, field.getType()).invoke(invoked, context.settinglist.get(field.getName()));
                }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new BadLootException("I cant set value into through the setter of field `" + field.getName() +
                            "` in class " + type + "\n" + e.getMessage());
                }
            }else if (! context.ignoringlist.contains(field.getName())) {

                try {
                    Object result = null;

                    Ingredients ingredients = new Ingredients(
                            field.getType(), field.getName(),
                            new Generic(field.getGenericType()),
                            field.getDeclaredAnnotations(),
                            field.getModifiers()
                    );
                    for (Fillen.Diet diet : context.bag.get()) {
                        Object timed = diet.menu(ingredients);
                        if (timed != null) {
                            if (timed.getClass().isAssignableFrom(FillenList.class)) {
                                result = diet.fillenListHandler((FillenList) timed);
                            }else {
                                result = timed;
                            }
                            if (diet.getPriority().equals(Priority.HIGH)) break;
                        }
                    }
                    type.getDeclaredMethod(setterName, field.getType()).invoke(invoked, result);
                }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new BadLootException("I cant set value through the setter of field `" + field.getName() +
                            "` in class " + type + "\n" + e.getMessage());
                }catch (IllegalArgumentException e) {
                    throw new BadLootException("Incorrect type of result in field `" + field.getName() +
                            "` in class " + type + "\n" + e.getMessage());
                }
            }else {
                context.ignoreCounter+=1;
            }
        }
        return invoked;
    }

    public static void restChecker(Class<?> type, Context context) throws BadLootException {
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
