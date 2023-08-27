package com.github.shakal76.fillen.exception.service.logical;

import com.github.shakal76.fillen.exception.BadLootException;

public class SetFieldNotFoundException extends BadLootException {
    public SetFieldNotFoundException(String message) {
        super(message);
    }
}
