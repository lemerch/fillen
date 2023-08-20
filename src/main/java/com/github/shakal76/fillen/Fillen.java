package com.github.shakal76.fillen;

import com.github.shakal76.fillen.exception.BadLootException;
import com.github.shakal76.fillen.utils.Generic;

import java.lang.annotation.Annotation;
import java.util.*;

public class Fillen {
    private Bag bag = new Bag();

    // CONFIGURE
    public Fillen(Bag bag) {
        this.bag = bag;
    }
    public Fillen(Fillen.Diet... diets) {
        for (Diet diet : diets) {
            this.bag.put(diet);
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
        for (Fillen.Diet diet : bag.get()) {
            diet.bag = this.bag;
            diet.ignoringlist = new ArrayList<>();
            diet.settinglist = new HashMap<>();
        }
        return Heart.dinner(type, new ArrayList<>(), new HashMap<>(), bag);
    }

    // UTILS
    // TODO: add priority field

    public abstract static class Diet {
        List<String> ignoringlist = null;
        Map<String, Object> settinglist = null;
        public Bag bag = null;

        public Object heart(Ingredients ingredients) throws BadLootException {
            if (ignoringlist == null || settinglist == null || bag == null) {
                throw new BadLootException("hava not context");
            }
            Object result = null;
            boolean was = false;
            for (Fillen.Diet diet : bag.get()) {
                Object timed = diet.menu(ingredients);
                if (timed != null) {
                    result = timed;
                    was = true;
                }
            }
            if (was) {
                return result;
            }else {
                try {
                    return Heart.dinner(
                            Class.forName(ingredients.type.getTypeName()), ignoringlist, settinglist, bag
                    );
                } catch (Exception e) { return null;}
            }
        }
        public Boolean isTypesEquals(Class<?> one, Class<?> two) throws BadLootException {
            return two.isAssignableFrom(one);
        }
        public List<Class<?>> getListArray(Class<?> arr) {
            List<Class<?>> array = new ArrayList<>();
            if (arr.isArray()) {
                Class<?> currentType = arr;
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
        public abstract Object menu(Ingredients ingredients) throws BadLootException;

    }

}
