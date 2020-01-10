package com.gonnect.querydsl.integration.unit;

import com.gonnect.querydsl.integration.unit.models.Person;
import org.junit.Test;



public class PersonPrimitiveQueriesTest extends AbstractUnitTest<Person> {

    // basic operators
    @Test
    public void testEquals() {
        check("firstName==gaurav", "{\"firstName\": \"gaurav\"}");
    }

    @Test
    public void testNotEquals() {
        check("firstName!=naman","{\"firstName\": {\"$ne\": \"naman\"}}");
    }

    @Test
    public void testGreaterThan() {
        check("age=gt=32", "{\"age\": {\"$gt\": 32}}");
    }

    @Test
    public void testGreaterThanOrEqualTo() {
        check("age=ge=32", "{\"age\": {\"$gte\": 32}}");
    }

    @Test
    public void testLessThan() {
        check("age=lt=30", "{\"age\": {\"$lt\": 30}}");
    }

    @Test
    public void testLessThanOrEqualTo() {
        check("age=le=31", "{\"age\": {\"$lte\": 31}}");
    }

    @Test
    public void testInListOfThings() {
        check("firstName=in=(aarika,naman,amay)", "{\"firstName\": {\"$in\": [\"aarika\", \"naman\", \"amay\"]}}");
    }

    @Test
    public void testNotInListOfThings() {
        check("firstName=out=(billy,bob,joel)", "{\"firstName\": {\"$nin\": [\"billy\", \"bob\", \"joel\"]}}");
    }

}
