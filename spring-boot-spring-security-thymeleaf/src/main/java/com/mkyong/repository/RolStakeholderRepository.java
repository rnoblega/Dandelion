package com.mkyong.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mkyong.entity.TipoRoleStakeholder;
import com.mkyong.entity.TipoStakeholder;

@Repository
public interface RolStakeholderRepository extends CrudRepository<TipoRoleStakeholder, Long> {
	
	public List<TipoRoleStakeholder> findAll();
	
	public List<TipoRoleStakeholder> findByidTipoStakeholder(TipoStakeholder id);
	
	public TipoRoleStakeholder findByid(int id);

}
