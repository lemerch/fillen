package com.github.shakal76.fillen.common;

import com.github.shakal76.fillen.Fillen;
import com.github.shakal76.fillen.exception.BadLootException;
import com.github.shakal76.fillen.exception.user.DietAlreadyExistException;
import com.github.shakal76.fillen.common.pojo.IgnorePojo;
import org.junit.Test;

import static com.github.shakal76.fillen.basic.api.base;
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
            IgnorePojo ignorePojo = fillen.ingore("b").dinner(IgnorePojo.class);
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
            fillen.set("b", "hello, world").dinner(IgnorePojo.class);
        }catch (BadLootException e) {
            assertNotNull(e);
        }
        try {
            IgnorePojo ignorePojo = fillen.set("a", 123).ignore("b").dinner(IgnorePojo.class);
            assertEquals(ignorePojo.getA(), 123);
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
            fillen.set("b", "hello, world").ignore("b").dinner(IgnorePojo.class);
        }catch (BadLootException e) {
            assertNotNull(e);
        }
    }
}
