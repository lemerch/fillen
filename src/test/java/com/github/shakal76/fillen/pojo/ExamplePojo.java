package com.github.shakal76.fillen.pojo;

import java.util.List;

public class ExamplePojo {

    private String text;
    private Integer number;

    private List<Other> list;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Other> getList() {
        return list;
    }

    public void setList(List<Other> list) {
        this.list = list;
    }
}
