package com.example.demo;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Student")
@Data
public class Student implements Serializable {

  private String id;
  private String name;
  private Gender gender;
  private int grade;

  public Student(String id,
      String name,
      Gender gender,
      int grade) {
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.grade = grade;
  }

  public enum Gender {
    MALE, FEMALE
  }
}
