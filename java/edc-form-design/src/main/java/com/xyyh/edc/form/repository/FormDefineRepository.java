package com.xyyh.edc.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.xyyh.edc.form.entity.FormDefine;

public interface FormDefineRepository extends JpaRepository<FormDefine, Long>, QuerydslPredicateExecutor<FormDefine> {

}
