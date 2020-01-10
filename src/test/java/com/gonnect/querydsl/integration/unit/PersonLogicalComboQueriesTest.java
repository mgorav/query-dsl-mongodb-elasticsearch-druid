package com.gonnect.querydsl.integration.unit;

import com.gonnect.querydsl.integration.unit.models.Person;
import org.junit.Test;

public class PersonLogicalComboQueriesTest extends AbstractUnitTest<Person> {

    @Test
    public void testEqualityOfTwoAndedThings() {
        check("firstName!=gaurav;lastName==dummy", "{\"$and\": [{\"firstName\": {\"$ne\": \"gaurav\"}}, {\"lastName\": \"dummy\"}]}");
    }

    @Test
    public void testThingsThatAreOredTogether() {
        check("firstName!=shikha,lastName==malhotra", "{\"$or\": [{\"firstName\": {\"$ne\": \"shikha\"}}, {\"lastName\": \"malhotra\"}]}");
    }

    @Test
    public void testAndingOfOringsOfAndings() {
        check("((firstName==gaurav;lastName==malhotra),(firstName==shikha;lastName==malhotra));((age==21;height==90),(age==30;height==100))",
                "{\"$and\": [{\"$or\": [{\"$and\": [{\"firstName\": \"gaurav\"}, {\"lastName\": \"malhotra\"}]}, {\"$and\": [{\"firstName\": \"shikha\"}, {\"lastName\": \"malhotra\"}]}]}, {\"$or\": [{\"$and\": [{\"age\": 21}, {\"height\": 90}]}, {\"$and\": [{\"age\": 30}, {\"height\": 100}]}]}]}");
    }


}
