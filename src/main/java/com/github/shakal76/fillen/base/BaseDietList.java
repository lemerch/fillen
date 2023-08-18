package com.github.shakal76.fillen.base;

import com.github.shakal76.fillen.Fillen;
import com.github.shakal76.fillen.exception.BadLootException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BaseDietList {
    public Fillen.Diet diet = new Fillen.Diet() {
        @Override
        public Object menu(Class<?> field, Type[] generics) throws BadLootException  {
            if(isTypesEquals(field, List.class)) {
                Class<?> actual = null;
                try {
                    actual = Class.forName(generics[0].getTypeName());
                }catch (ClassNotFoundException e) {}

                int size = 5 + (int)(Math.random() * ((10 - 5) + 1));
                List<Object> result = new ArrayList<>(size);

                var wrappers = new BaseDietWrappers();
                Object isSimpleType = wrappers.diet.menu(actual, generics);
                if (isSimpleType == null) {
                    for (int i = 0; i < size; i++)
                        result.add( heart(actual) );
                }else {
                    result.add(isSimpleType);
                    for (int i = 0; i < size; i++)
                        result.add(wrappers.diet.menu(actual, generics));
                }
                return result;
            }else {
                return null;
            }
        }
    };
}
