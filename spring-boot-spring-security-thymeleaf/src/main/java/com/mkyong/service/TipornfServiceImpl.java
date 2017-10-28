package com.mkyong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkyong.entity.Tipornf;
import com.mkyong.repository.TipornfRepository;

@Service ("tipornfService")
public class TipornfServiceImpl implements TipornfService{
	
	
	@Autowired
	private TipornfRepository tipornfRepository;

	@Override
	public Tipornf findBynombre(String nombre) {
		return tipornfRepository.findBynombre(nombre);
	}

	@Override
	public List<Tipornf> findAll() {
		return tipornfRepository.findAll();
	}

}
