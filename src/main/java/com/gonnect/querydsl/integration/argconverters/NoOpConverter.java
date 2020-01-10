package com.gonnect.querydsl.integration.argconverters;


import com.gonnect.querydsl.integration.structs.ConversionInfo;
import com.gonnect.querydsl.integration.structs.Lazy;

public class NoOpConverter implements StringToQueryValueConverter {

    @Override
    public Lazy<Object> convert(ConversionInfo info) {
        return Lazy.fromValue(info.getArgument());
    }

}
