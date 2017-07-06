package com.mkyong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mkyong.entity.TipoRoleStakeholder;
import com.mkyong.entity.TipoStakeholder;
import com.mkyong.service.RolStakeholderService;
import com.mkyong.service.TipoStakeholderService;


@Controller
public class RolStakeholderController {

	@Autowired
	private RolStakeholderService rolStakeholderService;
	
	@Autowired
	private TipoStakeholderService tipoStakeholderService;
	
	@RequestMapping("/roles")
	public @ResponseBody List<TipoRoleStakeholder> roles() {
		return rolStakeholderService.findAll();
	}
	
	@RequestMapping("/rolesportipo/{idTipo}")
	public @ResponseBody List<TipoRoleStakeholder> filterRoles(@PathVariable Integer idTipo) {
		
		TipoStakeholder tipoStakeholder = tipoStakeholderService.findByid(idTipo);
		return rolStakeholderService.findByidTipoStakeholder(tipoStakeholder);
	}
	
	
}
