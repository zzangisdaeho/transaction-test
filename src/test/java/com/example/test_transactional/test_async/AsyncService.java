package com.example.test_transactional.test_async;

import com.example.test_transactional.entity.ParentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@Slf4j
public class AsyncService {

    @Async
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void asyncMethod(ParentEntity parent){

        log.info("------------------------------------------");

        //lazy loading 실패해야하지않나..
        log.info("asyncMethod : {}", parent.getChildren());
        log.info("transaction is active : {}", TransactionSynchronizationManager.isActualTransactionActive());
        parent.setName("changed?");
    }
}
