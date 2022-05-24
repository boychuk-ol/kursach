package com.example.demo.Task;

import com.example.demo.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long taskId;

    private String title, category;
    private boolean isImportant;
    private boolean isDone;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    @Autowired
    public Task(String title, String category, boolean isImportant, boolean isDone) {
        this.title = title;
        this.category = category;
        this.isImportant = isImportant;
        this.isDone = isDone;
    }

    @Autowired
    public Task(String title, String category, boolean isImportant, boolean isDone, User user) {
        this.title = title;
        this.category = category;
        this.user = user;
        this.isImportant = isImportant;
        this.isDone = isDone;
    }

    @Autowired
    public Task() {
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        this.isImportant = important;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}