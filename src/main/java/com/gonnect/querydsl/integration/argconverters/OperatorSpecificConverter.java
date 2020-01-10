package com.gonnect.querydsl.integration.argconverters;

import com.gonnect.querydsl.integration.structs.ConversionInfo;
import com.gonnect.querydsl.integration.structs.Lazy;

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
