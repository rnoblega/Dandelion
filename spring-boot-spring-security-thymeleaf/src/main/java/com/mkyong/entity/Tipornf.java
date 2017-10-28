package com.mkyong.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tipornf")
public class Tipornf {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
	
	@Column(name = "descripcion")
    private String descripcion;
	
	@Column(name = "nombre")
    private String nombre;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoRnf")
    private List<Plantilla> plantillaList;
    
    @OneToMany(mappedBy = "tipoId")
    private List<Caracteristica> caracteristicaCollection;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Plantilla> getPlantillaList() {
		return plantillaList;
	}

	public void setPlantillaList(List<Plantilla> plantillaList) {
		this.plantillaList = plantillaList;
	}

	public List<Caracteristica> getCaracteristicaCollection() {
		return caracteristicaCollection;
	}

	public void setCaracteristicaCollection(List<Caracteristica> caracteristicaCollection) {
		this.caracteristicaCollection = caracteristicaCollection;
	}
	
    
    
}
