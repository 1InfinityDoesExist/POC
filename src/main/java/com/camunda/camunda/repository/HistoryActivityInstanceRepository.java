package com.camunda.camunda.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.camunda.camunda.entity.HistoryActivityInstance;

@Repository
public interface HistoryActivityInstanceRepository extends MongoRepository<HistoryActivityInstance, String> {

}
