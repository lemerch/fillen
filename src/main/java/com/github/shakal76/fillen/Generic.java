package com.github.shakal76.fillen;

import java.lang.reflect.Type;
import java.util.Arrays;

public class Generic {
    private String[] arr;
    public Generic(Type type) {
        String all = type.getTypeName();
        String[] splitted = all.split("<");
        for (int i = 0; i < splitted.length; i++) {
            splitted[i] = splitted[i].replaceAll(">", "");
        }
        this.arr = splitted;
        remove();
    }
    public Class<?> get() {
        try {
            Class<?> cl = Class.forName(arr[0]);
            return cl;
        }catch (ClassNotFoundException e) {}
        return null;
    }
    public Generic remove() {
        String[] newArr = new String[arr.length-1];
        for (int i = 1; i < arr.length; i++) {
            newArr[i-1] = arr[i];
        }
        this.arr = newArr;
        return this;
    }

}
