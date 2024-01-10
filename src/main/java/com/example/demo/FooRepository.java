package com.example.demo;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface FooRepository extends CrudRepository<FooEntity, Long>, JpaSpecificationExecutor<FooEntity> {
}
