package com.metalsa.supplier.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
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
@Table(name = "NVC_TBL_COTIZACIONES")
public class NvcTblCotizaciones implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCotizacion")
    @SequenceGenerator(name = "seqCotizacion", sequenceName = "SEQ_COTIZACION", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cotizacion")
    private Integer idCotizacion;

    private Integer idEstatus;

    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date inicioVigencia;

    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date finVigencia;

    @Size(max = 50)
    private String enviarOc;

    private Integer idTipoTransp;

    private Integer idMedio;

    @JsonIgnore
    @OneToMany(mappedBy = "idCotizacion")
    private List<NvcTblRfqProv> nvcTblRfqProvList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCotizacion", fetch = FetchType.EAGER)
    private List<NvcTblDocsCotizacion> nvcTblDocsCotizacionList;
    
    public NvcTblCotizaciones() {
    }
    
    public NvcTblCotizaciones(Integer idCotizacion) {
        this.idCotizacion = idCotizacion;
    }
    
    
    
    /*@JoinColumn(name = "ID_INCOTERM", referencedColumnName = "ID_INCOTERM")
    @ManyToOne
    private NvcTblIncoterm idIncoterm;*/
}

