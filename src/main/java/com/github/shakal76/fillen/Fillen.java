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
import com.github.shakal76.fillen.exception.UserDietException;

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
 *      <li><blockquote>{@code ingoreFields(String... fields)}</blockquote></li>
 *      <li><blockquote>{@code setField(String name, Object value)}</blockquote></li>
 *  </ul>
 *
 *  And of course you have such instrument as:
 *  <blockquote><pre>{@code abstract static class Fillen.Diet; }</pre></blockquote>
 *
 *  With it, you can process your new types or redefine the logic of filling in existing ones.
 *  For more information, see {@link Fillen.Diet}
 * </p>
 *
 * <p></p>
 *
 * <h3>For Developers</h3>
 * <p>I have divided the API into 4 semantic blocks
 *  <ul>
 *      <li>CONFIGURE - this block is a group of constructors that accept
 *      {@link Bag}
 *      or it component {@link Fillen.Diet}</li>
 *
 *      <li>INTERMEDIATE HANDLERS - this block consists of 2 methods:
 *          <blockquote><pre>{@code ingoreFields(String... fields)}</pre></blockquote>
 *          <blockquote><pre>{@code setField(String name, Object value)}</pre></blockquote>
 *          for additional configuration
 *      and returns {@link Flight}</li>
 *
 *      <li>PERFERMER - this is the most important block, it is he who acts as a service in this application.
 *      BUT. before calling {@code Heart.dinner(...))} he should provide everyone with a {@code Fillen.Diet} in {@code context.bag} this very context</li>
 *
 *      <li>UTILS - it consists of one {@code abstract static class Fillen.Diet; }, but despite this, it is no less important especially for users
 *      For more information about this class read here {@link Fillen.Diet}</li>
 *  </ul>
 * </p>
 */
// TODO: increase library security through encapsulation
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

    /**
     * <h3>Fillen.Diet abstract class is instrument for users type controll</h3>
     * <p>You can realize this class and put it into {@link Fillen} constructor
     *  Even if you process already processed types, your types will still override them.
     *  BUT! If you need a 100% guarantee that it is your type processing logic that will work with fields of the corresponding type,
     *  then increase its priority to HIGH via <blockquote><pre>{@code Fillen.Diet().setPriority(Priority.HIGH);}</pre></blockquote>
     * </p>
     *
     * <p>For an example, you can see it <a href="https://github.com/shakal76/fillen/blob/main/src/test/java/com/github/shakal76/fillen/ExampleTest.java">here</a>
     *
     * <p></p>
     * <h3>IMPORTANT</h3>
     * <p>When you put your menus in a bag at Priority.LOW of each of them - priority in conflicts will be given to the last non-null</p>
     *
     * <p></p>
     *
     * <h3>For Developers</h3>
     * <p>As in the case of {@link Fillen}, I have divided this class into several semantic block, such as:</p>
     *
     * <ul>
     *      <li>CONFIGURE - this block is a simple private field of {@link Priority} type and its setter with getter.
     *      Pay attention to the setter, it returns its own object, so you can assign priority to it immediately after creating the menu</li>
     *
     *      <li>BUILT-IN METHODS - this is an entertaining block that will make it easier for you to work with reflection.
     *      It contains several small but very important methods that you can use only when implementing this class.
     *      There are such methods as:
     *          <ul>
     *              <li>callback - this method will allow you to return to the previous {@code Fillen.diet.menu(...)}
     *              for processing nested classes (currently used in {@link com.github.shakal76.fillen.base.BaseDiet}
     *              for structures such as List and primitive arrays)
     *
     *              <p></p>
     *              <h4>WARNING</h4>
     *              this method should be used quite carefully because it can cause recursion and then StackOverflowException</li>
     *              <p></p>
     *
     *              <li>dinner - this method will allow you to call {@link Heart}.dinner while passing you the current context.
     *              This method is very useful if you have a field that is also a class that needs to be filled in.</li>
     *
     *              <p></p>
     *
     *              <li>getListArray - a very useful method for determining the behavior of filling arrays.
     *              It returns a list of array types of different nesting that must be created with multidimensional arrays.
     *              You can see an example of use in {@link com.github.shakal76.fillen.base.BaseDiet}</li>
     *
     *              <p></p>
     *
     *              <li>isTypesEquals - the simplest possible method that checks whether the data types match
     *              it is made for easier reading of custom Fillen.Diet.menu</li>
     *           </ul>
     *      </li>
     *
     *      <li>TARGET - there is only one method in this block.
     *      The same non-implemented abstract method that I hope you will be able to implement easily :)</li>
     * </ul>
     */
    public abstract static class Diet {
        Context context;

        // CONFIGURE
        private Priority priority = Priority.LOW;

        public Priority getPriority() {
            return priority;
        }
        public Diet setPriority(Priority priority) {
            this.priority = priority;
            return this;
        }

        // BUILT-IN METHODS

        // BE CAREFULLY WITH IT BECAUSE OF RECURSION
        protected Object callback(Ingredients ingredients) throws UserDietException {
            if (context == null) {
                throw new UserDietException("haven't context");
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
        protected Object dinner(Class<?> type) throws UserDietException {
            if (context == null) {
                throw new UserDietException("haven't context");
            }
            try {
                return Heart.dinner(type, context);
            }catch (BadLootException e) {
                throw new UserDietException(e.getMessage());
            }
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

        // TARGET
        public abstract Object menu(Ingredients ingredients) throws UserDietException;

    }

}
