package com.example.test_transactional.service;

import com.example.test_transactional.entity.TestEntity;
import com.example.test_transactional.repository.TestEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParentService {

    private final TestEntityRepository repository;

    private final ChildService childService;

    private final EntityManager entityManager;

    @Transactional
    public void parentService(long seq){
        TestEntity testEntity = repository.findById(seq).get();

        try{
            childService.childService(testEntity.getSeq());
        }catch (RuntimeException e){
            log.info("error resolved");
        }

        testEntity.setName("changedName_parent");

    }
}
