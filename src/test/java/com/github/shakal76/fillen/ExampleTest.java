package com.github.shakal76.fillen;

import com.github.shakal76.fillen.exception.BadLootException;
import com.github.shakal76.fillen.pojo.ExamplePojo;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;


public class ExampleTest {

    @Test
    public void firstExample() throws BadLootException {


        Fillen fillen = new Fillen();

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
                }

                // ye, it safety :))
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
}