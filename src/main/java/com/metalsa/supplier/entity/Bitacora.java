package com.metalsa.supplier.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

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
import oracle.security.crypto.core.math.BigInt;

@Data
@Entity
@Table(name = "SP_TBL_BITACORA")
public class Bitacora implements Serializable {

	/**
	 * SP_TBL_BITACORA
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqBitacora")
	@SequenceGenerator(name = "seqBitacora", sequenceName = "SP_TBL_BITACORA_SEQ", allocationSize = 1)
	@Column(name = "ID_BITACORA")
	private Long idBitacora;
	
	@Column(name = "ID_VENDOR")
	private String idVendor;
	
	@Column(name = "ID_CATALOGO")
	private Long idCatalogo;
	
	@Column(name = "ID_ITEM")
	private Long idItem;
	
	@Column(name = "VENDOR")
	private String vendor;
	
	@Column(name = "ACTION")
	private String action;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "RESUME")
	private String resume;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "DATE_OF_ACTION")
	private Date DateOfAction;
}
