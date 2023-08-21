package com.github.shakal76.fillen;

import com.github.shakal76.fillen.exception.BadLootException;
import com.github.shakal76.fillen.pojo.ExamplePojo;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ExampleTest {

    @Test
    public void example() throws BadLootException {


        Fillen fillen = new Fillen();

        ExamplePojo dinner = fillen.dinner(ExamplePojo.class);
        System.out.println(Arrays.deepToString(dinner.getText()));
        System.out.println(dinner.getNumber());
        System.out.println(dinner.getList().get(0).get(0).getC());
    }
}