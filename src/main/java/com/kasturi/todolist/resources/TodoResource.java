package com.kasturi.todolist.resources;

import java.util.ArrayList;
import java.util.Set;
import java.util.List;

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

import com.kasturi.todolist.daos.TodoDAO;
import com.kasturi.todolist.daos.CategoryDAO;
import com.kasturi.todolist.models.SubTask;
import com.kasturi.todolist.models.Todo;
import com.kasturi.todolist.models.TaskResponse;
import org.bson.types.ObjectId;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {
    private TodoDAO todoDAO;
    private CategoryDAO categoryDAO;

    private final Validator validator;

    public TodoResource(final TodoDAO todoDAO, final CategoryDAO categoryDAO,final Validator validator) {
        this.todoDAO = todoDAO;
        this.categoryDAO = categoryDAO;
        this.validator = validator;
    }

    /**
     * 
     * @return List<Todos>
     */
    @GET
    public Response getTodos() {
        List<TaskResponse> tasksList = new ArrayList<TaskResponse>();
        todoDAO.getAll().forEach((todo) ->{
            TaskResponse response = new TaskResponse(todo,categoryDAO.getOne(todo.getCategory()));
            tasksList.add(response);
        });
        return Response.ok(tasksList).build();
    }

    /**
     * 
     * @param todo
     * @return todo
     */
    @POST
    public Response createTodo(Todo todo) {
        Set<ConstraintViolation<Todo>> todoViolations = validator.validate(todo);
        if (todoViolations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<Todo> violation : todoViolations) {
                validationMessages
                        .add("Task: " + violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
        } else {
            ArrayList<String> validationMessages = new ArrayList<String>();
            todo.getTasks().forEach((task) -> {
                Set<ConstraintViolation<SubTask>> taskViolation = validator.validate(task);
                if (taskViolation.size() > 0) {
                    for (ConstraintViolation<SubTask> violation : taskViolation) {
                        validationMessages.add(
                                "SubTask: " + violation.getPropertyPath().toString() + ": " + violation.getMessage());
                    }
                }
            });
            if (validationMessages.size() > 0) {
                return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
            } else {
                Todo savedDocument = todoDAO.save(todo);
                TaskResponse response = new TaskResponse(savedDocument,categoryDAO.getOne(savedDocument.getCategory()));
                return Response.ok(response).build();
            }
        }
    }

    /**
     * 
     * @param id
     * @return todo with id
     */
    @GET
    @Path("/{id}")
    public Response getTodoByID(@PathParam("id") ObjectId id) {
        Todo todo = todoDAO.getOne(id);
        if (todo != null) {
            TaskResponse response = new TaskResponse(todo,categoryDAO.getOne(todo.getCategory()));
            return Response.ok(response).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    /**
     * 
     * @param id
     * @param todo updates todo with id
     */
    @PUT
    @Path("/{id}")
    public Response updateTodoById(@PathParam("id") ObjectId id, Todo todo) {
        Set<ConstraintViolation<Todo>> todoViolations = validator.validate(todo);
        if (todoViolations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<Todo> violation : todoViolations) {
                validationMessages
                        .add("Task: " + violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
        } else {
            ArrayList<String> validationMessages = new ArrayList<String>();
            todo.getTasks().forEach((task) -> {
                Set<ConstraintViolation<SubTask>> taskViolation = validator.validate(task);
                if (taskViolation.size() > 0) {
                    for (ConstraintViolation<SubTask> violation : taskViolation) {
                        validationMessages.add(
                                "SubTask: " + violation.getPropertyPath().toString() + ": " + violation.getMessage());
                    }
                }
            });
            if (validationMessages.size() > 0) {
                return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
            } else {
                todoDAO.update(id, todo);
                if (todo != null) {
                    TaskResponse response = new TaskResponse(todo,categoryDAO.getOne(todo.getCategory()));
                    return Response.ok(response).build();
                } else {
                    return Response.status(Status.NOT_FOUND).build();
                }
            }
        }
    }

    /**
     * 
     * @param id deleted todo with id
     */
    @DELETE
    @Path("/{id}")
    public Response removeTodoById(@PathParam("id") ObjectId id) {
        todoDAO.delete(id);
        return Response.ok().build();
    }

}