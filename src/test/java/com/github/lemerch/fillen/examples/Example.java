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
package com.github.lemerch.fillen.examples;

import com.github.lemerch.fillen.examples.beans.FirstBean;
import com.github.lemerch.fillen.examples.beans.SecondBean;
import com.github.lemerch.fillen.Fillen;
import com.github.lemerch.fillen.Flight;
import com.github.lemerch.fillen.exception.BadLootException;
import com.github.lemerch.fillen.exception.user.UserDietException;
import com.github.lemerch.montelo.exception.UserException;
import com.github.lemerch.montelo.utils.Bag;
import com.github.lemerch.montelo.utils.Ingredients;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.github.lemerch.fillen.basic.api.base;

public class Example {

    @Test
    public void firstExample() throws BadLootException {

        Fillen.Diet diet = new Fillen.Diet() {
            @Override
            public Object menu(Ingredients ingredients) throws UserException {
                if (isTypesEqual(ingredients.type, SecondBean.class)) {
                    return dinner(ingredients.type);
                }

                // ye, its safety :))
                return null;
            }
        };

        Fillen fillen = new Fillen(base, diet);

        FirstBean first = fillen.dinner(FirstBean.class);

        System.out.println("\nFIRST RESULT:\n");
        System.out.println("text: " + Arrays.deepToString(first.getText()));
        System.out.println("number: " + first.getNumber());
        System.out.println("list: " + first.getList().get(0).poll().getA());
        System.out.println("decimal: " + first.getDecimal());
    }

    @Test
    public void secondExample() throws BadLootException {

        Fillen.Diet diet = new Fillen.Diet() {
            @Override
            public Object menu(Ingredients ingredients) throws UserException {
                if (isTypesEqual(ingredients.type, BigDecimal.class)) {
                    return new BigDecimal(123);
                }else if (isTypesEqual(ingredients.type, SecondBean.class)) {
                    return dinner(ingredients.type);
                }

                // ye, its safety :))
                return null;
            }
        };

        Bag<Fillen.Diet> bag = new Bag<>();
        bag.add(diet);
        bag.add(base);

        Fillen fillen = new Fillen(bag);

        Flight flight = fillen.ignore("text").set("number", 25);

        // or just ExamplePojo first = fillen.ignore("text").set("number", 25).dinner(Example.class);

        FirstBean first = flight.dinner(FirstBean.class);

        System.out.println("-----------------\nSECOND RESULT:\n");
        System.out.println("text: " + Arrays.deepToString(first.getText()));
        System.out.println("number: " + first.getNumber());
        System.out.println("list: " + first.getList().get(0).get(0).getC());
        System.out.println("decimal: " + first.getDecimal());
    }

}
