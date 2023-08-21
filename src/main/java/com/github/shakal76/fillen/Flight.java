package com.github.shakal76.fillen;

import com.github.shakal76.fillen.exception.BadLootException;

class Flight {
    private Context context;

    Flight(Context context) {
        this.context = context;
    }
    public Flight ignoreFields(String... fieldNames) {
        for (String field : fieldNames) {
            this.context.ignoringlist.add(field);
        }
        return this;
    }
    public<T> Flight setField(String fieldName, T value) {
        this.context.settinglist.put(fieldName, value);
        return this;
    }
    public<T> T dinner(Class<T> type) throws BadLootException {
        return Heart.dinner(type, context);
    }
}
