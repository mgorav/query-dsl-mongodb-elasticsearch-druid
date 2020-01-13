package com.gonnect.querydsl.druid;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DruidQuery {
    private final String query;
    private final String context;
}

