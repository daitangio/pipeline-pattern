package com.gioorgi.pattern.pipeline.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gioorgi.pattern.pipeline.entity.SurfGuy;

@Repository
public interface SurfGuyRepository extends CrudRepository<SurfGuy, Long>, CustomRepository {
    
}
