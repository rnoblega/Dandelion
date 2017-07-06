package com.mkyong.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleStakeholderPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "id_tipo_role_stakeholder")
	private int idTipoRoleStakeholder;
	@Basic(optional = false)
	@Column(name = "id_stakeholder")
	private int idStakeholder;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getIdTipoRoleStakeholder() {
		return idTipoRoleStakeholder;
	}
	public void setIdTipoRoleStakeholder(int idTipoRoleStakeholder) {
		this.idTipoRoleStakeholder = idTipoRoleStakeholder;
	}
	public int getIdStakeholder() {
		return idStakeholder;
	}
	public void setIdStakeholder(int idStakeholder) {
		this.idStakeholder = idStakeholder;
	}
	
}
