package com.mkyong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkyong.entity.Proyecto;
import com.mkyong.entity.Usuario;
import com.mkyong.repository.ProyectoRepository;

@Service("proyectoService")
public class ProyectoServiceImpl implements ProyectoService {
	
	@Autowired
	private ProyectoRepository proyectoRepository;

	@Override
	public List<Proyecto> findProyectoByusuarioCreacionId(Usuario id) {
		return proyectoRepository.findByusuarioCreacionId(id);
	}

	@Override
	public void saveProyecto(Proyecto proyecto) {
		proyectoRepository.save(proyecto);
		
	}

	@Override
	public Proyecto findByid(int id) {
		return proyectoRepository.findByid(id);
	}

	@Override
	public void delete(Proyecto proy) {
		proyectoRepository.delete(proy);
		
	}

	

}
