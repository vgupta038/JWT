package com.jwt.SignUpSignInValidateToken.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.SignUpSignInValidateToken.entity.UserEntity;
import com.jwt.SignUpSignInValidateToken.repository.UserEntityRepository;

@Service
public class UserEntityService implements UserDetailsService{

	@Autowired
	private UserEntityRepository userEntityRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> user = userEntityRepository.findByUsername(username);
	    if (user.isPresent()) {
	      UserEntity appuser = user.get();
	      return new User(appuser.getUsername(), appuser.getPassword(), new ArrayList<>());
	    } else {
	      throw new UsernameNotFoundException(String.format("User not found:: ", username));
	    }
	}
	
	public Boolean existsByUsername(String username) {
		return userEntityRepository.existsByUsername(username);
	}
	
	public void save(UserEntity user) {
		userEntityRepository.save(user);
	}

}
