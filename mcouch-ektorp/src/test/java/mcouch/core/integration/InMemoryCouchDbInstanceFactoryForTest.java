package mcouch.core.integration;

import mcouch.core.InMemoryCouchDb;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

public class InMemoryCouchDbInstanceFactoryForTest {
    public static StdCouchDbConnector create(String databaseName) {
        InMemoryCouchDb inMemoryCouchDb = new InMemoryCouchDb();
        inMemoryCouchDb.createDatabase(databaseName);
        StdHttpClient stdHttpClient = new StdHttpClient(inMemoryCouchDb);
        StdCouchDbInstance stdCouchDbInstance = new StdCouchDbInstance(stdHttpClient);
        return new StdCouchDbConnector(databaseName, stdCouchDbInstance);
    }
}