package com.mkyong.service;

import java.util.List;

import com.mkyong.entity.TipoRoleStakeholder;
import com.mkyong.entity.TipoStakeholder;



public interface RolStakeholderService {

	public List<TipoRoleStakeholder> findAll();
	
	public List<TipoRoleStakeholder> findByidTipoStakeholder(TipoStakeholder id);
	
	public TipoRoleStakeholder findByid(int id);
}
