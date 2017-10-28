package com.mkyong.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "caracteristica")
public class Caracteristica {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
	
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "nombre")
    private String nombre;
    
    @JoinColumn(name = "TIPO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Tipornf tipoId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caracteristicaId")
    private List<Subcaracteristica> subcaracteristicaList;

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

	public Tipornf getTipoId() {
		return tipoId;
	}

	public void setTipoId(Tipornf tipoId) {
		this.tipoId = tipoId;
	}

	public List<Subcaracteristica> getSubcaracteristicaList() {
		return subcaracteristicaList;
	}

	public void setSubcaracteristicaList(List<Subcaracteristica> subcaracteristicaList) {
		this.subcaracteristicaList = subcaracteristicaList;
	}
    
    
}
