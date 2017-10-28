package com.mkyong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "pregunta")
public class Pregunta {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	@Column(name = "texto")
    private String texto;
	
	
//	@OneToMany(mappedBy = "idPreguntaOrigen")
//    private List<PreguntaEncuesta> preguntaEncuestaList;
//    private static final long serialVersionUID = 1L;
    
    
    @JoinColumn(name = "SUBCARACTERISTICA_ID", referencedColumnName = "ID")
    @ManyToOne
    @XmlTransient
    private Subcaracteristica subcaracteristicaId;


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


	public Subcaracteristica getSubcaracteristicaId() {
		return subcaracteristicaId;
	}


	public void setSubcaracteristicaId(Subcaracteristica subcaracteristicaId) {
		this.subcaracteristicaId = subcaracteristicaId;
	}
    
    
	
}
