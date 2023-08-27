package com.github.shakal76.fillen.examples.pojo;

import com.github.shakal76.fillen.examples.pojo.Other;

import java.math.BigDecimal;
import java.util.ArrayList;

public class NewPojo {
    private ArrayList<Other> list;

    private BigDecimal decimal;

    public ArrayList<Other> getList() {
        return list;
    }

    public BigDecimal getDecimal() {
        return decimal;
    }

    public void setList(ArrayList<Other> list) {
        this.list = list;
    }

    public void setDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }
}
