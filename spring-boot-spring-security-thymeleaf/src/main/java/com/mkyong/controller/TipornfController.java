package com.mkyong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mkyong.entity.Caracteristica;
import com.mkyong.entity.Subcaracteristica;
import com.mkyong.entity.Tipornf;
import com.mkyong.service.TipornfService;

@Controller
public class TipornfController {

	@Autowired
	private TipornfService tipornfService;
	
	@GetMapping("/tipornf")
	public  String getTipornf() {
		List<Tipornf> listTipornf =  tipornfService.findAll();
		
		for (int i = 0; i < listTipornf.size(); i++){
			System.out.println("*Tipo rnf : " + listTipornf.get(i).getNombre());
			
			List<Caracteristica> listCaracteristica = listTipornf.get(i).getCaracteristicaCollection();
			
			for (int j = 0 ; j < listCaracteristica.size(); j++){
				System.out.println("**Caracteristica : " + listCaracteristica.get(j).getNombre());
				
				List<Subcaracteristica> listSubcaracteristica = listCaracteristica.get(j).getSubcaracteristicaList();
				
				for (int k = 0; k < listSubcaracteristica.size(); k++){
					System.out.println("*** Subcaracteristica : " + listSubcaracteristica.get(k).getNombre());
				}
				
			}
			
		}
	
		return "/index";
				
	}
	
}
