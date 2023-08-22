package com.github.shakal76.fillen;

import com.github.shakal76.fillen.enums.Priority;
import com.github.shakal76.fillen.exception.BadLootException;
import com.github.shakal76.fillen.pojo.ExamplePojo;
import com.github.shakal76.fillen.pojo.Other;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;


public class ExampleTest {

    @Test
    public void firstExample() throws BadLootException {

        Fillen.Diet diet = new Fillen.Diet() {
            @Override
            public Object menu(Ingredients ingredients) throws BadLootException {
                if (isTypesEquals(ingredients.type, Other.class)) {
                    return heart(ingredients.type);
                }

                // ye, its safety :))
                return null;
            }
        };

        Fillen fillen = new Fillen(diet);

        ExamplePojo first = fillen.dinner(ExamplePojo.class);

        System.out.println("\nFIRST RESULT:\n");
        System.out.println("text: " + Arrays.deepToString(first.getText()));
        System.out.println("number: " + first.getNumber());
        System.out.println("list: " + first.getList().get(0).get(0).getC());
        System.out.println("decimal: " + first.getDecimal());
    }
    @Test
    public void secondExample() throws BadLootException {

        Fillen.Diet diet = new Fillen.Diet() {
            @Override
            public Object menu(Ingredients ingredients) throws BadLootException {
                if (isTypesEquals(ingredients.type, BigDecimal.class)) {
                    return new BigDecimal(123);
                }else if (isTypesEquals(ingredients.type, Other.class)) {
                    return heart(ingredients.type);
                }

                // ye, its safety :))
                return null;
            }
        };

        Bag bag = new Bag();
        bag.put(diet);
        // you can put more than one Fillen.Diet

        Fillen fillen = new Fillen(bag);
        // or just Fillen fillen = new Fillen(diet);

        Flight flight = fillen.ignoreFields("text").setField("number", 25);

        // or just ExamplePojo first = fillen.ignoreFields("text").setField("number", 25).dinner(Example.class);

        ExamplePojo first = flight.dinner(ExamplePojo.class);

        System.out.println("-----------------\nSECOND RESULT:\n");
        System.out.println("text: " + Arrays.deepToString(first.getText()));
        System.out.println("number: " + first.getNumber());
        System.out.println("list: " + first.getList().get(0).get(0).getC());
        System.out.println("decimal: " + first.getDecimal());
    }
    @Test
    public void priorityTest() throws BadLootException {

        // LOW PRIORITY AS DEFAULT
        Fillen.Diet low = new Fillen.Diet() {
            @Override
            public Object menu(Ingredients ingredients) throws BadLootException {
                if (isTypesEquals(ingredients.type, String.class)) {
                    return "low";
                }else if (isTypesEquals(ingredients.type, Other.class)) {
                    return heart(ingredients.type);
                }
                return null;
            }
        };

        Fillen.Diet high = new Fillen.Diet() {
            @Override
            public Object menu(Ingredients ingredients) throws BadLootException {
                if (isTypesEquals(ingredients.type, String.class)) {
                    return "high";
                }else if (isTypesEquals(ingredients.type, Other.class)) {
                    return heart(ingredients.type);
                }
                return null;
            }
        }.setPriority(Priority.HIGH);

        Bag bag = new Bag();
        bag.put(high);
        bag.put(low);

        Fillen fillen = new Fillen(bag);

        ExamplePojo first = fillen.dinner(ExamplePojo.class);

        System.out.println("-----------------\nPRIORITYTEST RESULT  (must be [[[high]]]):\n");
        System.out.println("text: " + Arrays.deepToString(first.getText()));
    }
}