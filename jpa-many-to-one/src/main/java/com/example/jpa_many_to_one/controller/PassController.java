package com.example.jpa_many_to_one.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.jpa_many_to_one.entity.PassRecord;
import com.example.jpa_many_to_one.entity.PassRecordRepository;
import com.example.jpa_many_to_one.entity.Student;
import com.example.jpa_many_to_one.entity.StudentRepository;

@RestController
@CrossOrigin
public class PassController {

  @Autowired
  StudentRepository studentRepository;

  @Autowired
  PassRecordRepository passRecordRepository;

  @GetMapping("/student/all")
  public List<Student> getAllStudent() {
    return studentRepository.findAll();
  }

  @PostMapping("/student/add")
  public Student addStudent(@RequestBody Student student) {
    return studentRepository.save(student);
  }

  @PostMapping("/student/update")
  public Student updateStudent(@RequestBody Student student) {
    Optional<Student> studentOptional = studentRepository.findById(student.getId());

    if (studentOptional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    Student studentInDb = studentOptional.get();
    studentInDb.setName(student.getName());
    return studentRepository.save(studentInDb);
  }

  // *** Delete Student ***//
  @DeleteMapping("/student/{studentId}")
  public void deleteStudentById(@PathVariable(name = "studentId") String studentId) {
    Optional<Student> studentOptional = studentRepository.findById(studentId);

    if (studentOptional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    Student studentInDb = studentOptional.get();

    // Remove relation with records and student
    List<PassRecord> records = passRecordRepository.findByStudent(studentInDb);
    records = records.stream()
        .map(record -> {
          record.setStudent(null);
          return record;
        })
        .collect(Collectors.toList());
    passRecordRepository.saveAll(records);

    studentRepository.delete(studentInDb);
  }

  @GetMapping("/record/all")
  public List<PassRecord> getAllRecord() {
    return passRecordRepository.findAll();
  }

  @PostMapping("/record/add")
  public PassRecord addRecord(@RequestBody PassRecord passRecord) {
    Optional<Student> studentOptional = studentRepository.findById(passRecord.getStudentId());

    if (studentOptional.isEmpty()) {
      return passRecordRepository.save(passRecord);
    }

    Student student = studentOptional.get();
    passRecord.setStudent(student);
    return passRecordRepository.save(passRecord);
  }

  @DeleteMapping("/record/{recordId}")
  public void deleteRecordById(@PathVariable(name = "recordId") String recordId) {
    Optional<PassRecord> passRecordOptional = passRecordRepository.findById(recordId);

    if (passRecordOptional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    passRecordRepository.delete(passRecordOptional.get());
  }

  /* 測試把 relation 連回去 */
  @PostMapping("/record/relate")
  public PassRecord relateRecord(@RequestBody PassRecord passRecord) {
    Optional<Student> studentOptional = studentRepository.findById(passRecord.getStudentId());
    Optional<PassRecord> passRecordOptional = passRecordRepository.findById(passRecord.getId());

    if (studentOptional.isEmpty() || passRecordOptional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    Student studentInDb = studentOptional.get();
    PassRecord passRecordInDb = passRecordOptional.get();

    passRecordInDb.setStudent(studentInDb);
    return passRecordRepository.save(passRecordInDb);
  }
}
