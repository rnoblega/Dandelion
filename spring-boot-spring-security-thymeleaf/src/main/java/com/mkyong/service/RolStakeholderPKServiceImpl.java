package com.mkyong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkyong.entity.RoleStakeholder;
import com.mkyong.entity.Stakeholder;
import com.mkyong.repository.RolStakeholderPKRepository;

@Service("rolStakeholderPKService")
public class RolStakeholderPKServiceImpl implements RolStakeholderPKService{
	
	@Autowired
	private RolStakeholderPKRepository rolStakeholderPKRepository;

	@Override
	public void saveRoleStakeholder(RoleStakeholder roleStakeholderPK) {
		
		rolStakeholderPKRepository.save(roleStakeholderPK);
		
	}

	@Override
	public List<RoleStakeholder> findBystakeholder(Stakeholder stakeholder) {
		return rolStakeholderPKRepository.findBystakeholder(stakeholder);
	}

	@Override
	public void delete(RoleStakeholder roleStakeholderPK) {
		rolStakeholderPKRepository.delete(roleStakeholderPK);	
		
	}

	@Override
	public void deleteBystakeholder(Stakeholder stakeholder) {
		rolStakeholderPKRepository.deleteBystakeholder ( stakeholder);
		
	}

}
