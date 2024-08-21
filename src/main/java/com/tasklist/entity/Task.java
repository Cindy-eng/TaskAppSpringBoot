package com.tasklist.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(length = 128, nullable = false)
  private String title;

  @Column(length = 256)
  private String description;

  @Column(nullable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate date;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateTask;

  @Column
  private boolean completed;

  public Task() {

  }

  public Task(String title, String description, LocalDate date, boolean completed) {
    this.title = title;
    this.description = description;
    this.date = date;
    this.completed = completed;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public LocalDate getDateTask() {
    return dateTask;
  }

  public void setDateTask(LocalDate dateTask) {
    this.dateTask = dateTask;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  @Override
  public String toString() {
    return "Task [id=" + id + ", title=" + title + ", description=" + description + ", Date=" + date
            +", dateTask=" + dateTask + ", completed=" + completed + "]";
  }

}
