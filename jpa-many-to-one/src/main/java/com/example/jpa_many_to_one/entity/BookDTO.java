package com.example.jpa_many_to_one.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {
  String id;
  String title;
  String authorId;

  public BookDTO() {
  }

  public BookDTO(Book book) {
    this.id = book.id;
    this.title = book.title;
    this.authorId = book.author.id;
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

  public String getAuthorId() {
    return this.authorId;
  }

  public void setAuthorId(String authorId) {
    this.authorId = authorId;
  }
}
