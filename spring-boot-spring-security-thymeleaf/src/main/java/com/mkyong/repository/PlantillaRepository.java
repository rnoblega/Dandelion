package com.mkyong.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mkyong.entity.Plantilla;

@Repository
public interface PlantillaRepository extends CrudRepository<Plantilla, Long>{

	public void delete(Plantilla plantilla);
	
	public Plantilla findByid(int id);
	
}


