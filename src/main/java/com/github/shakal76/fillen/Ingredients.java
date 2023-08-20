package com.github.shakal76.fillen;

import java.lang.annotation.Annotation;

public class Ingredients {
    public final Class<?> type;
    public final String name;
    public final Annotation[] declaredAnnotations;
    public final int modifier;

    public Ingredients(Class<?> type, String name, Annotation[] declaredAnnotations, int modifier) {
        this.type = type;
        this.name = name;
        this.declaredAnnotations = declaredAnnotations;
        this.modifier = modifier;
    }
}
