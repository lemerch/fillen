package com.github.shakal76.fillen;

import com.github.shakal76.fillen.base.BaseDiet;

import java.util.ArrayList;
import java.util.List;

public class Bag {
    private List<Fillen.Diet> bag = new ArrayList<>();

    public Bag() {
        this.bag.add(BaseDiet.diet);
    }
    public void put(Fillen.Diet diet) {
        this.bag.add(diet);
    }
    public List<Fillen.Diet> get() {
        return this.bag;
    }
}
