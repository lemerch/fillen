package com.github.shakal76.fillen.examples;

import com.github.shakal76.fillen.Bag;
import com.github.shakal76.fillen.Fillen;
import com.github.shakal76.fillen.exception.BadLootException;
import org.junit.Test;

import static com.github.shakal76.fillen.basic.api.base;

public class ExampleTest {

    @Test
    public void firstExample() throws BadLootException {
        Bag bag = new Bag();
        bag.add(base);
        Fillen fillen = new Fillen(base);
    }

}