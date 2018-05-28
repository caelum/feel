package br.com.caelum.feel.feedback.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.caelum.feel.security.Role;

@Component
public class AuthenticatedUser {

	public boolean isPeople() {
		return isPeople((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}
	
	public boolean isReader() {
		return isReader((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}
	
	public boolean isPeople(UserDetails currentUser) {
		return currentUser.getAuthorities()
				.contains(Role.PEOPLE);
	}
	
	public boolean isReader(UserDetails currentUser) {
		return currentUser.getAuthorities()
				.contains(Role.READER);
	}
}
