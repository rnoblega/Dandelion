package com.mkyong.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tipo_role_stakeholder")
public class TipoRoleStakeholder {
	
	@ManyToMany
	private List<Stakeholder> stakeholderList;
	private static long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	
	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoRoleStakeholder")
	@JsonBackReference
	private List<RoleStakeholder> roleStakeholderList;
	
	@JoinColumn(name = "id_tipo_stakeholder", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@JsonManagedReference	
	private TipoStakeholder idTipoStakeholder;
	
	public List<Stakeholder> getStakeholderList() {
		return stakeholderList;
	}
	public void setStakeholderList(List<Stakeholder> stakeholderList) {
		this.stakeholderList = stakeholderList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<RoleStakeholder> getRoleStakeholderList() {
		return roleStakeholderList;
	}
	public void setRoleStakeholderList(List<RoleStakeholder> roleStakeholderList) {
		this.roleStakeholderList = roleStakeholderList;
	}
	public TipoStakeholder getIdTipoStakeholder() {
		return idTipoStakeholder;
	}
	public void setIdTipoStakeholder(TipoStakeholder idTipoStakeholder) {
		this.idTipoStakeholder = idTipoStakeholder;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	
}
