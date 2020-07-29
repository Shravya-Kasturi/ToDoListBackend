package com.kasturi.todolist.models;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import com.kasturi.todolist.utils.SerializeObjectId;

public class Todo {

    @JsonSerialize(using = SerializeObjectId.class)
    private ObjectId id;

    @NotEmpty
    private String name;

    private String description;

    private List<SubTask> tasks = new ArrayList<SubTask>();

    private Date dueDate;

    private String status;
    
    private Date completionDate;
    
    private ObjectId category;

    private String position;

    public Todo() {
    }

    public Todo(ObjectId id, String name, String description, List<SubTask> tasks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tasks = tasks;
    }

    public ObjectId getId() {
        return this.id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SubTask> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<SubTask> tasks) {
        this.tasks = tasks;
    }

    public Todo id(ObjectId id) {
        this.id = id;
        return this;
    }

    public Todo name(String name) {
        this.name = name;
        return this;
    }

    public Todo description(String description) {
        this.description = description;
        return this;
    }

    public Todo tasks(List<SubTask> tasks) {
        this.tasks = tasks;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Todo)) {
            return false;
        }
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id) && Objects.equals(name, todo.name)
                && Objects.equals(description, todo.description) && Objects.equals(tasks, todo.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, tasks);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", description='" + getDescription() + "'"
                + ", tasks='" + getTasks() + "'" + "}";
    }

    public Todo(ObjectId id, String name, String description, List<SubTask> tasks, Date dueDate, String status, Date completionDate, ObjectId category, String position) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tasks = tasks;
        this.dueDate = dueDate;
        this.status = status;
        this.completionDate = completionDate;
        this.category = category;
        this.position = position;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCompletionDate() {
        return this.completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        System.out.println(completionDate);
        this.completionDate = completionDate;
    }

    public ObjectId getCategory() {
        return this.category;
    }

    public String getPosition() {
        return this.position;
    }

    public void setCategory(ObjectId category) {
        this.category = category;
    }

    public void setPosition (String position) {
        this.position = position;
    }

    public Todo dueDate(Date dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public Todo status(String status) {
        this.status = status;
        return this;
    }

    public Todo completionDate(Date completionDate) {
        this.completionDate = completionDate;
        return this;
    }

    public Todo category(ObjectId category) {
        this.category = category;
        return this;
    }

    public Todo position(String position) {
        this.position = position;
        return this;
    }

    

}

