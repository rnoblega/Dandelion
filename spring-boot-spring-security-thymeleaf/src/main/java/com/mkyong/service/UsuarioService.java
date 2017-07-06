package com.mkyong.service;

import java.util.List;

import com.mkyong.entity.Usuario;

public interface UsuarioService {
	
	public Usuario findUserByName(String name);
	public void saveUser(Usuario user);
	public List<Usuario> findAll();

}
