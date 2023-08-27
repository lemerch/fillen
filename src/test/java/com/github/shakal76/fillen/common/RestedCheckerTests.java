package com.github.shakal76.fillen.common;

import com.github.shakal76.fillen.Fillen;
import com.github.shakal76.fillen.exception.BadLootException;
import com.github.shakal76.fillen.exception.service.logical.IgnoreFieldNotFoundException;
import com.github.shakal76.fillen.exception.service.logical.SetFieldNotFoundException;
import com.github.shakal76.fillen.exception.user.DietAlreadyExistException;
import org.junit.Test;

import static com.github.shakal76.fillen.basic.api.base;
import static org.junit.Assert.*;

public class RestedCheckerTests {

    @Test
    public void IgnoreRestedCheckerTest() {
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
            fillen.ingore("notexistfield").dinner(RestedCheckerTests.class);
        }catch (IgnoreFieldNotFoundException e) {
            assertNotNull(e);
        }catch (BadLootException e) {
            assertNull(e);
        }

    }
    @Test
    public void SetRestedCheckerTest() {
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
            fillen.set("abc", 123).dinner(RestedCheckerTests.class);
        }catch (SetFieldNotFoundException e) {
            assertNotNull(e);
        }catch (BadLootException e) {
            assertNull(e);
        }

    }
}
