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
package com.github.lemerch.fillen.common;

import com.github.lemerch.fillen.common.beans.IgnoreBean;
import com.github.lemerch.fillen.Fillen;
import com.github.lemerch.fillen.exception.BadLootException;
import com.github.lemerch.fillen.exception.user.DietAlreadyExistException;
import org.junit.Test;

import static com.github.lemerch.fillen.basic.api.base;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class FlightTests {
    @Test
    public void setterForIgnoreFieldNotNecessarily() {
        BadLootException exception = null;

        Fillen fillen = null;

        try {
            fillen = new Fillen(base);
        }catch (DietAlreadyExistException e) {
            exception = e;
        }
        assertNull(exception);
        assertNotNull(fillen);

        try {
            IgnoreBean ignorePojo = fillen.ignore("b").dinner(IgnoreBean.class);
            assertNull(ignorePojo.getB());
        }catch (BadLootException e) {
            assertNull(e);
        }
    }
    @Test
    public void setterForSetFieldIsNecessarily() {
        Fillen fillen = null;

        try {
            fillen = new Fillen(base);
        }catch (DietAlreadyExistException e) {
            assertNull(e);
        }
        assertNotNull(fillen);

        try {
            fillen.set("b", "hello, world").dinner(IgnoreBean.class);
        }catch (BadLootException e) {
            assertNotNull(e);
        }
        try {
            IgnoreBean ignorePojo = fillen.set("a", 123).ignore("b").dinner(IgnoreBean.class);
            assertEquals(123, ignorePojo.getA());
            assertNull(ignorePojo.getB());
        }catch (BadLootException e) {
            assertNull(e);
        }
    }
    @Test
    public void setOrIgnoreConflict() {
        Fillen fillen = null;

        try {
            fillen = new Fillen(base);
        }catch (DietAlreadyExistException e) {
            assertNull(e);
        }
        assertNotNull(fillen);

        try {
            fillen.set("b", "hello, world").ignore("b").dinner(IgnoreBean.class);
        }catch (BadLootException e) {
            assertNotNull(e);
        }
    }
}
