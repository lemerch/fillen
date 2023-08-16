package com.github.shakal76.fillen.bags;

import com.github.shakal76.fillen.Fillen;

import java.util.ArrayList;
import java.util.List;

public class UserTypeContainer {
    private List<Fillen.UserType> container = new ArrayList<>();

    public void put(Fillen.UserType userType) {
        this.container.add(userType);
    }
    public List<Fillen.UserType> get() {
        return this.container;
    }
}
