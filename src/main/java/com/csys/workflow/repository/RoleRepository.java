package com.csys.workflow.repository;

import com.csys.workflow.domain.Role;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Role entity.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}

