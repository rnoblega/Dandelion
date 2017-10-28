package com.mkyong.service;

import com.mkyong.entity.Plantilla;

public interface PlantillaService {
	
	public void savePlantilla(Plantilla plantilla);
	
	public Plantilla findByid(int id);
	
	public void deletePlantilla(Plantilla plantilla);
}
