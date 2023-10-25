package com.github.lemerch.fillen.common.beans;

public class NoDefaultConstructor {

    private int i = 0;

    public NoDefaultConstructor(int i) {
        this.i = i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
