package com.github.shakal76.fillen.common.beans;

public class IgnoreBean {
    private int a;
    private String b;
    private Double c;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    // NO SETTER B
    public String getB() {
        return b;
    }

    public Double getC() {
        return c;
    }

    public void setC(Double c) {
        this.c = c;
    }
}
