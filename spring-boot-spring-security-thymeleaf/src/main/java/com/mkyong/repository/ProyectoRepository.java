package com.mkyong.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mkyong.entity.Proyecto;
import com.mkyong.entity.Usuario;

@Repository
public interface ProyectoRepository extends CrudRepository<Proyecto, Long>{

	public List<Proyecto> findByusuarioCreacionId (Usuario id);
	
	public Proyecto findByid(int id);
	
	public void delete(Proyecto proy);
	
	
	
}
