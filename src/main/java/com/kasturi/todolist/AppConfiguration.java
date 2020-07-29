package com.kasturi.todolist;

import com.kasturi.todolist.db.MongoConnection;
import io.dropwizard.Configuration;

public class AppConfiguration extends Configuration {

    /**
     * The data configuration for MongoDB.
     */
    private MongoConnection mongoConnection;

    /**
     * Gets the mongoDBConnection.
     *
     * @return the value mongoDbConnection.
     */
    public MongoConnection getMongoConnection() {
        return mongoConnection;
    }

    /**
     * Sets the mongoDBConnection.
     *
     * @param mongoConnection value.
     */
    public void setMongoConnection(final MongoConnection mongoConnection) {
        this.mongoConnection = mongoConnection;
    }
}
