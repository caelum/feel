package br.com.caelum.feel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		int umDia = 86400;
		http.authorizeRequests()
		.antMatchers("/questions/**").permitAll()		
		.antMatchers("/magic/**").permitAll()		
		.antMatchers("/custom/login").permitAll()		
		.antMatchers("/behavior/feedback/anonimous/form").permitAll()		
		.antMatchers("/behavior/feedback/anonimous").permitAll()		
		.antMatchers("/behavior/anonimous/timeline/**").permitAll()		
		.antMatchers("/admin/cycles").authenticated()		
		.antMatchers("/admin/**").hasAuthority(Role.PEOPLE.getName())		
		.anyRequest().authenticated()
		.and().formLogin()		
			.failureUrl("/login/form?error")
			.loginProcessingUrl("/login")
			.loginPage("/login/form")			
			.defaultSuccessUrl("/admin/cycles",true)		
		.permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login/form").permitAll()		
		.and()
		.rememberMe()
		.tokenValiditySeconds(umDia)
		.and()
		.csrf()
		.disable();
	}
	
	
	@Autowired
	private UserDetailsService userDetailsService;

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**").antMatchers("/webjars/**").antMatchers("/images/**").antMatchers("/js/**");
	}
	
}
