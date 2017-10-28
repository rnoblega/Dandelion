package com.mkyong.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mkyong.entity.Tipornf;




@Repository
public interface TipornfRepository extends CrudRepository<Tipornf, Long>   {
	
	public Tipornf findBynombre (String nombre);

	public List<Tipornf> findAll ();
}
