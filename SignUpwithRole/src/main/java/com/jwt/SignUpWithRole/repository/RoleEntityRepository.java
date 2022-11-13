package com.jwt.SignUpWithRole.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.SignUpWithRole.dto.ERole;
import com.jwt.SignUpWithRole.entity.RoleEntity;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long>{
	Optional<RoleEntity> findByName(ERole name);
}
