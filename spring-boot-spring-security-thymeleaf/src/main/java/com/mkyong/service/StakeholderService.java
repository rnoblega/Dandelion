package com.mkyong.service;

import java.util.List;

import com.mkyong.entity.Proyecto;
import com.mkyong.entity.Stakeholder;

public interface StakeholderService {

	public void saveStakeholder(Stakeholder stakeholder);

	public List<Stakeholder> findByidProyecto(Proyecto proy);

	public void delete(Stakeholder stakeholder);

	public Stakeholder findByid(int id);
}
