package com.mkyong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkyong.entity.TipoRoleStakeholder;
import com.mkyong.entity.TipoStakeholder;
import com.mkyong.repository.RolStakeholderRepository;

@Service("rolStakeholderService")
public class RolStakeholderServiceImpl implements RolStakeholderService{

	@Autowired
	private RolStakeholderRepository rolStakeholderRepository;
	
	@Override
	public List<TipoRoleStakeholder> findAll() {
		return rolStakeholderRepository.findAll();
	}

	@Override
	public List<TipoRoleStakeholder> findByidTipoStakeholder(TipoStakeholder id) {
		return rolStakeholderRepository.findByidTipoStakeholder(id);
	}

	@Override
	public TipoRoleStakeholder findByid(int id) {
		return rolStakeholderRepository.findByid(id);
	}

}
