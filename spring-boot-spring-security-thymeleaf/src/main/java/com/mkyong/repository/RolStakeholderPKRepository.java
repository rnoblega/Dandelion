package com.mkyong.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mkyong.entity.RoleStakeholder;
import com.mkyong.entity.Stakeholder;

@Repository
@Transactional
public interface RolStakeholderPKRepository extends CrudRepository<RoleStakeholder, Long>{
	
	public List<RoleStakeholder> findBystakeholder (Stakeholder stakeholder);
	
	public void delete(RoleStakeholder roleStakeholder);

	public void deleteBystakeholder(Stakeholder stakeholder);

}
