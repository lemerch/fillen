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

import com.github.shakal76.fillen.exception.user.DietAlreadyExistException;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>Bag is a simple container for {@link Fillen.Diet} objects</h3>
 * <p>Its peculiarity is that it adds a basic type handler to its "bag".</p>
 * <p>Despite the fact that a List is used inside to store `diets`, the types of these `diets` must be unique, as for example in Set</p>
 */
public class Bag {

    private List<Fillen.Diet> bag = new ArrayList<>();

    public Bag() {}

    /**
     * Adds a new {@link Fillen.Diet} to the list by checking for its presence in the list
     *
     * @param diet
     * @return true when it was added successfully
     * @throws DietAlreadyExistException
     */
    public boolean add(Fillen.Diet diet) throws DietAlreadyExistException {
        if (get(diet.getClass()) == null) {
            return this.bag.add(diet);
        }else {
            throw new DietAlreadyExistException("Diet `" + diet.getClass() + "` already exist in this bag");
        }
    }

    /**
     * Removes by index in list
     *
     * @param index
     * @return true when successfully
     */
    public Fillen.Diet remove(int index) { return this.bag.remove(index); }

    /**
     * Removes a {@link Fillen.Diet} in the list by checking for its presence in the list
     * @param associated
     * @return
     */
    public Fillen.Diet remove(Class<? extends Fillen.Diet> associated) {
        int index = 0;
        for (Fillen.Diet diet : bag) {
            Class<Fillen.Diet> an = (Class<Fillen.Diet>) diet.getClass();
            if (associated.getName() == an.getName() && associated.getPackageName() == an.getPackageName()) {
                return remove(index);
            }
            index++;
        }
        return null;
    }

    /**
     * @return inner list
     */
    public List<Fillen.Diet> get() {
        return this.bag;
    }

    /**
     * @param index
     * @return `diet` by index from inner list
     */
    public Fillen.Diet get(int index) {
        return this.bag.get(index);
    }

    /**
     * @param associated
     * @return `diet` by it signrature (className, packageName)
     */
    public Fillen.Diet get(Class<? extends Fillen.Diet> associated) {
        for (Fillen.Diet diet : bag) {
            Class<Fillen.Diet> an = (Class<Fillen.Diet>) diet.getClass();
            if (associated.getName() == an.getName() && associated.getPackageName() == an.getPackageName()) {
                return diet;
            }
        }
        return null;
    }
    public void addAll(Bag bag) { this.bag.addAll(bag.get()); }

    private void setinner(List<Fillen.Diet> list) {
        this.bag = list;
    }

    /**
     * @return cloned Bag
     */
    public Bag clone() {
        Bag bag = new Bag();
        bag.setinner(new ArrayList<>(this.bag));
        return bag;
    }

}
