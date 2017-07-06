package com.mkyong.service;

import java.util.List;

import com.mkyong.entity.TipoStakeholder;

public interface TipoStakeholderService {
	
	public List<TipoStakeholder> findAll();
	
	public TipoStakeholder findByid(int id);
}
