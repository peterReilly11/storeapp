package com.store.storeapp.repository;

import java.util.List;

import com.store.storeapp.model.Journal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface JournalRepository extends JpaRepository<Journal, Long>{
    List<Journal> findAll();
    List<Journal> findByEmailOrderByDateTimeCreatedDesc(String email);
}