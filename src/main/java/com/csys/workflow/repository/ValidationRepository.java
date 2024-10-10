package com.csys.workflow.repository;

import com.csys.workflow.domain.Validation;
import com.csys.workflow.domain.Demande;
import com.csys.workflow.domain.Validation;
import java.lang.Integer;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Validation entity.
 */
@Repository
public interface ValidationRepository extends JpaRepository<Validation, Integer> {
    List<Validation> findByDemande_IdDemande(Integer idDemande);
}

