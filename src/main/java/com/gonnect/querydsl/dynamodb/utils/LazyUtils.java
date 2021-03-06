package com.gonnect.querydsl.dynamodb.utils;

import com.gonnect.querydsl.dynamodb.structs.Lazy;

import java.util.List;

public final class LazyUtils {
    private LazyUtils(){}


    public static <T> T firstThatReturnsNonNull(List<Lazy<T>> lazies) {
        for(Lazy<T> lazy : lazies) {
            T val = lazy.get();
            if(val != null) {
                return val;
            }
        }
        return null;
    }


}
