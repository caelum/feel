package br.com.caelum.feel.feedback.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.caelum.feel.security.Role;

@Component
public class AuthenticatedUser {

	public boolean isPeople() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.contains(Role.PEOPLE);
	}
	
	public boolean isReader() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.contains(Role.READER);
	}
}
