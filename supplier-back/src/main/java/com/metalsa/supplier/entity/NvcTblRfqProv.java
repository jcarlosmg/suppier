package com.metalsa.supplier.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author mlopez
 */
@Data
@Entity
@Table(name = "NVC_TBL_RFQ_PROV")
public class NvcTblRfqProv implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRfq")
    @SequenceGenerator(name = "seqRfq", sequenceName = "SEQ_RFQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "LLAVE_ID", nullable = false)
    private Long llaveId;

    @Size(max = 450)
    @Column(name = "ADICIONAL_CONTACT", length = 450)
    private String adicionalContact;

    @ManyToOne()
    @JoinColumn(name = "ID_RFQ", referencedColumnName = "ID_RFQ", nullable = false)
    private NvcTblRfq idRfq;
    
    private Long idProvExterno;
    
    
    @JsonIgnore
    @ManyToOne()
    @JoinColumns({
        @JoinColumn(name = "ID_PROV_INTERNO", referencedColumnName = "ID_PROVEEDOR")
        ,
        @JoinColumn(name = "ID_SITE", referencedColumnName = "ID_SUCURSAL_PROVEEDOR")})
    private NvcTblProvSitesH nvcTblProvSitesH;
    
    private Long idContact;
    
     
    @ManyToOne()
    @JoinColumn(name = "ID_COTIZACION", referencedColumnName = "ID_COTIZACION")
    private NvcTblCotizaciones idCotizacion;
    
    private String idIdioma;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRfqProv", fetch = FetchType.LAZY)
    //@OrderBy("llaveId.detalleRequisicion.requisicion, llaveId.detalleRequisicion.linea ASC")
    private List<NvcTblReqLineaProv> nvcTblReqLineaProvList;

    @Column(name = "ULTIMA_NOTIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaNotificacion;

    @Size(max = 250)
    private String terminosEspecialesPago;

    private Character notificacionEnviado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsignacion;
    
    private String comentario;
}

