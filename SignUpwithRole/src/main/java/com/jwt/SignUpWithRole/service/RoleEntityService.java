package com.jwt.SignUpWithRole.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.SignUpWithRole.dto.ERole;
import com.jwt.SignUpWithRole.entity.RoleEntity;
import com.jwt.SignUpWithRole.repository.RoleEntityRepository;

@Service
public class RoleEntityService {

	@Autowired
	private RoleEntityRepository roleEntityRepository;
	
	public Optional<RoleEntity> findByName(ERole name) {
		return roleEntityRepository.findByName(name);
	}
	
}
