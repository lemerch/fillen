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

import com.github.shakal76.fillen.exception.user.GenericException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * <h3>Generic is a simple class for processing, iterating and conveniently storing data about a generic type</h3>
 * <p>
 *     This class consists essentially of a constructor and two simple methods: {@link Generic#get()}, {@link Generic#next()}.
 *     The main value here is a constructor that overwrites data about a generic type in a convenient form for subsequent processing.
 * </p>
 * <p>The constructor simply parses the value from type.getTypeName()</p>
 */
public class Generic {
    private List<Class<?>> generics;
    public Generic(Type type) throws GenericException {
        String all = type.getTypeName();
        this.generics = new ArrayList<>();
        String[] splitted = all.split("<");
        for (int i = 1; i < splitted.length; i++) {
            String className = splitted[i].replaceAll(">", "");
            try {
                this.generics.add(Class.forName(className));
            }catch (ClassNotFoundException e) {
                throw new GenericException("I cant find this class: " + className + "\n" + e.getMessage());
            }
        }
    }
    private Generic(List<Class<?>> list) {
        this.generics = list;
    }

    /**
     * @return list of generic types
     */
    public List<Class<?>> get() { return this.generics; }

    /**
     * This method create new Instance of {@link Generic}
     * transfer current list to it without first element
     *
     * @return next iteration of Generic
     * @throws GenericException when list size >= 1
     */
    public Generic next() throws GenericException {
        if (this.generics.size() >= 1) {
            List<Class<?>> newlist = new ArrayList<>(this.generics.size());
            for (int i = 1; i < this.generics.size(); i++) newlist.add(this.generics.get(i));
            return new Generic(newlist);
        }else {
            throw new GenericException("generic next iteration cannot be processed because it last iteration");
        }
    }
}
