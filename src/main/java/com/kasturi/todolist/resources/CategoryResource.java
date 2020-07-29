package com.kasturi.todolist.resources;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.kasturi.todolist.models.Category;
import com.kasturi.todolist.daos.CategoryDAO;
import org.bson.types.ObjectId;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {
    private CategoryDAO categoryDAO;

    private final Validator validator;

    public CategoryResource(final CategoryDAO categoryDAO, final Validator validator) {
        this.categoryDAO = categoryDAO;
        this.validator = validator;
    }

    /**
     * 
     * @return List<Categories>
     */
    @GET
    public Response getCategories() {
        return Response.ok(categoryDAO.getAll()).build();
    }

    /**
     * 
     * @param category
     * @return category
     */
    @POST
    public Response createCategory(Category category) {
        Set<ConstraintViolation<Category>> categoryViolations = validator.validate(category);
        if (categoryViolations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<Category> violation : categoryViolations) {
                validationMessages
                        .add("Category: " + violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
        } 
                Category savedDocument = categoryDAO.save(category);
                return Response.ok(savedDocument).build();
            
        
    }

    /**
     * 
     * @param id
     * @return category with id
     */
    @GET
    @Path("/{id}")
    public Response getCategoryByID(@PathParam("id") ObjectId id) {
        Category category = categoryDAO.getOne(id);
        if (category != null) {
            return Response.ok(category).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    /**
     * 
     * @param id
     * @param category updates category with id
     */
    @PUT
    @Path("/{id}")
    public Response updateCategoryById(@PathParam("id") ObjectId id, Category category) {
        Set<ConstraintViolation<Category>> categoryViolations = validator.validate(category);
        if (categoryViolations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<Category> violation : categoryViolations) {
                validationMessages
                        .add("Category: " + violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
        } 
                categoryDAO.update(id, category);
                return Response.ok(category).build();
    }

    /**
     * 
     * @param id deleted category with id
     */
    @DELETE
    @Path("/{id}")
    public Response removeCategoryById(@PathParam("id") ObjectId id) {
        categoryDAO.delete(id);
        return Response.ok().build();
    }

}