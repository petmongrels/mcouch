package mcouch.core.jackson;

import mcouch.core.sample.ArrayHolder;
import org.junit.Test;
import sun.jvm.hotspot.oops.Array;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class JSONSerializerTest {
    @Test
    public void deserializeArray() {
        System.out.println(JSONSerializer.toJson(new ArrayList<String>()));
        JSONSerializer.fromJson("[]", ArrayList.class);
//        ArrayHolder arrayList = JSONSerializer.fromJson("{\"a\":[]}", ArrayHolder.class);
//        assertEquals(0, arrayList.size());
    }

}