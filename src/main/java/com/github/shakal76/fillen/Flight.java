package com.github.shakal76.fillen;

import com.github.shakal76.fillen.exception.BadLootException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Flight {
    private List<Object> ignoringlist = new ArrayList<>();
    private Map<String, Object> settinglist = new HashMap<>();
    private List<Fillen.UserType> configuration;

    Flight(List<Fillen.UserType> configuration) {
        this.configuration = configuration;
    }
    public Flight ignoreFields(String... fieldNames) {
        for (String field : fieldNames) {
            this.ignoringlist.add(field);
        }
        return this;
    }
    public<T> Flight setField(String fieldName, T value) {
        this.settinglist.put(fieldName, value);
        return this;
    }
    public<T> T dinner(Class<T> type) throws BadLootException {
        return Heart.dinner(type, ignoringlist, settinglist, configuration);
    }
}