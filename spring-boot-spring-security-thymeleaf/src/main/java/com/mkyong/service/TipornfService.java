package com.mkyong.service;

import java.util.List;

import com.mkyong.entity.Tipornf;

public interface TipornfService {
	
	public Tipornf findBynombre (String nombre);

	public List<Tipornf> findAll ();

}
