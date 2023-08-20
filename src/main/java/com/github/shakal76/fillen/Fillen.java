package com.github.shakal76.fillen;

import com.github.shakal76.fillen.exception.BadLootException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
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

    public abstract static class Diet {
        List<String> ignoringlist = null;
        Map<String, Object> settinglist = null;
        public Bag bag = null;

        public Object heart(Class<?> type, String name, Generic generic, Annotation[] annotations) throws BadLootException {
            if (ignoringlist == null || settinglist == null || bag == null) {
                throw new BadLootException("hava not context");
            }
            Object result = null;
            boolean was = false;
            for (Fillen.Diet diet : bag.get()) {
                Object timed = diet.menu(type, type.getName(), generic, type.getDeclaredAnnotations());
                if (timed != null) {
                    result = timed;
                    was = true;
                }
            }
            if (was) {
                return result;
            }else {
                try {
                    return Heart.dinner(Class.forName(type.getTypeName()), ignoringlist, settinglist, bag);
                } catch (Exception e) { return null;}
            }
        }
        public Boolean isTypesEquals(Class<?> one, Class<?> two) throws BadLootException {
            return two.isAssignableFrom(one);
        }
        public abstract Object menu(Class<?> type, String name, Generic generic, Annotation[] annotations) throws BadLootException;

    }

}
