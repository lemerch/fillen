package com.github.shakal76.fillen.exception.service.specialized;

import com.github.shakal76.fillen.exception.BadLootException;

public class HeartException extends BadLootException {
    public HeartException(String message) {super(message);}
    public HeartException(String message, Exception e) {super(message, e);}
}
