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
package com.github.lemerch.fillen.basic;

import com.github.lemerch.fillen.exception.user.UserDietException;
import com.github.lemerch.fillen.Fillen;
import com.github.lemerch.montelo.exception.UserException;
import com.github.lemerch.montelo.utils.Ingredients;

import java.util.UUID;

/**
 * <h3>This is a simple `diet` that sets random values to simple types, shells, and primitives</h3>
 */
public class RandomDiet extends Fillen.Diet {
    @Override
    public Object menu(Ingredients ingredients) throws UserException {
        if(isTypesEqual(ingredients.type, String.class)) {
            return UUID.randomUUID().toString().replaceAll("-", "");
        }else if(isTypesEqual(ingredients.type, byte.class) || isTypesEqual(ingredients.type, Byte.class)) {
            return 5 + (byte)(Math.random() * ((10 - 5) + 1));
        }else if(isTypesEqual(ingredients.type, short.class) || isTypesEqual(ingredients.type, Short.class)) {
            return 5 + (short)(Math.random() * ((10 - 5) + 1));
        }else if(isTypesEqual(ingredients.type, int.class) || isTypesEqual(ingredients.type, Integer.class)) {
            return 5 + (int)(Math.random() * ((10 - 5) + 1));
        }else if(isTypesEqual(ingredients.type, long.class) || isTypesEqual(ingredients.type, Long.class)) {
            return 5 + (long)(Math.random() * ((10 - 5) + 1));
        }else if(isTypesEqual(ingredients.type, Float.class) || isTypesEqual(ingredients.type, Float.class)) {
            return 5 + (float)(Math.random() * ((10 - 5) + 1));
        }else if(isTypesEqual(ingredients.type, double.class) || isTypesEqual(ingredients.type, Double.class)) {
            return 5 + (Math.random() * ((10 - 5) + 1));
        }else if(isTypesEqual(ingredients.type, boolean.class) || isTypesEqual(ingredients.type, Boolean.class)) {
            return Math.random() < 0.5;
        }else if(isTypesEqual(ingredients.type, char.class) || isTypesEqual(ingredients.type, Character.class)) {
            return ((int) (Math.random() * 52) < 26) ? 'A' : 'a';
        }
        return null;
    }
}
