package com.example.test_transactional;

import com.example.test_transactional.entity.TestEntity;
import com.example.test_transactional.repository.TestEntityRepository;
import com.example.test_transactional.service.ParentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
class TestTransactionalApplicationTests {

    @Autowired
    ParentService parentService;

    @Autowired
    TestEntityRepository repository;

    private long savedSeq;

    @BeforeEach
    void saveTestData(){
        TestEntity testEntity = new TestEntity();
        testEntity.setName("notChangedName");
        testEntity.setName2("notChangedName2");
        TestEntity save = repository.save(testEntity);
        savedSeq = save.getSeq();
    }

    @Test
    @Commit
    void test1() {
        parentService.parentService(savedSeq);

        TestEntity testEntity = repository.findById(savedSeq).orElseThrow(() -> new IllegalStateException("데이터... 날라갔는데?"));

        Assertions.assertThat(testEntity.getName()).isEqualTo("changedName_parent");
        Assertions.assertThat(testEntity.getName2()).isEqualTo("notChangedName2");
    }

}
