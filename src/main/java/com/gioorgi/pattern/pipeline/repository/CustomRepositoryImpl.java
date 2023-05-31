package com.gioorgi.pattern.pipeline.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lombok.extern.slf4j.Slf4j;

/** This class is used to access directly to entity Manager.
 * Refer to https://www.baeldung.com/spring-data-entitymanager
 */
@Slf4j
public class CustomRepositoryImpl  implements CustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void flush() {
        log.info("Flush mode....{}",entityManager.getFlushMode().name());
        log.info("Forcing flush...");

        entityManager.flush();
    }



}
