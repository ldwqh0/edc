package com.xyyh.meta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.xyyh.meta.entity.Table;

public interface TableRepository extends JpaRepository<Table, Long>,QuerydslPredicateExecutor<Table> {

}
