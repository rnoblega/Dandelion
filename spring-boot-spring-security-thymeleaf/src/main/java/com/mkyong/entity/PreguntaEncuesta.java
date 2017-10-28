package com.mkyong.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "pregunta_encuesta")
public class PreguntaEncuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    
    @Column(name = "texto")
    private String texto;
    
    
    @Column(name = "fecha_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUpdate;
    
    
    @JoinColumn(name = "id_pregunta_origen", referencedColumnName = "ID")
    @ManyToOne
    private Pregunta idPreguntaOrigen;
    
    
    @JoinColumn(name = "id_encuesta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Encuesta idEncuesta;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


//	public int getOrden() {
//		return orden;
//	}
//
//
//	public void setOrden(int orden) {
//		this.orden = orden;
//	}


	public Date getFechaUpdate() {
		return fechaUpdate;
	}


	public void setFechaUpdate(Date fechaUpdate) {
		this.fechaUpdate = fechaUpdate;
	}


	public Pregunta getIdPreguntaOrigen() {
		return idPreguntaOrigen;
	}


	public void setIdPreguntaOrigen(Pregunta idPreguntaOrigen) {
		this.idPreguntaOrigen = idPreguntaOrigen;
	}


	public Encuesta getIdEncuesta() {
		return idEncuesta;
	}


	public void setIdEncuesta(Encuesta idEncuesta) {
		this.idEncuesta = idEncuesta;
	}
    

    
}
