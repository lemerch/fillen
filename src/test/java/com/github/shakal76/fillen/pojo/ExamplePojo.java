package com.github.shakal76.fillen.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * GETTERS AND SETTERS NOT REQUIRED
 */
public class ExamplePojo {

    private String[][][] text;
    private Integer number;

    private List<List<Other>> list;

    private BigDecimal decimal;

/////////////////////////////////////////////////////////////////////

    public BigDecimal getDecimal() {
        return decimal;
    }

    public String[][][] getText() {
        return text;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<List<Other>> getList() {
        return list;
    }

}
