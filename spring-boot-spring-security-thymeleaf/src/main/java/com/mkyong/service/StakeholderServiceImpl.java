package com.mkyong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkyong.entity.Proyecto;
import com.mkyong.entity.Stakeholder;
import com.mkyong.repository.StakeholderRepository;

@Service("stakeholderService")
public class StakeholderServiceImpl implements StakeholderService{

	@Autowired
	private StakeholderRepository stakeholderRepository;

	@Override
	public void saveStakeholder(Stakeholder stakeholder) {
		stakeholderRepository.save(stakeholder);
		
	}

	@Override
	public List<Stakeholder> findByidProyecto(Proyecto proy) {
		return stakeholderRepository.findByidProyecto(proy);
	}

	@Override
	public void delete(Stakeholder stakeholder) {
		stakeholderRepository.delete(stakeholder);
		
	}

	@Override
	public Stakeholder findByid(int id) {
		return stakeholderRepository.findByid(id);
	}
}
