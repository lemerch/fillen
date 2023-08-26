package com.github.shakal76.fillen.pojo;

import java.math.BigDecimal;
import java.util.*;

/**
 * GETTERS AND SETTERS NOT REQUIRED
 */
public class ExamplePojo {

    private String[][][][][] text;
    private Integer number;

    private ArrayList<LinkedList<Other>> list;

    private BigDecimal decimal;

    ////////////////////////////////////////////
    public String[][][][][] getText() {
        return text;
    }

    public Integer getNumber() {
        return number;
    }

    public ArrayList<LinkedList<Other>> getList() {
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

    public void setList(ArrayList<LinkedList<Other>> list) {
        this.list = list;
    }

    public void setDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }
}
