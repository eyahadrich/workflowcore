package com.csys.workflow.repository;

import com.csys.workflow.domain.RoleEquipe;
import com.csys.workflow.domain.RoleEquipePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RoleEquipe entity.
 */
@Repository
public interface RoleEquipeRepository extends JpaRepository<RoleEquipe, RoleEquipePK> {
}

