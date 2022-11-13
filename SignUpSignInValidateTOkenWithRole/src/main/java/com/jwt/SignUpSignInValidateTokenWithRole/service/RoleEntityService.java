package com.jwt.SignUpSignInValidateTokenWithRole.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.SignUpSignInValidateTokenWithRole.dto.ERole;
import com.jwt.SignUpSignInValidateTokenWithRole.entity.RoleEntity;
import com.jwt.SignUpSignInValidateTokenWithRole.repository.RoleEntityRepository;

@Service
public class RoleEntityService {

	@Autowired
	private RoleEntityRepository roleEntityRepository;
	
	public Optional<RoleEntity> findByName(ERole name) {
		return roleEntityRepository.findByName(name);
	}
	
}
