package com.gonnect.querydsl.dynamodb.argconverters;

import com.gonnect.querydsl.dynamodb.structs.ConversionInfo;
import com.gonnect.querydsl.dynamodb.structs.Lazy;

public interface StringToQueryValueConverter {

    Lazy<Object> convert(ConversionInfo info);

}
