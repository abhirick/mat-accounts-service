package com.abhirick.matrimonial.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abhirick.matrimonial.model.Account;
import com.abhirick.matrimonial.repository.AccountRepository;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
	
	private AccountRepository userRepository;

	public UserPrincipalDetailsService(AccountRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		Account user = this.userRepository.findByUsername(s);
		UserPrincipal userPrincipal = new UserPrincipal(user);
		return userPrincipal;
	}
}