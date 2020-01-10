package com.gonnect.querydsl.dynamodb.argconverters;


import com.gonnect.querydsl.dynamodb.structs.ConversionInfo;
import com.gonnect.querydsl.dynamodb.structs.Lazy;

public class NoOpConverter implements StringToQueryValueConverter {

    @Override
    public Lazy<Object> convert(ConversionInfo info) {
        return Lazy.fromValue(info.getArgument());
    }

}
