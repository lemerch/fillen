package com.github.lemerch.fillen.examples.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * FOR IGNORE - SETTER IS OPTIONAL
 */
public class FirstBean {

    private String[][][][][] text;
    private Integer number;

    private ArrayList<LinkedList<SecondBean>> list;

    private BigDecimal decimal;

    public String[][][][][] getText() {
        return text;
    }

    public Integer getNumber() {
        return number;
    }

    public ArrayList<LinkedList<SecondBean>> getList() {
        return list;
    }

    public BigDecimal getDecimal() {
        return decimal;
    }

    public void setText(String[][][][][] text) {
        this.text = text;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setList(ArrayList<LinkedList<SecondBean>> list) {
        this.list = list;
    }

    public void setDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }
}
