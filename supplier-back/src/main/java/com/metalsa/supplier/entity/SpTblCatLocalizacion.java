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
// import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "SP_TBL_CAT_LOCALIZACION")
public class SpTblCatLocalizacion implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTblCatLoc")
    @SequenceGenerator(name = "seqTblCatLoc", sequenceName = "seq_sp_tbl_cat_loc", allocationSize = 1)
    @Basic(optional = false)
    // //@NotNull
    @Column(name = "ID_CATALOGO_LOCALIZACION")
    private Long idCatalogoLocalizacion;       
    
    //@NotNull
    @Column(name = "ID_SP_TBL_CATALOGO")
    private Long idSpTblCatalogo;
    
    //@NotNull
    @Column(name = "ID_LOCALIZACION")
    private Long idLocalizacion;
    
    //@NotNull
    @Column(name = "ID_UEN")
    private Long idUen;
    
    @Basic(optional = false)
    //@NotNull
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion; 
    
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    
    @Column(name = "USUARIO_ACTUALIZACION")
    private String usuarioActualizacion;
    
    @Column(name = "ACTIVO")
    private Integer activo;
    
    @Column(name = "CONTACTOS_EMAIL")
    private String contactoEmail;
}
