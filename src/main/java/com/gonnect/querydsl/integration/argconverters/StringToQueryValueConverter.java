package com.gonnect.querydsl.integration.argconverters;

import com.gonnect.querydsl.integration.structs.ConversionInfo;
import com.gonnect.querydsl.integration.structs.Lazy;

public interface StringToQueryValueConverter {

    Lazy<Object> convert(ConversionInfo info);

}
