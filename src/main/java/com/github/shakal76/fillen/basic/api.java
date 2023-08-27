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

// base api
public final class api {
    public static final Fillen.Diet base = new Fillen.Diet() {
        @Override
        public Object menu(Ingredients ingredients) throws UserDietException {
            return connector(ingredients, new RandomDiet(), new CollectionDiet(), new ArrayDiet());
        }
    };
}