package com.github.shakal76.fillen.common;

import com.github.shakal76.fillen.Bag;
import com.github.shakal76.fillen.Fillen;
import com.github.shakal76.fillen.exception.BadLootException;
import com.github.shakal76.fillen.exception.user.UserDietException;
import com.github.shakal76.fillen.utils.Ingredients;
import org.junit.Test;

import static com.github.shakal76.fillen.basic.api.base;
import static org.junit.Assert.*;

public class BagTests {

    @Test
    public void BagAddTest() {
        BadLootException exception = null;

        Bag bag = new Bag();
        try {
            bag.add(base);
            bag.add(base);
        }catch (BadLootException e) {
            exception = e;
        }

        assertNotNull(exception);
    }

    @Test
    public void BagGetTest() {
        BadLootException exception = null;

        Fillen.Diet diet = new Fillen.Diet() {
            @Override
            public Object menu(Ingredients ingredients) throws UserDietException {
                if (ingredients.type == null) {
                    return true;
                }
                return false;
            }
        };
        Bag bag = new Bag();
        try {
            bag.add(base);
            bag.add(diet);
        }catch (BadLootException e) {
            exception = e;
        }
        assertNull(exception);
        Fillen.Diet getted = bag.get(diet.getClass());
        assertNotNull(getted);

        Boolean result = false;
        try {
            result = (Boolean) getted.menu(
                    new Ingredients(null, null, null, null, 0)
            );
        }catch (BadLootException e) {
            exception = e;
        }
        assertNull(exception);
        assertEquals((Boolean) result, true);
    }
}
