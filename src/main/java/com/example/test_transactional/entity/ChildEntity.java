package com.example.test_transactional.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = "parent")
public class ChildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long seq;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentSeq")
    private ParentEntity parent;

}
