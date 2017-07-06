package com.mkyong.service;

import java.util.List;

import com.mkyong.entity.Proyecto;
import com.mkyong.entity.Usuario;

public interface ProyectoService {

	public List<Proyecto> findProyectoByusuarioCreacionId(Usuario id);
	public void saveProyecto(Proyecto proyecto);
	public Proyecto findByid(int id);
	
	public void delete(Proyecto proy);
}
