package com.example.jpa_many_to_one.entity;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
  List<Book> findAll();
}
