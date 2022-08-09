package com.example.test_transactional.data;

import com.example.test_transactional.entity.ChildEntity;
import com.example.test_transactional.entity.ParentEntity;
import com.example.test_transactional.repository.ParentEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {

    private final ParentEntityRepository parentEntityRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() {
        ParentEntity parent1 = getParentEntity(1);
        ParentEntity parent2 = getParentEntity(2);

        parentEntityRepository.saveAll(List.of(parent1, parent2));
    }

    private ParentEntity getParentEntity(int num) {
        ChildEntity child1 = new ChildEntity();
        child1.setName("child" + ((num*2)-1));

        ChildEntity child2 = new ChildEntity();
        child2.setName("child" + (num*2));

        ParentEntity parent = new ParentEntity();
        parent.setName("parent" + num);
        parent.getChildren().add(child1);
        parent.getChildren().add(child2);

        child1.setParent(parent);
        child2.setParent(parent);
        return parent;
    }
}
