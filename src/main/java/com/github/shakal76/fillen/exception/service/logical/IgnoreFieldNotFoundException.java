package com.github.shakal76.fillen.exception.service.logical;

import com.github.shakal76.fillen.exception.BadLootException;

public class IgnoreFieldNotFoundException extends BadLootException {
    public IgnoreFieldNotFoundException(String message) {
        super(message);
    }
}
