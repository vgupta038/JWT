package com.jwt.SignUpSignInWithRole.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.SignUpSignInWithRole.dto.ERole;
import com.jwt.SignUpSignInWithRole.entity.RoleEntity;
import com.jwt.SignUpSignInWithRole.repository.RoleEntityRepository;

@Service
public class RoleEntityService {

	@Autowired
	private RoleEntityRepository roleEntityRepository;
	
	public Optional<RoleEntity> findByName(ERole name) {
		return roleEntityRepository.findByName(name);
	}
	
}
