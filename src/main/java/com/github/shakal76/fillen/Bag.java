package com.github.shakal76.fillen;

import com.github.shakal76.fillen.base.BaseDietList;
import com.github.shakal76.fillen.base.BaseDietWrappers;

import java.util.ArrayList;
import java.util.List;

public class Bag {
    private List<Fillen.Diet> bag = new ArrayList<>();

    public Bag() {
        this.bag.add(new BaseDietWrappers().diet);
        this.bag.add(new BaseDietList().diet);
    }
    public void put(Fillen.Diet userType) {
        this.bag.add(userType);
    }
    public List<Fillen.Diet> get() {
        return this.bag;
    }
    public void set(Bag bag) {
        for (Fillen.Diet diet : bag.get()) {
            this.bag.add(diet);
        }
    }
}
