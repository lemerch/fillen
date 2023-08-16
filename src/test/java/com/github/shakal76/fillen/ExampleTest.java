package com.github.shakal76.fillen;

import com.github.shakal76.fillen.bags.UserTypeContainer;
import com.github.shakal76.fillen.exception.BadLootException;
import com.github.shakal76.fillen.pojo.ExamplePojo;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class ExampleTest {

    @Test
    public void example() throws BadLootException {

        Fillen.UserType listHandler = new Fillen.UserType() {
            @Override
            public Object handler(Field field) {
                if (isType(List.class, field)) {
                    List<String> x = new ArrayList<>();
                    x.add("Hello");
                    return x;
                }
                return field;
            }
        };

        UserTypeContainer con = new UserTypeContainer();
        con.put(listHandler);
        Fillen fillen = new Fillen(con);

        Flight flight = fillen.ignoreFields("number");
        ExamplePojo dsa = flight.dinner(ExamplePojo.class);
        System.out.println(dsa.getText());
        System.out.println(dsa.getNumber());
        System.out.println(dsa.getList().get(0));

        ExamplePojo dinner = fillen.dinner(ExamplePojo.class);
        System.out.println(dinner.getText());
        System.out.println(dinner.getNumber());
        System.out.println(dinner.getList().get(0));
    }
}