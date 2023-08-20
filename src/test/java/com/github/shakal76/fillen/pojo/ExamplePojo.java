package com.github.shakal76.fillen.pojo;

import java.util.List;

public class ExamplePojo {

    private String text[][][][];
    private Integer number;

    private List<String> list;

    public String[][][][] getText() {
        return text;
    }

    public void setText(String[][][][] text) {
        this.text = text;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
