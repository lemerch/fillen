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
import com.github.shakal76.fillen.utils.Generic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Heart is default class just for using static method {@code dinner}
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


        T invoked;
        try {
            invoked = type.getConstructor().newInstance();
        }catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new BadLootException("I cant find default constructor in " + type.getName() + "\n" + e.getMessage());
        }

        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            String filedName = field.getName();
            if (context.settinglist.containsKey(filedName)) {
                try {
                    field.set(invoked, context.settinglist.get(filedName));
                }catch (IllegalAccessException e) {
                    throw new BadLootException("I cant set value into field: " + field.getName() +
                            ";\nin class: " + type + "\n" + e.getMessage());
                }
            }else if (! context.ignoringlist.contains(filedName)) {
                try {
                    Object result = null;

                    Ingredients ingredients = new Ingredients(
                            field.getType(), field.getName(),
                            new Generic(field.getGenericType()),
                            field.getDeclaredAnnotations(),
                            field.getModifiers()
                    );
                    for (Fillen.Diet diet : context.bag.get()) {
                        Object temp = diet.menu(ingredients);
                        if (temp != null) {
                            result = temp;
                        }
                    }
                    field.set(invoked, result);
                }catch (IllegalAccessException e) {
                    throw new BadLootException("I cant set value into field: " + field.getName() +
                            ";\nin class: " + type + "\n" + e.getMessage());
                }
            }
            field.setAccessible(false);
        }

        return invoked;
    }
}
