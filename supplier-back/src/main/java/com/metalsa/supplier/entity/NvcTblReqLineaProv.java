package com.metalsa.supplier.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "NVC_TBL_REQ_LINEA_PROV")
public class NvcTblReqLineaProv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRfqLineaProv")
    @SequenceGenerator(name = "seqRfqLineaProv", sequenceName = "SEQ_RFQ_LINEA_PROV", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REQ_LINEA_PROV", nullable = false)
    private Integer idReqLineaProv;

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private BigDecimal precioMercado;

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private BigDecimal precioUnitario;

    private String moneda;

    @Size(max = 50)
    private String tiempoEntrega;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String declinada;

    @Size(max = 100)
    @Column(length = 100)
    private String motivoDeclinada;

    @Size(max = 2500)
    @Column(length = 2500)
    private String comentario;

    private String descAlternativa;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNecesidadRfq;

    private Character enviadoProv;

    @Size(max = 250)
    private String especificaionTermEspeciales;

    private Integer idEstatus;

    private String idUnidadDeMedida;

    @Size(max = 300)
    @Column(length = 300)
    private String comentarioCotizacion;

    private BigDecimal idRazonSelCotizacion;

    private String mejorOpcion;

    private String iva;

    private BigInteger mostrarDocQuot;

    private Character checkFlag;

    @ManyToOne()
    @JoinColumn(name = "LLAVE_ID", nullable = false)
    private NvcTblRfqLinea rfqLinea;

    @ManyToOne()
    @JoinColumn(name = "ID_RFQ_PROV", referencedColumnName = "LLAVE_ID")
    private NvcTblRfqProv idRfqProv;

    @Column(name = "CAPTURADO_POR", length = 1)
    private String capturadoPor;

    public NvcTblReqLineaProv() {
    }

    public NvcTblReqLineaProv(Integer idReqLineaProv) {
        this.idReqLineaProv = idReqLineaProv;
    }

    public NvcTblReqLineaProv(Integer idReqLineaProv, BigDecimal precioMercado, BigDecimal precioUnitario, String moneda, String declinada) {
        this.idReqLineaProv = idReqLineaProv;
        this.precioMercado = precioMercado;
        this.precioUnitario = precioUnitario;
        this.moneda = moneda;
        this.declinada = declinada;
    }

    public NvcTblReqLineaProv(NvcTblRfqLinea rfqLinea) {
        this.rfqLinea = rfqLinea;
    }

}

