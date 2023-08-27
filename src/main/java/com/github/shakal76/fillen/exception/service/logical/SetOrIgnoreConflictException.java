package com.github.shakal76.fillen.exception.service.logical;

import com.github.shakal76.fillen.exception.BadLootException;

public class SetOrIgnoreConflictException extends BadLootException {
    public SetOrIgnoreConflictException(String message) {
        super(message);
    }
}