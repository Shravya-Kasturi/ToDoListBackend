package com.kasturi.todolist.models;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import java.util.Date;
import com.kasturi.todolist.utils.SerializeObjectId;


public class SubTask {

    @JsonSerialize(using = SerializeObjectId.class)
    private ObjectId id;

    @NotEmpty
    private String name;

    private String description;

    private Date dueDate;

    private String status;

    public SubTask() {
    }

    public SubTask(ObjectId id, String name, String description, Date dueDate, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
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


    public SubTask id(ObjectId id) {
        this.id = id;
        return this;
    }

    public SubTask name(String name) {
        this.name = name;
        return this;
    }

    public SubTask description(String description) {
        this.description = description;
        return this;
    }


    public SubTask dueDate(Date dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public SubTask status(String status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SubTask)) {
            return false;
        }
        SubTask task = (SubTask) o;
        return Objects.equals(id, task.id) && Objects.equals(name, task.name)
                && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", description='" + getDescription() + "'"
                + "}";
    }
}
