package com.mkyong.service;

import java.util.List;

import com.mkyong.entity.RoleStakeholder;
import com.mkyong.entity.Stakeholder;

public interface RolStakeholderPKService {

	public void saveRoleStakeholder(RoleStakeholder roleStakeholderPK);
	
	public List<RoleStakeholder> findBystakeholder ( Stakeholder stakeholder);
	
	public void delete (RoleStakeholder roleStakeholderPK);
	
	public void deleteBystakeholder ( Stakeholder stakeholder);
	
}
