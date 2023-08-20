package com.github.shakal76.fillen;

import com.github.shakal76.fillen.exception.BadLootException;

import java.lang.reflect.Field;
import java.util.*;

public class Fillen {
    private Bag bag = new Bag();

    // CONFIGURE
    public Fillen(Bag bag) {
        this.bag = bag;
    }
    public Fillen(Diet... diets) {
        for (Diet diet : diets) {
            bag.put(diet);
        }
    }


    // INTERMEDIATE HANDLERS
    public Flight ignoreFields(String... fieldNames) {
        Flight flight = new Flight(bag);
        return flight.ignoreFields(fieldNames);
    }
    public<T> Flight setField(String fieldName, T value) {
        Flight flight = new Flight(bag);
        return flight.setField(fieldName, value);
    }

    // PERFERMER


    public<T> T dinner(Class<T> type) throws BadLootException {
        return Heart.dinner(type, new ArrayList<>(), new HashMap<>(), bag);
    }

    // UTILS

    public abstract static class Diet {
        public static Boolean isTypesEquals(Class<?> one, Class<?> two) {
            return one.isAssignableFrom(two);
        }
        public abstract Object menu(Ingredients ingredients);
    }

}
