package com.gonnect.querydsl.dynamodb.argconverters;

import com.gonnect.querydsl.dynamodb.structs.ConversionInfo;
import com.gonnect.querydsl.dynamodb.structs.Lazy;

public class OperatorSpecificConverter implements StringToQueryValueConverter {

    @Override
    public Lazy<Object> convert(ConversionInfo info) {
        switch (info.getOperator()) {
            case REGEX:
                return Lazy.fromValue(info.getArgument());
            case EXISTS:
                return Lazy.fromValue(Boolean.valueOf(info.getArgument()));
            default:
                return Lazy.empty();
        }
    }

}
