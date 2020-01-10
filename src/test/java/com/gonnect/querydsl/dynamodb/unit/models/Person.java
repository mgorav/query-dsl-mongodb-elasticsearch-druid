package com.gonnect.querydsl.dynamodb.unit.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Calendar;

@Document(collection = "persons")
@Data
@NoArgsConstructor
public class Person {

    // standard stuff
    @Id
    private String id;
    private int age;
    private int height;
    private String firstName;
    private String lastName;


    // field annotations
    @Field("aGoodFieldName")
    private int aBadFieldName;


    // custom type conversions
    private Calendar dateOfBirth;


}
