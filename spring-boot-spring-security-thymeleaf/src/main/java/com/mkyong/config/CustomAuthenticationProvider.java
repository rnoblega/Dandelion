package com.mkyong.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mkyong.entity.Usuario;
import com.mkyong.service.UsuarioService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UsuarioService userService;
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		    String username = authentication.getName();
	        String password = (String) authentication.getCredentials();
	       
	        Usuario user = userService.findUserByName(username);
	        
	        PasswordEncoder encoder = new BCryptPasswordEncoder();
	        
	        if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
	            throw new BadCredentialsException("Usuario no encontrado.");
	        }
	 
	        if (!encoder.matches((String)authentication.getCredentials(), user.getPassword())){
	            throw new BadCredentialsException("Contraseña incorrecta.");
	        }
	 
	        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
	 
	        return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}

}