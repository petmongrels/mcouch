package mcouch.ektorp;

import mcouch.core.InMemoryCouchDb;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

public class InMemoryCouchDbInstanceFactory {
    public static StdCouchDbInstance create() {
        InMemoryCouchDb inMemoryCouchDb = new InMemoryCouchDb();
        StdHttpClient stdHttpClient = new StdHttpClient(inMemoryCouchDb);
        return new StdCouchDbInstance(stdHttpClient);
    }
}