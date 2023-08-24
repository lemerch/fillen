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
import com.github.shakal76.fillen.utils.FillenList;

import java.lang.reflect.Array;
import java.util.*;

/**
 * <h3>BaseDiet is a diet to support basic data types</h3>
 * <p>
 *  This is a standard diet for our owl, its essence is to support the following types:
 *  <ul>
 *      <li>String</li>
 *      <li>byte</li>
 *      <li>short</li>
 *      <li>int</li>
 *      <li>long</li>
 *      <li>float</li>
 *      <li>double</li>
 *      <li>List</li>
 *      <li>arrays</li>
 *  </ul>
 *  the value of which is random, except list and array.
 *  List and array work according to the following logic:
 *  if it is a container, then we find out the nesting and generate it until we reach the final type,
 *  and then we check whether it is a {@link FillenList},
 *  if so, then we take all its elements and put them in their last list,
 *  otherwise just the container value in the last container.
 *
 *  To simplify such logic, there is a {@link com.github.shakal76.fillen.utils.Generic} class for working with generic types, which you can iteratively go through.
 *
 *  And for simple arrays there is a built-in method in {@link Fillen.Diet} - getListArray - which also converts a sequence of arrays into a list,
 *  which will also be convenient for iterating and creating similar logic.
 * </p>
 *
 * <p></p>
 *
 * <h4>NOTICE</h4>
 * <p>
 *  the last element of the resulting list from the getListArray method is the final type, however,
 *  if you create primitive arrays through reflection, then keep in mind that a new class must also be created for this last element.
 *  See the implementation example before making your own container handler.
 *
 * </p>
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
                        Object obj = callback(ingredients.setType(gen.get(i)));
                        // list Rules
                        if (obj != null && obj.getClass().isAssignableFrom(FillenList.class)) {
                            FillenList<Object> userList = (FillenList) obj;
                            last.addAll(userList.getList());
                        }else {
                            last.add(obj);
                        }
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
            }
                return null;
        }
    };

    public Object arrayRules(Object fromCallback, Class<?> targetType) {
        if (fromCallback != null && fromCallback.getClass().isAssignableFrom(FillenList.class)) {
            List<Object> values = ((FillenList)fromCallback).getList();

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