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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>This is a simple container for transferring some structures between
 * api -> dinner,  diet -> dinner, {@link Fillen} -> {@link Flight}</h3>
 * <p>You can learn more about these structures here
 *  <ul>
 *      <li>{@link Fillen}</li>
 *      <li>{@link Flight}</li>
 *      <li>{@link Fillen.Diet}</li>
 *  </ul>
 * </p>
 */
class Context {

    /**
     * simple List< String >
     */
    public List<String> ignoringlist = new ArrayList<>();
    /**
     * simle map< String, Object >
     */
    public Map<String, Object> settinglist = new HashMap<>();

    /**
     * local bag - for more info see {@link Bag}
     */
    public Bag bag = new Bag();

    /**
     * settingCounter - will count every count in heart#dinner when the field has actually been fixed. 
     * This is necessary for post-processing the presence of an error when specifying the appropriate parameters.
     * {@link Heart#restedChecker(Class, Context)}
     */
    public int settingCounter = 0;
    /**
     * settingCounter - will count every count in heart#dinner when the field has actually been fixed.
     * This is necessary for post-processing the presence of an error when specifying the appropriate parameters.
     * {@link Heart#restedChecker(Class, Context)}
     */
    public int ignoreCounter = 0;


    /**
     * @return cloned Context
     */
    public Context clone() {
        Context ctx = new Context();
        ctx.bag = this.bag.clone();
        ctx.settinglist = new HashMap<>(this.settinglist);
        ctx.ignoringlist = new ArrayList<>(this.ignoringlist);

        ctx.settingCounter = this.settingCounter;
        ctx.ignoreCounter = this.ignoreCounter;
        return ctx;
    }

}
