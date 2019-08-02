package com.xyyh.edc.meta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.xyyh.edc.meta.entity.Table;

public interface TableRepository extends JpaRepository<Table, Long>, QuerydslPredicateExecutor<Table> {

	public Optional<Table> findByName(String name);

}
