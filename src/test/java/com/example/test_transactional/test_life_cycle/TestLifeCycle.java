package com.example.test_transactional.test_life_cycle;

import com.example.test_transactional.entity.ChildEntity;
import com.example.test_transactional.entity.ParentEntity;
import com.example.test_transactional.entity.TestEntity;
import com.example.test_transactional.repository.ParentEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
public class TestLifeCycle {

    @Autowired
    ParentEntityRepository parentEntityRepository;

    @Autowired
    EntityManager entityManager;

    private long savedParentId;

    private long child1Id;

    private long child2Id;

    @BeforeEach
    void saveTestData(){
        ChildEntity child1 = new ChildEntity();
        child1.setName("child1");

        ChildEntity child2 = new ChildEntity();
        child2.setName("child2");

        ParentEntity parent1 = new ParentEntity();
        parent1.setName("parent1");
        parent1.getChildren().add(child1);
        parent1.getChildren().add(child2);

        child1.setParent(parent1);
        child2.setParent(parent1);

        ParentEntity save = parentEntityRepository.save(parent1);

        savedParentId = save.getSeq();
        child1Id = save.getChildren().get(0).getSeq();
        child2Id = save.getChildren().get(1).getSeq();

        entityManager.flush();
        entityManager.clear();
    }

    @Test
    @Transactional
    @Commit
    void test1() {
        ParentEntity parentEntity = parentEntityRepository.findById(savedParentId).get();


        ChildEntity childEntity = parentEntity.getChildren().get(0);

        parentEntity.getChildren().remove(childEntity);

        ChildEntity childEntity1 = new ChildEntity();
        childEntity1.setName("newChild");

        parentEntity.getChildren().add(childEntity1);

    }
}
