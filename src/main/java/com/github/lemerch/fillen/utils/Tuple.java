package com.github.lemerch.fillen.utils;

public class Tuple<T> {
    private boolean flag = false;
    private T val;
    public Tuple(T val) {
        this.val = val;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

}
