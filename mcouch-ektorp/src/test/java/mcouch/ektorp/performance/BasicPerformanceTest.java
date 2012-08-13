package mcouch.ektorp.performance;

import mcouch.core.integration.InMemoryCouchDbInstanceFactoryForTest;
import mcouch.testapp.SampleEntity;
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
        create(dbConnector);
    }

    private void create(StdCouchDbConnector dbConnector) {
        for (int i = 0; i < 1; i++) {
            SampleEntity sampleEntity = new SampleEntity("someValue");
            dbConnector.create(sampleEntity);
            dbConnector.get(SampleEntity.class, sampleEntity.getId());
        }
    }

    @Test
    public void runWithInMemoryCouch() throws Exception {
        create(InMemoryCouchDbInstanceFactoryForTest.create("motech-tb-adherence"));
    }
}