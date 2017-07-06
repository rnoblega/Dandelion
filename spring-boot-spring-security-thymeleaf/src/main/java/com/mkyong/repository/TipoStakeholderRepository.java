package com.mkyong.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mkyong.entity.TipoStakeholder;

@Repository
public interface TipoStakeholderRepository extends CrudRepository<TipoStakeholder, Long>  {
	
	public List<TipoStakeholder> findAll ();
	
	public TipoStakeholder findByid(int id);

}
