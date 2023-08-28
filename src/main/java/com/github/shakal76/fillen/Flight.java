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
import com.github.shakal76.fillen.exception.service.logical.SetOrIgnoreConflictException;

/**
 * <h3>Flight class is the part of API - {@link Fillen}</h3>
 *
 * <p>
 *  This class is not much different from {@link Fillen},
 *  but it is through it that you can manipulate the methods:
 *  <ul>
 *      <li>{@link Flight#ignore(String...)}</li>
 *      <li>{@link Flight#set(String, Object)}</li>
 *  </ul>
 *
 *  Why do we need a separate class for this? - The answer is mobility.
 *  </p>
 *
 *  <p>
 *  You can make separate configurations with Flight with certain settings while having the same
 *  {@link Fillen} instance with its custom {@link Fillen.Diet} handlers
 * </p>
 */
public class Flight {
    private final Context context;

    Flight(Context context) {
        this.context = context;
    }

    /**
     * This method fills in the list {@link Context#ignoringlist}
     *
     * @param fieldNames
     * @return its instance
     */
    public Flight ignore(String... fieldNames) {
        for (String field : fieldNames) {
            this.context.ignoringlist.add(field);
        }
        return this;
    }

    /**
     * This method fills in the map {@link Context#settinglist}
     *
     * @param fieldName
     * @param value
     * @return its instance
     * @param <T>
     */
    public<T> Flight set(String fieldName, T value) {
        this.context.settinglist.put(fieldName, value);
        return this;
    }

    /**
     * Unlike {@link Fillen#dinner(Class)}, this method checks
     * for matching field names between {@link Context#ignoringlist} and {@link Context#settinglist}
     *
     * @param type
     * @return filled Object
     * @param <T>
     * @throws SetOrIgnoreConflictException when conflict between set and ignore exist
     * @throws BadLootException
     */
    public<T> T dinner(Class<T> type) throws BadLootException {
        for (String s : context.ignoringlist) {
            if (context.settinglist.containsKey(s)) {
                throw new SetOrIgnoreConflictException("ignore field `" + s + "` equals set value");
            }
        }
        Context ctx = this.context.clone();
        for (Fillen.Diet diet : context.bag.get()) {
            diet.context = this.context;
        }
        T obj = Heart.dinner(type, ctx);
        Heart.restedChecker(type, ctx);
        ctx = null;
        return obj;
    }

}
