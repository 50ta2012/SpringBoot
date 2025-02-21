package com.example.jpa_many_to_one.entity;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PassRecordRepository extends CrudRepository<PassRecord, String> {
  List<PassRecord> findAll();

  List<PassRecord> findByStudent(Student student);
}
