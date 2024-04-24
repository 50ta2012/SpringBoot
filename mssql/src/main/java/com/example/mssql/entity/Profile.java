package com.example.mssql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Profile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  String name;
  Integer age;
  String tag;
}
