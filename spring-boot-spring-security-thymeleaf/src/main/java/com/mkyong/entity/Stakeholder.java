package com.mkyong.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table (name="stakeholder")
public class Stakeholder {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
    @Column(name = "nombre_id")
    @NotEmpty(message = "*Ingrese identificador")
    private String nombreId;
    
    @Column(name = "nombre")
    @NotEmpty(message = "*Ingrese nombre")
    private String nombre;
    
    @Column(name = "apellido")
    @NotEmpty(message = "*Ingrese apellido")
    private String apellido;
    
    @Column(name = "descripcion")
    @NotEmpty(message = "*Ingrese descripción")
    @Length(max = 200, message = "*La descripción debe tener como máximo 500 caracteres")
    private String descripcion;
    
    
    @ManyToMany(mappedBy = "stakeholderList")
    @JsonBackReference
    private List<TipoRoleStakeholder> tipoRoleStakeholderList;
    
    @JoinColumn(name = "id_proyecto", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Proyecto idProyecto;
    
//  Contiene tipo y roles correspondientes a un stakeholder
    @Transient
	private ArrayList<String> listaSHTipoRoles;
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreId() {
		return nombreId;
	}

	public void setNombreId(String nombreId) {
		this.nombreId = nombreId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Proyecto getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Proyecto idProyecto) {
		this.idProyecto = idProyecto;
	}

	public List<TipoRoleStakeholder> getTipoRoleStakeholderList() {
		return tipoRoleStakeholderList;
	}

	public void setTipoRoleStakeholderList(List<TipoRoleStakeholder> tipoRoleStakeholderList) {
		this.tipoRoleStakeholderList = tipoRoleStakeholderList;
	}

	public ArrayList<String> getListaSHTipoRoles() {
		return listaSHTipoRoles;
	}

	public void setListaSHTipoRoles(ArrayList<String> listaSHTipoRoles) {
		this.listaSHTipoRoles = listaSHTipoRoles;
	}

    
}
