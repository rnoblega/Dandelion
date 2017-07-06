package com.mkyong.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "role_stakeholder")
public class RoleStakeholder {
	
	@EmbeddedId
    protected RoleStakeholderPK roleStakeholderPK;
    @JoinColumn(name = "id_stakeholder", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonManagedReference
    private Stakeholder stakeholder;
    
    @JoinColumn(name = "id_tipo_role_stakeholder", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoRoleStakeholder tipoRoleStakeholder;
    
    
	public RoleStakeholderPK getRoleStakeholderPK() {
		return roleStakeholderPK;
	}
	public void setRoleStakeholderPK(RoleStakeholderPK roleStakeholderPK) {
		this.roleStakeholderPK = roleStakeholderPK;
	}
	public Stakeholder getStakeholder() {
		return stakeholder;
	}
	public void setStakeholder(Stakeholder stakeholder) {
		this.stakeholder = stakeholder;
	}
	public TipoRoleStakeholder getTipoRoleStakeholder() {
		return tipoRoleStakeholder;
	}
	public void setTipoRoleStakeholder(TipoRoleStakeholder tipoRoleStakeholder) {
		this.tipoRoleStakeholder = tipoRoleStakeholder;
	}
   

}
