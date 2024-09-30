package com.example.jpa_many_to_one.entity;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {
  @Id
  @UuidGenerator
  String id;

  String title;

  @ManyToOne
  @JoinColumn(name = "author_id")
  Author author;

  @Transient
  String authorId;

  public Book setBookDTO(Book book) {
    this.id = book.id;
    this.title = book.title;
    this.authorId = book.author.id;
    return this;
  }

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
