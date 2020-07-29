package com.kasturi.todolist.db;

/**
 * This has the configuration to connect to MongoDB database.
 */
public class MongoConnection {

    /**
     * The db.
     */
    private String database;

    /**
     * mongoClientUri.
     */
    private String mongoClientUri;

    /**
     * Gets the database.
     *
     * @return the value database.
     */
    public String getDatabase() {
        return database;
    }

    /**
     * Sets the database.
     *
     * @param database value.
     */
    public void setDatabase(final String database) {
        this.database = database;
    }

    /**
     * Gets the mongoClientUri.
     *
     * @return the value mongoClientUri.
     */
    public String getMongoClientUri() {
        return mongoClientUri;
    }

    /**
     * Sets the mongoClientUri.
     *
     * @param mongoClientUri value.
     */
    public void setMongoClientUri(final String mongoClientUri) {
        this.mongoClientUri = mongoClientUri;
    }

    @Override
    public String toString() {
        return "MongoDBConnection{" + ", database='" + database + '\'' + ", mongoClientUri='" + mongoClientUri + '\''
                + '}';
    }
}

