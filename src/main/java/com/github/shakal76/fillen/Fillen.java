package com.github.shakal76.fillen;

import com.github.shakal76.fillen.exception.BadLootException;
import com.github.shakal76.fillen.bags.UserTypeContainer;
import java.lang.reflect.Field;
import java.util.*;

public class Fillen {
    private List<UserType> configuration = new ArrayList<>();

    // CONFIGURE
    public Fillen(UserTypeContainer configure) {
        this.configuration = configure.get();
    }
    public Fillen(UserType... userTypes) {
        for (UserType userType : userTypes) {
            configuration.add(userType);
        }
    }


    // INTERMEDIATE HANDLERS
    public Flight ignoreFields(String... fieldNames) {
        Flight flight = new Flight(configuration);
        return flight.ignoreFields(fieldNames);
    }
    public<T> Flight setField(String fieldName, T value) {
        Flight flight = new Flight(configuration);
        return flight.setField(fieldName, value);
    }

    // PERFERMER


    public<T> T dinner(Class<T> type) throws BadLootException {
        return Heart.dinner(type, new ArrayList<>(), new HashMap<>(), configuration);
    }

    // UTILS TODO: Отделить от API

    public abstract static class UserType {
        public static Boolean isType(Field one, Class<?> two) {
            return one.getType().isAssignableFrom(two);
        }
        public static Boolean isType(Class<?> one, Field two) {
            return one.isAssignableFrom(two.getType());
        }
        public abstract Object handler(Field field);
    }


    private String fieldToGetter(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
    private String fieldToSetter(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
}
