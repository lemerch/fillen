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
import com.github.shakal76.fillen.utils.FillenList;

/**
 * This is simple wrapper class for it fied {@link api#base}
 */
public final class api {
    /**
     * <h3>basic.api is a connector of standard diets to support basic data types</h3>
     * <p>It connect:
     * <ul>
     *     <li>{@link ArrayDiet}</li>
     *     <li>{@link CollectionDiet}</li>
     *     <li>{@link RandomDiet}</li>
     * </ul>
     * </p>
     *
     * <p>
     *  its essence is to support the following types:
     *  <ul>
     *      <li>String</li>
     *      <li>byte</li>
     *      <li>short</li>
     *      <li>int</li>
     *      <li>long</li>
     *      <li>float</li>
     *      <li>double</li>
     *      <li>char</li>
     *      <li>boolean</li>
     *
     *      <li>List</li>
     *      <li>Set</li>
     *      <li>Queue</li>
     *
     *      <li>Collection Implementations</li>
     *
     *      <li>arrays</li>
     *  </ul>
     *  the value of which is random, except list and array.
     *  Container types work according to the following logic:
     *  if it is a container, then we find out the nesting and generate it until we reach the final type,
     *  and then we check whether it is a {@link FillenList},
     *  if so, then we take all its elements and put them in their last list,
     *  otherwise just the container value in the last container.
     *
     *  To simplify such logic, there is a {@link com.github.shakal76.fillen.utils.Generic} class for working with generic types, which you can iteratively go through.
     * </p>
     */
    public static final Fillen.Diet base = new Fillen.Diet() {
        @Override
        public Object menu(Ingredients ingredients) throws UserDietException {
            return connector(ingredients, new RandomDiet(), new CollectionDiet(), new ArrayDiet());
        }
    };
}