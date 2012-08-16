package mcouch.core.couch.reducers;

import mcouch.core.http.NotImplementedException;

public class ReducerFactory {
    public static Reducer create(String reduceFunction) {
        if (reduceFunction == null) return null;
        switch (reduceFunction) {
            case "_count":
                return new CountReducer();
        }
        throw new NotImplementedException();
    }
}