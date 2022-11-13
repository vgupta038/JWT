package com.jwt.SignUpSignInWithRole.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.SignUpSignInWithRole.dto.ERole;
import com.jwt.SignUpSignInWithRole.entity.RoleEntity;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long>{
	Optional<RoleEntity> findByName(ERole name);
}
