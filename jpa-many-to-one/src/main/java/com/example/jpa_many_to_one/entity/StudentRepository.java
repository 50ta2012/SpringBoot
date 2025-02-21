package com.example.jpa_many_to_one.entity;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {
  List<Student> findAll();
}
