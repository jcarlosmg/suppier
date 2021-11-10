package com.metalsa.supplier.entity;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

// import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "SP_TBL_CATALOGO_ITEM_DOC")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CatItemDoc implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTblCatItemDoc")
    @SequenceGenerator(name = "seqTblCatItemDoc", sequenceName = " sp_tbl_catalogo_item_doc_seq", allocationSize = 1)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "ID_CATALOGO_ITEM_DOC")
    private Long idItemDoc;
    
    @ManyToOne (cascade = CascadeType.DETACH)
    @JoinColumn(name="ID_ITEM", referencedColumnName = "ID_ITEM", insertable = false, updatable = false)    
    @JsonIgnore
    private SpTblCatalogoItem spTblCatalogoItem;
    @Transient
    private Long idItem;
    
    @Basic(optional = false)
    //@NotNull
    @Column(name = "ID_TIPO_DOCUMENTO")
    private Long idTipoDocumento;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 256)
    @Column(name = "NOMBRE_ARCHIVO")
    private String nombreArchivo;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 256)
    @Column(name = "NOMBRE_ARCHIVO_FTP")
    private String nombreArchivoFtp;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 256)
    @Column(name = "EXTENSION_ARCHIVO")
    private String extensionArchivo;
    @Column(name = "TAMANIO_ARCHIVO")
    private Double tamanioArchivo;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 256)
    @Column(name = "URL_FTP")
    private String urlFtp;
    @Column(name = "POSICION")
    private Long posicion;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 256)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 256)
    @Column(name = "USUARIO_ACTUALIZACION")
    private String usuarioActualizacion;
    @Column(name = "ACTIVO")
    private Long activo;
    //@Size(max = 256)
    @Column(name = "COD_PRODUCTO")
    private String codProducto;
    @Column(name = "ID_UEN")
    private Long idUen;
    @Column(name = "ID_CATALOGO_UEN")
    private Long idCatalogoUen;
}
