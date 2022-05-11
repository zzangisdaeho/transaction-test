package com.example.test_transactional.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long seq;

    private String name;

    private String name2;
}
