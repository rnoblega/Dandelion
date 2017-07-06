package com.mkyong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkyong.entity.TipoStakeholder;
import com.mkyong.repository.TipoStakeholderRepository;

@Service("tipoStakeholderService")
public class TipoStakeholderServiceImpl implements TipoStakeholderService{

	@Autowired
	private TipoStakeholderRepository tipoStakeholderRepository;
	
	@Override
	public List<TipoStakeholder> findAll() {
		return tipoStakeholderRepository.findAll();
	}

	@Override
	public TipoStakeholder findByid(int id) {
		return tipoStakeholderRepository.findByid(id);
	}

}
