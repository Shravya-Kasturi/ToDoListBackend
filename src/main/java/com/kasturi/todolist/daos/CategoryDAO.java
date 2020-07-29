package com.kasturi.todolist.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.UpdateResult;
import com.kasturi.todolist.models.Category;
import com.kasturi.todolist.utils.CategoryMapper;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Data Access Object for objects of type {@link Category}.
 */
public class CategoryDAO {

    /**
     * The collection of Categories
     */
    final MongoCollection<Document> categoryCollection;

    /**
     * Constructor.
     *
     * @param categoryCollection the collection of Categories.
     */
    public CategoryDAO(final MongoCollection<Document> categoryCollection) {
        this.categoryCollection = categoryCollection;
    }

    /**
     * Find all Categories.
     *
     * @return the Categories.
     */
    public List<Category> getAll() {
        final MongoCursor<Document> categories = categoryCollection.find().iterator();
        final List<Category> categoriesFind = new ArrayList<>();
        try {
            while (categories.hasNext()) {
                final Document category = categories.next();
                categoriesFind.add(CategoryMapper.map(category));
            }
        } finally {
            categories.close();
        }
        return categoriesFind;
    }

    /**
     * Get one document find in other case return null.
     *
     * @param id the identifier for find.
     * @return the Category find.
     */
    public Category getOne(final ObjectId id) {
        final Optional<Document> categoryFind = Optional.ofNullable(categoryCollection.find(new Document("_id", id)).first());
        return categoryFind.isPresent() ? CategoryMapper.map(categoryFind.get()) : null;
    }

    /**
     * Save one Category document
     *
     * @param category the item to be saved
     * @return
     * @return
     * @return the Category.
     */
    public Category save(final Category category) {
        final Document saveCategory = new Document("name", category.getName()).append("description", category.getDescription());
             
        categoryCollection.insertOne(saveCategory);
        category.setId(saveCategory.getObjectId("_id"));
        return category;
    }

    /**
     * Update a Category.
     *
     * @param id   the identifier.
     * @param category the object to update.
     * @return
     */
    public UpdateResult update(final ObjectId id, final Category category) {
        return categoryCollection.updateOne(new Document("_id", id), 
        new Document("$set", new Document("name", category.getName()).append("description", category.getDescription())));
    }

    /**
     * Delete a Category.
     * 
     * @param id the identifier.
     */
    public void delete(final ObjectId id) {
        categoryCollection.deleteOne(new Document("_id", id));
    }
}