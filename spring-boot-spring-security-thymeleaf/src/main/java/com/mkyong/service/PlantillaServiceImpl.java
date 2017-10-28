package com.mkyong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkyong.entity.Plantilla;
import com.mkyong.repository.PlantillaRepository;

@Service ("plantillaService")
public class PlantillaServiceImpl implements PlantillaService {
	
	@Autowired
	private PlantillaRepository plantillaRepository;

	@Override
	public void savePlantilla(Plantilla plantilla) {
		plantillaRepository.save(plantilla);
		
	}

	@Override
	public Plantilla findByid(int id) {
		return plantillaRepository.findByid(id);
	}

	@Override
	public void deletePlantilla(Plantilla plantilla) {
		plantillaRepository.delete(plantilla);
		
	}

}
