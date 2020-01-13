package com.gonnect.querydsl.druid;

import com.gonnect.querydsl.druid.DruidQuery;
import com.gonnect.querydsl.druid.DruidQueryBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DruidQueryBuilderTest {

    private DruidQueryBuilder druidQueryBuilder = new DruidQueryBuilder();
    @Test
    public void testSimpleSearchWithEqualOperator() {

        DruidQuery query =  druidQueryBuilder.createQuery("orders", "*", "name==\'Kill Bill\'");
        assertEquals("SELECT * FROM \"orders\" WHERE (name = 'Kill Bill') LIMIT 1", query.getQuery());

    }

    @Test
    public void testAndSearchWithEqualOperator() {

        DruidQuery query =  druidQueryBuilder.createQuery("orders", "*", "name=='Kill Bill' and createdBy == 'Tiger'");
        assertEquals("SELECT * FROM \"orders\" WHERE  ((name = 'Kill Bill') and (createdBy = 'Tiger'))","SELECT * FROM \"orders\" WHERE  ((name = 'Kill Bill') and (createdBy = 'Tiger'))");
        System.out.println(query.getQuery());

    }

    @Test
    public void testOrSearchWithEqualOperator() {

        DruidQuery query =  druidQueryBuilder.createQuery("orders", "*", "name=='Kill Bill' or createdBy == 'Tiger'");
        assertEquals("SELECT * FROM \"orders\" WHERE  ((name = 'Kill Bill') or (createdBy = 'Tiger'))","SELECT * FROM \"orders\" WHERE  ((name = 'Kill Bill') or (createdBy = 'Tiger'))");
        System.out.println(query.getQuery());

    }

    @Test
    public void testAndOrSearchWithEqualOperator() {

        DruidQuery query =  druidQueryBuilder.createQuery("orders", "*", "name=='Kill Bill' or createdBy == 'Tiger' and company == 'Beer'");
        assertEquals("SELECT * FROM \"orders\" WHERE ((name = 'Kill Bill') or  ((createdBy = 'Tiger') and (company = 'Beer')))","SELECT * FROM \"orders\" WHERE ((name = 'Kill Bill') or  ((createdBy = 'Tiger') and (company = 'Beer')))");
        System.out.println(query.getQuery());

    }



}
