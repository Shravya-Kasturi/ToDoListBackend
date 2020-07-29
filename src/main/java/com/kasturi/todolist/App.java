package com.kasturi.todolist;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.kasturi.todolist.resources.TodoResource;
import com.kasturi.todolist.resources.CategoryResource;
import com.kasturi.todolist.db.ManageMongoConnection;
import com.kasturi.todolist.daos.TodoDAO;
import com.kasturi.todolist.daos.CategoryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import javax.servlet.DispatcherType;
import java.util.EnumSet;
import javax.servlet.FilterRegistration;

public class App extends Application<AppConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void run(AppConfiguration c, Environment e) throws Exception {

        final FilterRegistration.Dynamic cors =
        e.servlets().addFilter("CORS", CrossOriginFilter.class);

    // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

    // Add URL mapping
    cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        /**
         * Create a single instance of mongo client to use it for database operations
         */
        MongoClient mongoClient = new MongoClient(new MongoClientURI(c.getMongoConnection().getMongoClientUri()));

        ManageMongoConnection mongoManaged = new ManageMongoConnection(mongoClient);
        /**
         * Pass Mongo Category Collection reference to the category resources to make CRUD
         * operations
         */
        logger.info("Registering REST apis for category");
        final CategoryDAO categoryDAO = new CategoryDAO(
                mongoClient.getDatabase(c.getMongoConnection().getDatabase()).getCollection("categories"));

        /**
         * Pass Mongo Todo Collection reference to the todo resources to make CRUD
         * operations
         */
        logger.info("Registering REST apis for todo");
        final TodoDAO todoDAO = new TodoDAO(
                mongoClient.getDatabase(c.getMongoConnection().getDatabase()).getCollection("todos"));

       

        e.lifecycle().manage(mongoManaged);
        e.jersey().register(new TodoResource(todoDAO, categoryDAO,e.getValidator()));
        e.jersey().register(new CategoryResource(categoryDAO, e.getValidator()));
    }

}
