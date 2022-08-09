package com.example.test_transactional.test_async;

import com.example.test_transactional.entity.ChildEntity;
import com.example.test_transactional.entity.ParentEntity;
import com.example.test_transactional.repository.ParentEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class TestAsync {

    @Autowired
    private ParentEntityRepository parentEntityRepository;

    @Autowired
    private AsyncService asyncService;

//    @BeforeEach
//    @Transactional
//    void saveTestData(){
//        ParentEntity parent1 = getParentEntity(1);
//        ParentEntity parent2 = getParentEntity(2);
//
//        parentEntityRepository.saveAll(List.of(parent1, parent2));
//    }
//
//    private ParentEntity getParentEntity(int num) {
//        ChildEntity child1 = new ChildEntity();
//        child1.setName("child" + ((num*2)-1));
//
//        ChildEntity child2 = new ChildEntity();
//        child2.setName("child" + (num*2));
//
//        ParentEntity parent = new ParentEntity();
//        parent.setName("parent" + num);
//        parent.getChildren().add(child1);
//        parent.getChildren().add(child2);
//
//        child1.setParent(parent);
//        child2.setParent(parent);
//        return parent;
//    }

    @Test
    @Transactional
    void testAsync(){
        List<ParentEntity> allParent = parentEntityRepository.findAll();

        allParent.forEach(parentEntity -> {
            asyncService.asyncMethod(parentEntity);
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
