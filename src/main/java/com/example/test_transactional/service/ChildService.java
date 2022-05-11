package com.example.test_transactional.service;

import com.example.test_transactional.entity.TestEntity;
import com.example.test_transactional.repository.TestEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final TestEntityRepository repository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void childService(long seq){
        TestEntity testEntity1 = repository.findById(seq).get();
        testEntity1.setName2("changedName_child");

        throw new RuntimeException("runtime error occur");

    }
}
