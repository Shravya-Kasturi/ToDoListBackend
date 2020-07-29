package com.kasturi.todolist.db;

import com.mongodb.MongoClient;

import io.dropwizard.lifecycle.Managed;

public class ManageMongoConnection implements Managed {

    /** The mongoDB client. */
    private MongoClient mongoClient;

    /**
     * Constructor.
     * 
     * @param mongoClient the mongoDB client.
     */
    public ManageMongoConnection(final MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void start() throws Exception {
    }

    @Override
    public void stop() throws Exception {
        mongoClient.close();
    }

}