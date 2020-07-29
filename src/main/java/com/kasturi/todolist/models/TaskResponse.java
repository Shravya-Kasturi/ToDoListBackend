package com.kasturi.todolist.models;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import com.kasturi.todolist.utils.SerializeObjectId;

public class TaskResponse {

    @JsonSerialize(using = SerializeObjectId.class)
    private ObjectId id;

    @NotEmpty
    private String name;

    private String description;

    private List<SubTask> tasks = new ArrayList<SubTask>();

    private Date dueDate;

    private String status;
    
    private Date completionDate;
    
    private Category category;

    private String position;

    public TaskResponse() {
    }

    public TaskResponse(Todo todo, Category category) {
        this.id = todo.getId();
        this.name = todo.getName();
        this.description = todo.getDescription();
        this.dueDate = todo.getDueDate();
        this.status = todo.getStatus();
        this.completionDate = todo.getCompletionDate();
        this.tasks = todo.getTasks();
        this.position = todo.getPosition();
        this.category = category;
    }

    public TaskResponse(ObjectId id, String name, String description, List<SubTask> tasks, Date dueDate, String status, Date completionDate, String position,Category category) {
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
        this.completionDate = completionDate;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public TaskResponse id(ObjectId id) {
        this.id = id;
        return this;
    }

    public TaskResponse name(String name) {
        this.name = name;
        return this;
    }

    public TaskResponse description(String description) {
        this.description = description;
        return this;
    }

    public TaskResponse tasks(List<SubTask> tasks) {
        this.tasks = tasks;
        return this;
    }

    public TaskResponse dueDate(Date dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public TaskResponse status(String status) {
        this.status = status;
        return this;
    }

    public TaskResponse completionDate(Date completionDate) {
        this.completionDate = completionDate;
        return this;
    }

    public TaskResponse category(Category category) {
        this.category = category;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TaskResponse)) {
            return false;
        }
        TaskResponse taskResponse = (TaskResponse) o;
        return Objects.equals(id, taskResponse.id) && Objects.equals(name, taskResponse.name) && Objects.equals(description, taskResponse.description) && Objects.equals(tasks, taskResponse.tasks) && Objects.equals(dueDate, taskResponse.dueDate) && Objects.equals(status, taskResponse.status) && Objects.equals(completionDate, taskResponse.completionDate) && Objects.equals(category, taskResponse.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, tasks, dueDate, status, completionDate, category);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", tasks='" + getTasks() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", completionDate='" + getCompletionDate() + "'" +
            ", category='" + getCategory() + "'" +
            "}";
    }

}

