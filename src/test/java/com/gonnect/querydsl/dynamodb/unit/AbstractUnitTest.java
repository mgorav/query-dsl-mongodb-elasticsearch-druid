package com.gonnect.querydsl.dynamodb.unit;

import com.gonnect.querydsl.dynamodb.ComparisonToCriteriaConverter;
import com.gonnect.querydsl.dynamodb.RsqlMongoAdapter;
import com.google.common.reflect.TypeToken;
import org.bson.Document;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AbstractUnitTest.TestApplication.class)
public abstract class AbstractUnitTest<T> {

    protected Class<T> CLAZZ = (Class<T>)(new TypeToken<T>(getClass()){}).getRawType();
    protected RsqlMongoAdapter adapter;

    @Autowired
    protected MongoMappingContext mongoMappingContext;

    protected ConversionService conversionService = new DefaultConversionService();

    @Before
    public void setUp() {
        ComparisonToCriteriaConverter converter = new ComparisonToCriteriaConverter(conversionService, mongoMappingContext);
        adapter = new RsqlMongoAdapter(converter);
    }

    protected Query query(String rsql) {
        return Query.query(adapter.getCriteria(rsql, CLAZZ));
    }

    protected void check(String rsql, String mongo) {
        assertEquals(mongo, query(rsql).getQueryObject().toJson());
    }

    protected void check(String rsql, Consumer<Document> consumer) {
        consumer.accept(query(rsql).getQueryObject());
    }

    @SpringBootApplication
    public static class TestApplication {
        public static void main(String[] args) {
            SpringApplication.run(TestApplication.class, args);
        }
    }

}
