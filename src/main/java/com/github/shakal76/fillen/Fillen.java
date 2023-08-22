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
import java.util.*;

/**
 * <h3>Fillen class is api of this project</h3>
 * <p>
 *     You can use it for filling your class's fields.
 * </p>
 * <p>
 *     Just use: {@code new Fillen().dinner(YourClass.class)}
 * </p>
 */

// TODO: arrays test
public class Fillen {
    private final Context context = new Context();

    // CONFIGURE
    public Fillen(Bag bag) {
        this.context.bag = bag;
    }
    public Fillen(Fillen.Diet... diets) {
        for (Diet diet : diets) {
            this.context.bag.put(diet);
        }
    }


    // INTERMEDIATE HANDLERS
    public Flight ignoreFields(String... fieldNames) {
        Flight flight = new Flight(this.context);
        return flight.ignoreFields(fieldNames);
    }
    public<T> Flight setField(String fieldName, T value) {
        Flight flight = new Flight(this.context);
        return flight.setField(fieldName, value);
    }

    // PERFERMER


    public<T> T dinner(Class<T> type) throws BadLootException {
        for (Fillen.Diet diet : this.context.bag.get()) {
            diet.context = this.context;
        }
        return Heart.dinner(type, this.context);
    }

    // UTILS

    public abstract static class Diet {
        Context context;
        private Priority priority = Priority.LOW;

        public Priority getPriority() {
            return priority;
        }
        public Diet setPriority(Priority priority) {
            this.priority = priority;
            return this;
        }

        // BE CAREFULLY WITH IT BECAUSE OF RECURSION
        protected Object callback(Ingredients ingredients) throws BadLootException {
            if (context == null) {
                throw new BadLootException("haven't context");
            }
            Object result = null;
            for (Fillen.Diet diet : context.bag.get()) {
                Object timed = diet.menu(ingredients);
                if (timed != null) {
                    result = timed;
                    if (diet.getPriority().equals(Priority.HIGH)) break;
                }
            }
            return result;
        }
        protected Object heart(Class<?> type) throws BadLootException {
            if (context == null) {
                throw new BadLootException("haven't context");
            }
            return Heart.dinner(type, context);
        }
        protected Boolean isTypesEquals(Class<?> one, Class<?> two) {
            return two.isAssignableFrom(one);
        }
        protected List<Class<?>> getListArray(Class<?> arr) {
            List<Class<?>> array = new ArrayList<>();
            if (arr.isArray()) {
                Class<?> currentType = arr.getComponentType();
                while (currentType.isArray()) {
                    array.add(currentType);
                    currentType = currentType.getComponentType();
                }
                array.add(currentType);
                return array;
            }else {
                return null;
            }
        }
        public abstract Object menu(Ingredients ingredients) throws BadLootException;

    }

}
