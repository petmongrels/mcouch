package mcouch.core.integration;

import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.spring.HttpClientFactoryBean;

public class CouchDbInstanceFactoryForTest {
    public static StdCouchDbConnector create(String database) throws Exception {
        HttpClientFactoryBean clientFactoryBean = new HttpClientFactoryBean();
        StdCouchDbInstance stdCouchDbInstance = new StdCouchDbInstance(clientFactoryBean.getObject());
        return new StdCouchDbConnector(database, stdCouchDbInstance);
    }
}