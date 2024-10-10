package com.csys.workflow.repository;

import com.csys.workflow.domain.Menus;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Menus entity.
 */
@Repository
public interface MenusRepository extends JpaRepository<Menus, Integer> {
}

