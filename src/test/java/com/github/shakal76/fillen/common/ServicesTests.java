package com.github.shakal76.fillen.common;

import com.github.shakal76.fillen.Fillen;
import com.github.shakal76.fillen.exception.BadLootException;
import com.github.shakal76.fillen.exception.user.DietAlreadyExistException;
import com.github.shakal76.fillen.common.pojo.IgnorePojo;
import com.github.shakal76.fillen.common.pojo.NoDefaultConstructor;
import com.github.shakal76.fillen.common.pojo.WithFillenList;
import org.junit.Test;

import static com.github.shakal76.fillen.basic.api.base;
import static org.junit.Assert.assertNotNull;

public class ServicesTests {

    @Test
    public void noConstructorExceptionCheck() {
        BadLootException exception = null;

        Fillen fillen = null;

        try {
            fillen = new Fillen(base);
        } catch (DietAlreadyExistException e) {
            exception = e;
        }
        assertNotNull(fillen);

        try {
            fillen.dinner(NoDefaultConstructor.class);
        }catch (BadLootException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void noSetterExceptionCheck() {
        BadLootException exception = null;

        Fillen fillen = null;

        try {
            fillen = new Fillen(base);
        } catch (DietAlreadyExistException e) {
            exception = e;
        }
        assertNotNull(fillen);

        try {
            fillen.dinner(IgnorePojo.class);
        }catch (BadLootException e) {
            assertNotNull(e);
        }
    }
    @Test
    public void wrongCastExceptionCheck() {
        BadLootException exception = null;

        Fillen fillen = null;

        try {
            fillen = new Fillen(base);
        } catch (DietAlreadyExistException e) {
            exception = e;
        }
        assertNotNull(fillen);

        try {
            fillen.set("a", "Hello, world!").dinner(IgnorePojo.class);
        }catch (BadLootException e) {
            assertNotNull(e);
        }
    }
    @Test
    public void fillenListUsageCheck() {
        BadLootException exception = null;

        Fillen fillen = null;

        try {
            fillen = new Fillen(base);
        } catch (DietAlreadyExistException e) {
            exception = e;
        }
        assertNotNull(fillen);

        try {
            fillen.dinner(WithFillenList.class);
        }catch (BadLootException e) {
            assertNotNull(e);
        }
    }
}
