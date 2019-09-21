package com.abhirick.matrimonial.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.abhirick.matrimonial.repository.AccountRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private UserPrincipalDetailsService userPrincipalDetailsService;
	
	private AccountRepository userRepository;

	public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService, AccountRepository userRepository) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

	
    /**
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.
			inMemoryAuthentication()
			.withUser("admin").password(passwordEncoder().encode("admin12345")).authorities("ACCESS_GET","ACCESS_POST", "ACCESS_DELETE","ACCESS_PATCH", "ROLE_ADMIN")
			.and()
			.withUser("abhi").password(passwordEncoder().encode("abhi12345")).authorities("ACCESS_GET", "ROLE_USER")
			.and()
			.withUser("sourabh").password(passwordEncoder().encode("sourabh12345")).authorities("ACCESS_GET","ACCESS_POST" ,"ROLE_USER");
	}


     **/
    
    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // remove csrf and state in session because in jwt we do not need them
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // add jwt filters (1. authentication, 2. authorization)
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),  this.userRepository))
                .authorizeRequests()
                // configure access rules
                .antMatchers(HttpMethod.POST , "/api/v1.0/accounts").permitAll()
                .antMatchers(HttpMethod.PATCH , "/api/v1.0/accounts/**").hasRole("ADMIN")
    			.antMatchers(HttpMethod.DELETE , "/api/v1.0/accounts/**").hasRole("ADMIN")
    			.antMatchers(HttpMethod.GET , "/api/v1.0/accounts/**").hasAuthority("ACCESS_GET")
                .anyRequest().authenticated();
    }

    
    /**
     * 

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.
			csrf().disable().
			authorizeRequests()
		//	.antMatchers("api/v1.0/accounts").permitAll()
		//	.antMatchers("/api/v1.0/accounts/**").authenticated()
		//	.antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
		//	.antMatchers("/api/v1.0/accounts/**").hasAuthority("ACCESS_TEST1")
		//	.antMatchers("/api/v1.0/accounts/**").hasAuthority("ACCESS_TEST2")
		//	.antMatchers(HttpMethod.POST , "api/v1.0/accounts").hasAuthority("ACCESS_POST")
			.antMatchers(HttpMethod.PATCH , "/api/v1.0/accounts/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE , "/api/v1.0/accounts/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET , "/api/v1.0/accounts/**").hasAuthority("ACCESS_GET")
			.and()
			.httpBasic();
	}

     */
    
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}