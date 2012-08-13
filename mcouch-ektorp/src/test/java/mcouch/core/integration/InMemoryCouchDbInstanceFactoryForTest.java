package mcouch.core.integration;

import mcouch.core.InMemoryCouchDb;
import mcouch.core.couch.database.Databases;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

public class InMemoryCouchDbInstanceFactoryForTest {
    public static StdCouchDbConnector create(String databaseName) {
        Databases databases = new Databases();
        databases.create(databaseName);
        InMemoryCouchDb inMemoryCouchDb = new InMemoryCouchDb(databases);
        StdHttpClient stdHttpClient = new StdHttpClient(inMemoryCouchDb);
        StdCouchDbInstance stdCouchDbInstance = new StdCouchDbInstance(stdHttpClient);
        return new StdCouchDbConnector(databaseName, stdCouchDbInstance);
    }
}