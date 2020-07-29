package com.kasturi.todolist.utils;

import com.kasturi.todolist.models.Category;

import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class for Category objects.
 */
public class CategoryMapper {

    /**
     * Map objects {@link Document} to {@link Category}.
     *
     * @param categoryDocument the information document.
     * @return A object {@link Category}.
     */
    public static Category map(final Document categoryDocument) {
        Category category = new Category();
        category.setId(categoryDocument.getObjectId("_id"));
        category.setName(categoryDocument.getString("name"));
        category.setDescription(categoryDocument.getString("description"));
        return category;
    }
}