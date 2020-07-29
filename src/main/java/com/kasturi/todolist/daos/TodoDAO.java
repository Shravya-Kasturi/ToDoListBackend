package com.kasturi.todolist.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.UpdateResult;
import com.kasturi.todolist.models.SubTask;
import com.kasturi.todolist.models.Todo;
import com.kasturi.todolist.utils.TodoMapper;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Data Access Object for objects of type {@link Todo}.
 */
public class TodoDAO {

    /**
     * The collection of Todos
     */
    final MongoCollection<Document> todoCollection;

    // /**
    //  * The DAO for category
    //  */
    // final CategoryDAO categoryDAO;

    /**
     * Constructor.
     *
     * @param todoCollection the collection of todos.
     * 
     */
    public TodoDAO(final MongoCollection<Document> todoCollection) {
        this.todoCollection = todoCollection;
    }

    /**
     * Find all todos.
     *
     * @return the todos.
     */
    public List<Todo> getAll() {
        final MongoCursor<Document> todos = todoCollection.find().iterator();
        final List<Todo> todosFind = new ArrayList<>();
        try {
            while (todos.hasNext()) {
                final Document todo = todos.next();
                todosFind.add(TodoMapper.map(todo));
            }
        } finally {
            todos.close();
        }
        return todosFind;
    }

    /**
     * Get one document find in other case return null.
     *
     * @param id the identifier for find.
     * @return the Todo find.
     */
    public Todo getOne(final ObjectId id) {
        final Optional<Document> todoFind = Optional.ofNullable(todoCollection.find(new Document("_id", id)).first());
        // final ObjectId categoryId = todoFind.get().getObjectId("category");
        // System.out.println("categoryId :"+categoryId);
        // todoFind.get().remove("category");
        // System.out.println("category obj :"+categoryDAO.getOne(categoryId));
        // todoFind.get().append("category",categoryDAO.getOne(categoryId));
        // System.out.println("todo obj after :"+todoFind.get());
        return todoFind.isPresent() ? TodoMapper.map(todoFind.get()) : null;
    }

    /**
     * Save one todo document
     *
     * @param todo the item to be saved
     * @return
     * @return
     * @return the Todo.
     */
    public Todo save(final Todo todo) {
        List<Document> tasksList = new ArrayList<Document>();
        if (todo.getTasks().size() > 0) {
            for (SubTask task : todo.getTasks()) {
                ObjectId subTaskObjectId = new ObjectId();
                task.setId(subTaskObjectId);
                Document taskObj = new Document("name", task.getName()).append("description", task.getDescription())
                        .append("_id", subTaskObjectId).append("dueDate",task.getDueDate()).append("status",task.getStatus());
                tasksList.add(taskObj);
            }
        }
        final Document saveTodo = new Document("name", todo.getName()).append("description", todo.getDescription())
                .append("tasks", tasksList).append("dueDate",todo.getDueDate()).append("status",
                todo.getStatus()).append("completionDate",null).append("category",
                todo.getCategory()).append("position",todo.getPosition());
        todoCollection.insertOne(saveTodo);
        todo.setId(saveTodo.getObjectId("_id"));
        return todo;
    }

    /**
     * Update a Todo.
     *
     * @param id   the identifier.
     * @param todo the object to update.
     * @return
     */
    public UpdateResult update(final ObjectId id, final Todo todo) {
        List<Document> tasksList = new ArrayList<Document>();
        if (todo.getTasks().size() > 0) {
            for (SubTask task : todo.getTasks()) {
                Document taskObj = new Document("name", task.getName()).append("description", task.getDescription())
                        .append("_id", task.getId()).append("dueDate",task.getDueDate()).append("status",task.getStatus());
                tasksList.add(taskObj);
            }
        }
        return todoCollection.updateOne(new Document("_id", id),
                new Document("$set", new Document("name", todo.getName()).append("description", todo.getDescription())
                        .append("tasks", tasksList).append("dueDate",todo.getDueDate()).append("status",
                        todo.getStatus()).append("completionDate",todo.getCompletionDate()).append("category",
                        todo.getCategory()).append("position",todo.getPosition())));
    }

    /**
     * Delete a Todo.
     * 
     * @param id the identifier.
     */
    public void delete(final ObjectId id) {
        todoCollection.deleteOne(new Document("_id", id));
    }
}