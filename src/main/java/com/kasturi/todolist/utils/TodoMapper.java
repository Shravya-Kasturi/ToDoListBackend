package com.kasturi.todolist.utils;

import com.kasturi.todolist.models.SubTask;
import com.kasturi.todolist.models.Todo;

import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Mapper class for Todo objects.
 */
public class TodoMapper {

    /**
     * Map objects {@link Document} to {@link Todo}.
     *
     * @param todoDocument the information document.
     * @return A object {@link Todo}.
     */
    public static Todo map(final Document todoDocument) {
        Todo todo = new Todo();
        todo.setId(todoDocument.getObjectId("_id"));
        todo.setName(todoDocument.getString("name"));
        todo.setDescription(todoDocument.getString("description"));
        todo.setDueDate(todoDocument.getDate("dueDate"));
        todo.setStatus(todoDocument.getString("status"));
        todo.setCompletionDate(todoDocument.getDate("completionDate"));
        todo.setCategory(todoDocument.getObjectId("category"));
        todo.setPosition(todoDocument.getString("position"));
        List<SubTask> tasks = new ArrayList<SubTask>();
        @SuppressWarnings("unchecked")
        ArrayList<Document> tasksList = (ArrayList<Document>) todoDocument.get("tasks");
        if (tasksList.size() > 0) {
            for (Document task : tasksList) {
                SubTask taskObject = new SubTask();
                taskObject.setId((ObjectId) task.get("_id"));
                taskObject.setName((String) task.get("name"));
                taskObject.setDescription((String) task.get("description"));
                taskObject.setDueDate((Date) task.get("dueDate"));
                taskObject.setStatus((String) task.get("status"));
                tasks.add(taskObject);
            }
        }
        todo.setTasks(tasks);
        return todo;
    }
}