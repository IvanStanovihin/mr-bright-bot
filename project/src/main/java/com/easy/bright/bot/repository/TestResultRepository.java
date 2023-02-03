package com.easy.bright.bot.repository;

import com.easy.bright.bot.model.TestResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends CrudRepository<TestResult, Long> {
}
