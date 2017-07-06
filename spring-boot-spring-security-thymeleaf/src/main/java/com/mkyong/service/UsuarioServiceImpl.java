package com.mkyong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mkyong.entity.Usuario;
import com.mkyong.repository.UsuarioRepository;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Usuario findUserByName(String name) {
		return userRepository.findByname(name);
	}

	@Override
	public void saveUser(Usuario user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);

	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = userRepository.findByname(username);

		return buildUserForAuthentication(user);
	}

	private UserDetails buildUserForAuthentication(Usuario user) {
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), user.isActive(), true, true, true, null);
	}

	

	@Override
	public List<Usuario> findAll() {
		return userRepository.findAll();
	}
}


