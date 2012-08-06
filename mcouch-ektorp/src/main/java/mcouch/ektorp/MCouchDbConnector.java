package mcouch.ektorp;

import mcouch.core.couch.AllDocuments;
import mcouch.core.couch.indexing.View;
import mcouch.core.couch.indexing.Indexes;
import mcouch.core.rhino.EmitFunction;
import mcouch.core.rhino.MapFunctionInterpreter;
import mcouch.ektorp.repository.MapFunction;
import mcouch.ektorp.repository.Repository;
import mcouch.ektorp.repository.RepositoryMethod;
import org.ektorp.*;
import org.ektorp.changes.ChangesCommand;
import org.ektorp.changes.ChangesFeed;
import org.ektorp.changes.DocumentChange;
import org.ektorp.http.HttpClient;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("unchecked")
public class MCouchDbConnector implements CouchDbConnector {
    private Repository repository;
    private EmitFunction emitFunction;
    private AllDocuments allDocuments = new AllDocuments();
    private Indexes indexes;

    public MCouchDbConnector(Repository repository, MapFunctionInterpreter mapFunctionInterpreter, EmitFunction emitFunction) {
        this.repository = repository;
        this.emitFunction = emitFunction;
        this.indexes = new Indexes(mapFunctionInterpreter);
    }

    @Override
    public void create(String id, Object o) {
        CouchDbDocument couchDbDocument = (CouchDbDocument) o;
        couchDbDocument.setId(id);
        allDocuments.add(id, o);
    }

    @Override
    public void create(Object o) {
        create(UUID.randomUUID().toString(), o);
    }

    /**
     * Doesn't update the document revision number
     */
    @Override
    public void update(Object o) {
        CouchDbDocument couchDbDocument = (CouchDbDocument) o;
        allDocuments.add(couchDbDocument.getId(), couchDbDocument);
    }

    @Override
    /**
     * Doesn't return the correct revision number
     */
    public String delete(Object o) {
        CouchDbDocument couchDbDocument = (CouchDbDocument) o;
        allDocuments.remove(couchDbDocument.getId());
        return null;
    }

    @Override
    public String delete(String id, String revision) {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public String copy(String sourceDocId, String targetDocId) {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public String copy(String sourceDocId, String targetDocId, String targetRevision) {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public PurgeResult purge(Map<String, List<String>> revisionsToPurge) {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public <T> T get(Class<T> c, String id) {
        Object o = allDocuments.get(id);
        if (o == null) throw new DocumentNotFoundException(id);
        return (T) o;
    }

    @Override
    public <T> T get(Class<T> c, String id, Options options) {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public <T> T find(Class<T> c, String id) {
        return (T) allDocuments.get(id);
    }

    @Override
    public <T> T find(Class<T> c, String id, Options options) {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public <T> T get(Class<T> c, String id, String rev) {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public <T> T getWithConflicts(Class<T> c, String id) {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public boolean contains(String id) {
        return allDocuments.containsKey(id);
    }

    @Override
    public InputStream getAsStream(String id) {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public InputStream getAsStream(String id, String rev) {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public InputStream getAsStream(String id, Options options) {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public List<Revision> getRevisions(String id) {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public AttachmentInputStream getAttachment(String id, String attachmentId) {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public AttachmentInputStream getAttachment(String id, String attachmentId, String revision) {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public String createAttachment(String docId, AttachmentInputStream data) {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public String createAttachment(String docId, String revision, AttachmentInputStream data) {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public String deleteAttachment(String docId, String revision, String attachmentId) {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public List<String> getAllDocIds() {
        throw new MCouchEktorpException("Not supported yet");
    }

    @Override
    public <T> List<T> queryView(ViewQuery query, Class<T> type) {
        if ("all".equals(query.getViewName())) {
            TypeDiscriminator annotation = type.getAnnotation(TypeDiscriminator.class);
            TypeDiscriminatorExpression expression = new TypeDiscriminatorExpression(annotation.value());
            String indexName = String.format("%s-%s", type.getName(), query.getViewName());
            View view = indexes.buildIndex(indexName, MapFunction.forAllDocumentsOfType(expression.typeName()), emitFunction, allDocuments);
            return allDocuments.getAll(view.all());
        }
        RepositoryMethod repositoryMethod = repository.repositoryMethod(Thread.currentThread().getStackTrace());
        return repositoryMethod.execute(query, type, allDocuments, indexes);
    }

    @Override
    public <T> Page<T> queryForPage(ViewQuery query, PageRequest pr, Class<T> type) {
        return null;
    }

    @Override
    public ViewResult queryView(ViewQuery query) {
        return null;
    }

    @Override
    public StreamingViewResult queryForStreamingView(ViewQuery query) {
        return null;
    }

    @Override
    public InputStream queryForStream(ViewQuery query) {
        return null;
    }

    @Override
    public void createDatabaseIfNotExists() {
    }

    @Override
    public String getDatabaseName() {
        return null;
    }

    @Override
    public String path() {
        return null;
    }

    @Override
    public HttpClient getConnection() {
        return null;
    }

    @Override
    public DbInfo getDbInfo() {
        return null;
    }

    @Override
    public DesignDocInfo getDesignDocInfo(String designDocId) {
        return null;
    }

    @Override
    public void compact() {
    }

    @Override
    public void compactViews(String designDocumentId) {
    }

    @Override
    public void cleanupViews() {
    }

    @Override
    public int getRevisionLimit() {
        return 0;
    }

    @Override
    public void setRevisionLimit(int limit) {
    }

    @Override
    public ReplicationStatus replicateFrom(String source) {
        return null;
    }

    @Override
    public ReplicationStatus replicateFrom(String source, Collection<String> docIds) {
        return null;
    }

    @Override
    public ReplicationStatus replicateTo(String target) {
        return null;
    }

    @Override
    public ReplicationStatus replicateTo(String target, Collection<String> docIds) {
        return null;
    }

    @Override
    public void addToBulkBuffer(Object o) {
    }

    @Override
    public List<DocumentOperationResult> flushBulkBuffer() {
        return null;
    }

    @Override
    public void clearBulkBuffer() {
    }

    @Override
    public List<DocumentOperationResult> executeBulk(InputStream inputStream) {
        return null;
    }

    @Override
    public List<DocumentOperationResult> executeAllOrNothing(InputStream inputStream) {
        return null;
    }

    @Override
    public List<DocumentOperationResult> executeBulk(Collection<?> objects) {
        return null;
    }

    @Override
    public List<DocumentOperationResult> executeAllOrNothing(Collection<?> objects) {
        return null;
    }

    @Override
    public List<DocumentChange> changes(ChangesCommand cmd) {
        return null;
    }

    @Override
    public StreamingChangesResult changesAsStream(ChangesCommand cmd) {
        return null;
    }

    @Override
    public ChangesFeed changesFeed(ChangesCommand cmd) {
        return null;
    }

    @Override
    public String callUpdateHandler(String designDocID, String function, String docId) {
        return null;
    }

    @Override
    public String callUpdateHandler(String designDocID, String function, String docId, Map<String, String> params) {
        return null;
    }

    @Override
    public <T> T callUpdateHandler(UpdateHandlerRequest req, Class<T> c) {
        return null;
    }

    @Override
    public String callUpdateHandler(UpdateHandlerRequest req) {
        return null;
    }

    @Override
    public void ensureFullCommit() {
    }

    @Override
    public void updateMultipart(String id, InputStream stream, String boundary, long length, Options options) {
    }

    @Override
    public void update(String id, InputStream document, long length, Options options) {
    }

    public static MCouchDbConnector create() {
        EmitFunction emitFunction = new EmitFunction();
        MapFunctionInterpreter mapFunctionInterpreter = new MapFunctionInterpreter(emitFunction);
        Repository repository = new Repository(emitFunction);
        return new MCouchDbConnector(repository, mapFunctionInterpreter, emitFunction);
    }
}
