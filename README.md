mCouch
======

Provides in memory implementation of couch db which can be used in unit testing to speed up the performance of your tests. No meant to be used for production. It supports:
- PUT, POST, DELETE and GET of documents.
- Bulk API, add, update and delete of documents.
- Execution of views.
- Supports count as standard reducer.

Does not implement.
- Replication
- Document conflict checks
- Custom reducers

It uses Rhino to interpret the Java script map functions.
It uses Jackson for JSON parsing.
Based on stubbing of Apache Commons HTTP client.