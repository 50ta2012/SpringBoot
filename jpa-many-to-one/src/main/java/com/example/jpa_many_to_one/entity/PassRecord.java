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
public class PassRecord {
  @Id
  @UuidGenerator
  String id;

  String plateNumber;

  @ManyToOne
  @JoinColumn(name = "student_id", nullable = true)
  Student student;

  @Transient
  String studentId;

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPlateNumber() {
    return this.plateNumber;
  }

  public void setPlateNumber(String plateNumber) {
    this.plateNumber = plateNumber;
  }

  public Student getStudent() {
    return this.student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public String getStudentId() {
    return this.studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

}
