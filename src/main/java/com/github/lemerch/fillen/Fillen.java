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
package com.github.lemerch.fillen;

import com.github.lemerch.fillen.exception.BadLootException;
import com.github.lemerch.fillen.exception.user.DietAlreadyExistException;
import com.github.lemerch.fillen.exception.user.UserDietException;
import com.github.lemerch.montelo.Montelo;
import com.github.lemerch.montelo.exception.UserException;
import com.github.lemerch.montelo.utils.Bag;
import com.github.lemerch.montelo.utils.Ingredients;

import java.util.*;

/**
 * <h3>Fillen class is API of this project</h3>
 * <p>You can use this class for filling your class's fields. Just use:
 *
 * <blockquote><pre>
 *     {@code new Fillen().dinner(MyClass.class);}
 * </pre></blockquote>
 * it will return your filling and prepared object. You can also manipulate
 * with fillen behaviour. Just example you have such methods as:
 *  <ul>
 *      <li>{@link Fillen#ignore(String...)}</li>
 *      <li>{@link Fillen#set(String, Object)}</li>
 *  </ul>
 *
 *  And of course you have such instrument as:
 *  {@link Fillen.Diet}
 *
 *  With it, you can process your new types or redefine the logic of filling in existing ones.
 * </p>
 *
 * <p></p>
 *
 * <h3>For Developers</h3>
 * <p>I have divided the API into 4 semantic blocks
 *  <ul>
 *      <li>CONFIGURE - this block is a group of constructors that accept
 *      {@link Bag}
 *      or it component {@link Diet}</li>
 *
 *      <li>INTERMEDIATE HANDLERS - this block consists of 2 methods:
 *          {@link Fillen#ignore(String...)}
 *          {@link Fillen#set(String, Object)}
 *          for additional configuration
 *      and returns {@link Flight}</li>
 *
 *      <li>PERFERMER - this is the most important block, it is he who acts as a service in this application.
 *      BUT. before calling {@link Heart#dinner(Class, Context)} he should provide everyone {@link Fillen.Diet} clone of {@link Fillen#context}.
 *      After processing dinner clone of context will be set to null</li>
 *
 *      <li>UTILS - it consists of one {@link Fillen.Diet}, but despite this, it is no less important especially for users</li>
 *  </ul>
 * </p>
 */
public final class Fillen {
    /**
     * This is a simple constant that setting in constructor at once
     * See more about this type here - {@link Context}
     */
    private final Context context = new Context();

    // CONFIGURE
    public Fillen(Bag<Diet> bag) {
        this.context.bag = bag;
    }
    public Fillen(Diet... diets) throws DietAlreadyExistException {
        for (Diet diet : diets) {
            this.context.bag.add(diet);
        }
    }


    // INTERMEDIATE HANDLERS

    /**
     * This method create new instance of {@link Flight} class, his constructor has default modifier.
     * This is necessary because this class cannot be NOT part of {@link Fillen}.
     *
     * We pass the context to its constructor and call the {@link Flight#ignore} method of {@link Flight},
     * which will return itself.
     *
     * What is important is that we pass it exactly the cloned {@link Fillen#context}, so that when creating multiple {@link Flight},
     * they do not refer to the same context.
     *
     * @param fieldNames
     * @return {@link Flight}
     */
    public Flight ignore(String... fieldNames) {
        Flight flight = new Flight(this.context.clone());
        return flight.ignore(fieldNames);
    }

    /**
     * This method create new instance of {@link Flight} class, his constructor has default modifier.
     * This is necessary because this class cannot be NOT part of {@link Fillen}.
     *
     * We pass the context to its constructor and call the {@link Flight#set(String, Object)} method of {@link Flight},
     * which will return itself.
     *
     * What is important is that we pass it exactly the cloned {@link Fillen#context}, so that when creating multiple {@link Flight},
     * they do not refer to the same context.
     *
     * @param fieldName
     * @param value
     * @return {@link Flight}
     * @param <T>
     */
    public<T> Flight set(String fieldName, T value) {
        Flight flight = new Flight(this.context.clone());
        return flight.set(fieldName, value);
    }

    // PERFERMER

    /**
     * This is probably the most important method for our api.
     * It clones the context for Diets and Heart.
     *
     * This is necessary so that each call to this method works with
     * its own instance of the context and does not change each other's values,
     * for example, if they were run in parallel.
     *
     * At the end of his work, he just cleans it up and returns the finished object.
     *
     * @param type
     * @return prepared Object
     * @param <T>
     * @throws BadLootException
     */
    public<T> T dinner(Class<T> type) throws BadLootException {
        Context ctx = this.context.clone();
        for (Fillen.Diet diet : context.bag) {
            diet.context = this.context;
        }
        T obj = Heart.dinner(type, ctx);
        Heart.restedChecker(type, ctx);
        ctx = null;
        return obj;
    }

    // UTILS

    /**
     * <h3>Fillen.Diet abstract class is instrument for users type controll</h3>
     * <p>You can realize this class and put it into {@link Fillen} constructor
     *  Even if you process already processed types, your types will still override them.
     * </p>
     *
     * <p>For an example, you can see it <a href="https://github.com/shakal76/fillen/blob/main/src/test/java/com/github/shakal76/fillen/examples">here</a>
     *
     * <p></p>
     *
     * <h3>For Developers</h3>
     * <p>As in the case of {@link Fillen}, I have divided this class into several semantic block, such as:</p>
     *
     * <ul>
     *      <li>BUILT-IN METHODS - this is an entertaining block that will make it easier for you to work with reflection.
     *      It contains several small but very important methods that you can use only when implementing this class.
     *      There are such methods as:
     *          <ul>
     *              <li>{@link Fillen.Diet#seeback(Ingredients)}</li>
     *              <li>{@link Fillen.Diet#dinner(Class)}</li>
     *              <li>{@link Fillen.Diet#connector(Ingredients, Montelo...)}</li>
     *              <li>{@link Fillen.Diet#isTypesEqual(Class, Class)}</li>
     *              <li>{@link Fillen.Diet#getAllInterfaces(Class)}</li>
     *           </ul>
     *      </li>
     *
     *      <li>TARGET - there is only one method in this block.
     *      The same non-implemented abstract method that I hope you will be able to implement easily :)</li>
     * </ul>
     */
    public static abstract class Diet extends Montelo {
        Context context;


        // BUILT-IN METHODS

        /**
         * <h3>Warrning - may lead to infinite recursion</h3>
         *
         * This method calls all diets from a local context.
         * This method is suitable for recursive work with the type.
         *
         * At the moment it is used for processing lists, arrays and other containers.
         *
         * @param ingredients
         * @return result of diets
         * @throws UserDietException
         */
        protected Object seeback(Ingredients ingredients) throws UserException {
            Object result = null;
            System.out.println(context);
            for (Diet diet : context.bag) {
                Object timed = diet.menu(ingredients);
                if (timed != null) {
                    result = timed;
                }
            }
            return result;
        }

        /**
         * This method is needed to process classes whose fields need to be filled in according
         * to the CURRENT (the one in which your diet is currently working) diet context
         * (that is, all `ignore` and `set` will be taken into account)
         *
         * @param type
         * @return filled Object
         * @throws UserDietException
         */
        protected Object dinner(Class<?> type) throws UserException {
            try {
                return Heart.dinner(type, this.context);
            }catch (BadLootException e) {
                throw new UserException(e);
            }
        }


    }

}
