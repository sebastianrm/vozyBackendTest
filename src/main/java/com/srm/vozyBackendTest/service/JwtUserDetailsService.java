/**
 * 
 */
package com.srm.vozyBackendTest.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.srm.vozyBackendTest.document.User;
import com.srm.vozyBackendTest.repository.UserRepository;

/**
 * @author sebastian
 *
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepo.findByUserName(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());

	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public User save(User user) {

		
		String encode = bcryptEncoder.encode(user.getPassword());
		
		user.setPassword(encode);
		
		return userRepo.save(user);

	}
	
	public void delete(User user) {
		
		userRepo.delete(user);
		
	}
	
	public void update(User user) {
		
		userRepo.save(user);
		
	}

}
