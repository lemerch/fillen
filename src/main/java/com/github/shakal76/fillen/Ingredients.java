package com.github.shakal76.fillen;

import com.github.shakal76.fillen.utils.Generic;

import java.lang.annotation.Annotation;

public class Ingredients {
    public final Class<?> type;
    public final String name;
    public final Generic generic;
    public final Annotation[] declaredAnnotations;
    public final int modifier;

    public Ingredients(Class<?> type, String name, Generic generic, Annotation[] declaredAnnotations, int modifier) {
        this.type = type;
        this.name = name;
        this.generic = generic;
        this.declaredAnnotations = declaredAnnotations;
        this.modifier = modifier;
    }
}
