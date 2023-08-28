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

import java.util.UUID;

/**
 * <h3>This is a simple `diet` that sets random values to simple types, shells, and primitives</h3>
 */
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
        }else if(isTypesEquals(ingredients.type, boolean.class) || isTypesEquals(ingredients.type, Boolean.class)) {
            return Math.random() < 0.5;
        }else if(isTypesEquals(ingredients.type, char.class) || isTypesEquals(ingredients.type, Character.class)) {
            return ((int) (Math.random() * 52) < 26) ? 'A' : 'a';
        }
        return null;
    }
}
