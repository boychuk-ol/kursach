package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "task_id")
    private Long taskId;
    private String title;
}
