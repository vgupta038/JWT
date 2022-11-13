package com.jwt.SignUpSignInValidateTokenWithRole.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.SignUpSignInValidateTokenWithRole.dto.ERole;
import com.jwt.SignUpSignInValidateTokenWithRole.entity.RoleEntity;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long>{
	Optional<RoleEntity> findByName(ERole name);
}
