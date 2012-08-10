package mcouch.ektorp.performance;

import mcouch.core.InMemoryCouchDb;
import mcouch.core.couch.database.Databases;
import mcouch.ektorp.InMemoryCouchDbInstanceFactory;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.spring.HttpClientFactoryBean;
import org.junit.Ignore;
import org.junit.Test;

public class BasicPerformanceTest {
    @Test
    @Ignore
    public void runWithCouch() throws Exception {
        HttpClientFactoryBean clientFactoryBean = new HttpClientFactoryBean();
        StdCouchDbInstance stdCouchDbInstance = new StdCouchDbInstance(clientFactoryBean.getObject());
        create(stdCouchDbInstance);
    }

    private void create(StdCouchDbInstance stdCouchDbInstance) throws Exception {
        StdCouchDbConnector dbConnector = new StdCouchDbConnector("motech-tb-adherence", stdCouchDbInstance);
        for (int i = 0; i < 1; i++) {
            SampleEntity sampleEntity = new SampleEntity("someValue", "someValue2", "someValue3", "someValue4", "someValue5", "someValue6");
            dbConnector.create(sampleEntity);
            dbConnector.get(SampleEntity.class, sampleEntity.getId());
        }
    }

    @Test
    public void runWithInMemoryCouch() throws Exception {
        Databases databases = new Databases();
        databases.create("motech-tb-adherence");
        InMemoryCouchDb inMemoryCouchDb = new InMemoryCouchDb(databases);
        StdHttpClient stdHttpClient = new StdHttpClient(inMemoryCouchDb);
        StdCouchDbInstance stdCouchDbInstance = new StdCouchDbInstance(stdHttpClient);
        create(stdCouchDbInstance);
    }
}