package com.mkyong.entity;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "plantilla")
public class Plantilla {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
    private Integer id;
	
	@Column(name = "fechaCreacion")
    @Temporal(TemporalType.DATE)
    private Date fechacreacion;
	
	@JoinColumn(name = "PROYECTO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Proyecto idProyecto;
	
	@JoinColumn(name = "id_tipo_rnf", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Tipornf idTipoRnf;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlantilla")
    private List<Encuesta> encuestaList;
    
//  Contiene una lista en la que cada item es una lista con numero de usuarios y las preguntas para cada usuario
    @Transient
	private ArrayList<String> encuestasPorStakeholder;
  
  
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Proyecto getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Proyecto idProyecto) {
		this.idProyecto = idProyecto;
	}

	public Tipornf getIdTipoRnf() {
		return idTipoRnf;
	}

	public void setIdTipoRnf(Tipornf idTipoRnf) {
		this.idTipoRnf = idTipoRnf;
	}

	public List<Encuesta> getEncuestaList() {
		return encuestaList;
	}

	public void setEncuestaList(List<Encuesta> encuestaList) {
		this.encuestaList = encuestaList;
	}

	public ArrayList<String> getEncuestasPorStakeholder() {
		return encuestasPorStakeholder;
	}

	public void setEncuestasPorStakeholder(ArrayList<String> encuestasPorStakeholder) {
		this.encuestasPorStakeholder = encuestasPorStakeholder;
	}

	

}
