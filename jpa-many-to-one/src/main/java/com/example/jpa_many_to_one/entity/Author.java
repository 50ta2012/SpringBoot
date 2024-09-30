package com.example.jpa_many_to_one.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Author {
  @Id
  @UuidGenerator
  String id;

  String name;

  @CreationTimestamp
  LocalDateTime createdAt;

  @UpdateTimestamp
  LocalDateTime updatedAt;

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  List<Book> books;

  public Author setAuthorDTO(Author a) {
    this.id = a.id;
    this.name = a.name;
    this.createdAt = a.createdAt;
    this.updatedAt = a.updatedAt;
    this.books = a.books.stream()
        .map(book -> {
          Book bookDTO = new Book();
          bookDTO.setId(book.id);
          bookDTO.setTitle(book.title);
          return bookDTO;
        })
        .collect(Collectors.toList());

    return this;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
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

  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public List<Book> getBooks() {
    return this.books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;

    for (Book book : books) {
      book.setAuthor(this);
    }
  }
}
