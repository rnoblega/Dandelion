package com.mkyong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mkyong.entity.TipoStakeholder;
import com.mkyong.service.TipoStakeholderService;


@Controller
public class TipoStakeholderController {

	@Autowired
	private TipoStakeholderService tipoStakeholderService;
	
	@GetMapping("/tipos")
	public @ResponseBody List<TipoStakeholder> tipos() {
		return tipoStakeholderService.findAll();
	}
	
	@GetMapping("/tipos/{id}")
	public @ResponseBody TipoStakeholder filterTipos(@PathVariable Integer id) {
		return tipoStakeholderService.findByid(id);
	}
}
