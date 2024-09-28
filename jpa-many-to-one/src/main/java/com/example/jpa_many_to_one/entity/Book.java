package com.example.jpa_many_to_one.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {
  @Id
  @UuidGenerator
  String id;

  String title;

  @ManyToOne
  @JoinColumn(name = "author_id")
  Author author;

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Author getAuthor() {
    return this.author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }
}
