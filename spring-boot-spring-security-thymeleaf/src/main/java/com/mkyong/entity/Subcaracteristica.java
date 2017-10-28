package com.mkyong.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "subcaracteristica")
public class Subcaracteristica {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "ID")
     private Integer id;
	 
     @Column(name = "descripcion")
     private String descripcion;
     
     @Column(name = "nombre")
     private String nombre;

	
	@OneToMany(mappedBy = "subcaracteristicaId")
    private List<Pregunta> preguntaList;
   
    @JoinColumn(name = "CARACTERISTICA_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER)
    @XmlTransient
    private Caracteristica caracteristicaId;

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

	public List<Pregunta> getPreguntaList() {
		return preguntaList;
	}

	public void setPreguntaList(List<Pregunta> preguntaList) {
		this.preguntaList = preguntaList;
	}

	public Caracteristica getCaracteristicaId() {
		return caracteristicaId;
	}

	public void setCaracteristicaId(Caracteristica caracteristicaId) {
		this.caracteristicaId = caracteristicaId;
	}
    
    
}
