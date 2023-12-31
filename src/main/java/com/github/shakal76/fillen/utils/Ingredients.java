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
package com.github.shakal76.fillen.utils;

import com.github.shakal76.fillen.Fillen;

import java.lang.annotation.Annotation;

/**
 * <h3>This class is a simple IMMUTABLE container for transport information about field that Heart.dinner want to fill into Fillen.Diet.menu(...)</h3>
 *
 * <p>It contains:
 *  <ul>
 *     <li>field type</li>
 *     <li>field name</li>
 *     <li>field generic</li>
 *     <li>field declaredAnnotation</li>
 *     <li>field modifier</li>
 *  </ul>
 * </p>
 *
 * <p>
 *     Its peculiarity lies in the fact that its fields are public,
 *     but they are not changeable,
 *     since each setter creates a new object - this is done for the convenience and security of user handlers in {@link Fillen.Diet}
 * </p>
 */
public final class Ingredients {
    public final Class<?> type;
    public final String name;
    public final Generic generic;
    public final Annotation[] declaredAnnotations;
    public final int modifier;

    public Ingredients(Class<?> type, String name, Generic generic, Annotation[] declaredAnnotations, int modifier) {
        this.type = type;
        this.name = name;
        this.generic = generic;
        this.declaredAnnotations = declaredAnnotations;
        this.modifier = modifier;
    }

    public Ingredients setType(Class<?> type) {
        return new Ingredients(type, name, generic, declaredAnnotations, modifier);
    }
    public Ingredients setName(String name) {
        return new Ingredients(type, name, generic, declaredAnnotations, modifier);
    }
    public Ingredients setGeneric(Generic generic) {
        return new Ingredients(type, name, generic, declaredAnnotations, modifier);
    }
    public Ingredients setDeclredAnnotations(Annotation[] declaredAnnotations) {
        return new Ingredients(type, name, generic, declaredAnnotations, modifier);
    }
    public Ingredients setModifier(int modifier) {
        return new Ingredients(type, name, generic, declaredAnnotations, modifier);
    }

}
