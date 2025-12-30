package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

/**
* ユーザー情報 Entity
*/
@Data
@Entity
@Table(name = "subject")
public class SubjectEntity  {

  /**
   * ID
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  /**
   * 科目
   */
  @Column(name = "subject")
  private String subject;
}