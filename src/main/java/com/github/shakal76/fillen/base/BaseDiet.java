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
package com.github.shakal76.fillen.base;

import com.github.shakal76.fillen.Fillen;
import com.github.shakal76.fillen.Ingredients;
import com.github.shakal76.fillen.exception.UserDietException;

import java.lang.reflect.Array;
import java.util.*;

/**
 * This is base 'menu' that can be overwriten by your own Fillen.Diet
 */
public class BaseDiet {
    public Fillen.Diet diet = new Fillen.Diet() {
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
            }else if(isTypesEquals(ingredients.type, List.class)) {
                List<Object> result = new ArrayList<>();

                List<Class<?>> gen = ingredients.generic.get();
                List<Object> last = new ArrayList<>();
                for (int i = 0; i < gen.size(); i++) {
                    if (gen.get(i).isAssignableFrom(List.class)) {
                        if (i == 0) {
                            result.add(last);
                        }else {
                            List<Object> temp = new ArrayList<>();
                            last.add(temp);
                        }
                    }else {
                        last.add(callback(ingredients.setType(gen.get(i))));
                    }
                }

                return result;
            }else if(ingredients.type.isArray()) {

                // WARNING FOR DEVELOPERS:
                //                         LAST CELL OF getListArray's LIST
                //                         WILL BE SEEM AS SIMPLE TYPE, BUT YOU MUST CREATE NEW ARRAY INSTANCE FOR IT
                List<Class<?>> list = getListArray(ingredients.type);

                Object obj = callback(ingredients.setType(list.get(list.size()-1)));

                Object result = null;
                if (list.size() == 1) {
                    result = arrayRules(obj, list.get(list.size()-1));
                }else if(list.size() == 2) {
                    Object temp = Array.newInstance(list.get(0), 1);
                    Object last = arrayRules(obj, list.get(list.size()-1));
                    Array.set(temp, 0, last);
                    result = temp;
                }else {
                    Object root = Array.newInstance(list.get(0), 1);
                    Object previous = Array.newInstance(list.get(1), 1);
                    for (int i = 1; i < list.size()-1; i++) {
                        if (i == 1) {
                            Array.set(root, 0, previous);
                        }else {
                            Object temp = Array.newInstance(list.get(i), 1);
                            Array.set(previous, 0, temp);
                            previous = temp;
                        }
                    }
                    Object last = arrayRules(obj, list.get(list.size()-1));
                    Array.set(previous, 0, last);
                    result = root;
                }

                return result;
            }else {
                return null;
            }
        }
    };

    public Object arrayRules(Object fromCallback, Class<?> targetType) {
        if (fromCallback.getClass().isAssignableFrom(ArrayList.class)) {
            List<?> values = (List<?>) fromCallback;

            Object finals = Array.newInstance(targetType, values.size());

            for (int j = 0; j < values.size(); j++) {
                Array.set(finals, j, values.get(j));
            }
            return finals;
        }else {
            Object finals = Array.newInstance(targetType, 1);
            Array.set(finals, 0, fromCallback);
            return finals;
        }
    }
}