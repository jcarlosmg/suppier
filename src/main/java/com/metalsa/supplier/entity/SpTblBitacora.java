package com.metalsa.supplier.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "SP_TBL_BITACORA")
public class SpTblBitacora implements Serializable {

	/**
	 * SEQ_SP_TBL_CATALOGO_BITACORA
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTblBitacora")
	@SequenceGenerator(name = "seqTblBitacora", sequenceName = "SEQ_SP_TBL_CATALOGO_BITACORA", allocationSize = 1)
	@Column(name = "ID_BITACORA")
	private Long idBitacora;
	
	@Column(name = "USUARIO")
	private String usuario;
	
	@Column(name = "ACCION")
	private String accion;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "VISTA")
	private String vista;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "FECHA_ACCION")
	private Date fechaAccion;
	
	@Column(name = "ID_CATALOGO")
	private Long idCatalogo;
	
	@Column(name = "ID_VENDOR")
	private Long idVendor;
}
