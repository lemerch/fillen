package com.github.shakal76.fillen.pojo;

import java.math.BigDecimal;
import java.util.List;

public class NewPojo {
    private List<List<Other>> list;

    private BigDecimal decimal;

    public List<List<Other>> getList() {
        return list;
    }

    public BigDecimal getDecimal() {
        return decimal;
    }

    public void setList(List<List<Other>> list) {
        this.list = list;
    }

    public void setDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }
}
