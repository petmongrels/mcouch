package mcouch.core.couch.view;

import mcouch.core.TestContext;
import org.junit.Assert;
import org.junit.Test;

public class ViewGroupDefinitionTest {
    @Test
    public void testCreate() throws Exception {
        String document = "{\"views\":{\"by_a\":{\"map\":\"function(doc) { if(doc.type == 'Sample' && doc.a) {emit(doc.a, doc._id)} }\"}},\"lists\":{},\"shows\":{},\"language\":\"javascript\",\"filters\":{},\"updates\":{},\"_id\":\"_design/SampleEntity\"}";
        ViewGroupDefinition viewGroupDefinition = ViewGroupDefinition.create(document, TestContext.DOCUMENT_FUNCTIONS);
        Assert.assertNotNull(viewGroupDefinition.getView("by_a"));
    }
}