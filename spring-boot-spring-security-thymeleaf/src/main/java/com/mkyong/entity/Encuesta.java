package com.mkyong.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
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

@Entity
@Table(name = "encuesta")
public class Encuesta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
	
	@JoinColumn(name = "id_plantilla", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Plantilla idPlantilla;
	
    @Column(name = "nombre_archivo")
    private String nombreArchivo;

   
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    
    
    @JoinColumn( name = "id_proyecto", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Proyecto idProyecto;
    
    
    @JoinColumn(name = "id_stakeholder", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Stakeholder idStakeholder;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEncuesta")
    private List<PreguntaEncuesta> preguntaEncuestaList;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Plantilla getIdPlantilla() {
		return idPlantilla;
	}


	public void setIdPlantilla(Plantilla idPlantilla) {
		this.idPlantilla = idPlantilla;
	}


	public String getNombreArchivo() {
		return nombreArchivo;
	}


	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public Proyecto getIdProyecto() {
		return idProyecto;
	}


	public void setIdProyecto(Proyecto idProyecto) {
		this.idProyecto = idProyecto;
	}


	public Stakeholder getIdStakeholder() {
		return idStakeholder;
	}


	public void setIdStakeholder(Stakeholder idStakeholder) {
		this.idStakeholder = idStakeholder;
	}


	public List<PreguntaEncuesta> getPreguntaEncuestaList() {
		return preguntaEncuestaList;
	}


	public void setPreguntaEncuestaList(List<PreguntaEncuesta> preguntaEncuestaList) {
		this.preguntaEncuestaList = preguntaEncuestaList;
	}
    
    
    

}
