package com.example.jpa_many_to_one.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDTO {
  String id;
  String name;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  List<BookDTO> books;

  public AuthorDTO() {
  }

  public AuthorDTO(Author a) {
    this.id = a.id;
    this.name = a.name;
    this.createdAt = a.createdAt;
    this.updatedAt = a.updatedAt;
    this.books = a.books.stream()
        .map(book -> {
          BookDTO bookDTO = new BookDTO();
          bookDTO.setId(book.id);
          bookDTO.setTitle(book.title);
          return bookDTO;
        })
        .collect(Collectors.toList());
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public List<BookDTO> getBooks() {
    return this.books;
  }

  public void setBooks(List<BookDTO> books) {
    this.books = books;
  }
}
