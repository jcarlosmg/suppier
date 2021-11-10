package com.metalsa.supplier.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
// import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author hp
 */
@Data
@Entity
@Table(name = "SP_TBL_CATALOGO")
@NamedQueries({
    @NamedQuery(name = "SpTblCatalogo.findByLocalization", query = "SELECT s FROM SpTblCatalogo s"
    +" join SpTblCatLocalizacion x on s.idCatalogo = x.idSpTblCatalogo")
})
public class SpTblCatalogo implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTblCatalogo")
    @SequenceGenerator(name = "seqTblCatalogo", sequenceName = "SEQ_SP_TBL_CATALOGO", allocationSize = 1)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "ID_CATALOGO")
    private Long idCatalogo;
    
    //@NotNull
    @Column(name = "ID_PROVEEDOR")
    private Integer idProveedor;
    
    //@NotNull
    @Column(name = "NOMBRE_CATALOGO")
    private String nombreCatalogo;
    
    
    /*@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "FECHA_INICIO_VIGENCIA")*/
    @Column(name = "FECHA_INICIO_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioVigencia;
    
    
    /*@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "FECHA_FIN_VIGENCIA")*/
    @Column(name = "FECHA_FIN_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinVigencia;
    
    @Column(name = "PUBLICADO")
    private Integer publicado;
    
    /*@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")*/
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    //private Date fechaCreacion;
    
    /*@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")*/
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    //private Date fechaActualizacion;
    
    
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    
    @Column(name = "USUARIO_ACTUALIZACION")
    private String usuarioActualizacion;
    
    @Column(name = "ACTIVO")
    private Integer activo;
    
    @Column(name = "TIPO_AVISO_TERMINACION")
    private Integer tipoAvisoTerminacion;
    
    @Column(name = "ID_PROV_PUNCHOUT")
    private Integer idProvPunchout;
    
    /*@PrePersist
    public void prepersist(){
        this.fechaCreacion = new Date();
    }*/
    
}

