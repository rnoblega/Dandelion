package com.mkyong.entity;

import java.util.Collection;
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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "proyecto")
public class Proyecto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "organizacion")
	@NotEmpty(message = "*Ingrese organización")
	private String organizacion;

	@Column(name = "descripcion")
	@Length(max = 200, message = "*La descripción debe tener como máximo 200 caracteres")
	@NotEmpty(message = "*Ingrese descripción")
	private String descripcion;

	@Column(name = "fechaCreacion")
	@NotNull(message = "*Ingrese fecha creación")
	private Date fechaCreacion;

	@Column(name = "nombre")
	@NotEmpty(message = "*Ingrese nombre")
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "usuarioCreacionID", referencedColumnName = "id")
	private Usuario usuarioCreacionId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idProyecto")
	private List<Stakeholder> stakeholderList;
	
	@OneToMany(mappedBy = "idProyecto")
    private Collection<Plantilla> plantillaCollection;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCrecion) {
		this.fechaCreacion = fechaCrecion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setUsuariocreacionId(Usuario usuariocreacionId) {
		this.usuarioCreacionId = usuariocreacionId;
	}

	public Usuario getUsuarioCreacionId() {
		return usuarioCreacionId;
	}
	
	public List<Stakeholder> getStakeholderList() {
		return stakeholderList;
	}

	public void setStakeholderList(List<Stakeholder> stakeholderList) {
		this.stakeholderList = stakeholderList;	
	}

	public Collection<Plantilla> getPlantillaCollection() {
		return plantillaCollection;
	}

	public void setPlantillaCollection(Collection<Plantilla> plantillaCollection) {
		this.plantillaCollection = plantillaCollection;
	}
	
	
	
	
}
