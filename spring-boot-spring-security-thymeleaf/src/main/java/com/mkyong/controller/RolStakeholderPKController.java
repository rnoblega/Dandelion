package com.mkyong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mkyong.entity.RoleStakeholder;
import com.mkyong.entity.Stakeholder;
import com.mkyong.service.RolStakeholderPKService;
import com.mkyong.service.StakeholderService;

@Controller
public class RolStakeholderPKController {
	
	@Autowired
	private RolStakeholderPKService rolStakeholderPKService;
	
	@Autowired
	private StakeholderService stakeholderService;
	
	@GetMapping("/stakeholderroles/{id}")
	public @ResponseBody List<RoleStakeholder> filterTipos(@PathVariable Integer id) {
		
		Stakeholder stakeholder = stakeholderService.findByid(id);	
		return rolStakeholderPKService.findBystakeholder(stakeholder);
	}
}
