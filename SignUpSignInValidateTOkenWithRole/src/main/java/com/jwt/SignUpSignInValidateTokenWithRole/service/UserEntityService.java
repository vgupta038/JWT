package com.jwt.SignUpSignInValidateTokenWithRole.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.SignUpSignInValidateTokenWithRole.entity.RoleEntity;
import com.jwt.SignUpSignInValidateTokenWithRole.entity.UserEntity;
import com.jwt.SignUpSignInValidateTokenWithRole.repository.UserEntityRepository;

@Service
public class UserEntityService implements UserDetailsService{

	@Autowired
	private UserEntityRepository userEntityRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> user = userEntityRepository.findByUsername(username);
	    if (user.isPresent()) {
	      UserEntity appuser = user.get();
	      Set<RoleEntity> roles = appuser.getRoles();
	      Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	      for(RoleEntity role: roles){
	            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
	        }
	     /* Set<GrantedAuthority> authorities=appuser.getRoles().stream()
					.map(obj->new SimpleGrantedAuthority(obj.getName().name()))
					.collect(Collectors.toSet());*/
	      
	      return new User(appuser.getUsername(), appuser.getPassword(), authorities);
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
