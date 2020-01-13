# Mongo QureyDSL

## Example Unit Test cases DynamoDB

```java
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

```

```java

 @Test
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

```

## Example Unit Test cases Elastic Search

Here directly Elastic Search QueryBuilder is generated from Query DSL, as ElasticSearch provides nice abstraction by this builder

```java
 @Test
    public void testIn() {
        // Arrange
        ComparisonOperator in = new ComparisonOperator("=in=", true);
        QueryBuilder expectedQuery = termsQuery(selector, args);
        ComparisonNode node = new ComparisonNode(in, selector, args);

        // Act
        QueryBuilder actualQuery = interpreter.interpret(node);

        // Assert
        assertEquals(expectedQuery, actualQuery);
    }
```

## Druid example

```java
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
 ```

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

