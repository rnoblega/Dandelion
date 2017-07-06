package com.mkyong.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mkyong.entity.Proyecto;
import com.mkyong.entity.Stakeholder;

@Repository
public interface StakeholderRepository extends CrudRepository<Stakeholder, Long> {
	
	public List<Stakeholder> findByidProyecto (Proyecto proy);
	
	public void delete(Stakeholder stakeholder);
	
	public Stakeholder findByid(int id);
	
	
}
