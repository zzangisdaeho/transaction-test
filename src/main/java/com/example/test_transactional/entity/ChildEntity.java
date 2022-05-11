package com.example.test_transactional.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ChildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long seq;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentSeq")
    private ParentEntity parent;

}
