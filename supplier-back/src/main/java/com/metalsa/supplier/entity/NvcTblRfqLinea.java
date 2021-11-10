package com.metalsa.supplier.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author mlopez
 */
@Data
@Entity
@Table(name = "NVC_TBL_RFQ_LINEA")
public class NvcTblRfqLinea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRfqLinea")
    @SequenceGenerator(name = "seqRfqLinea", sequenceName = "SEQ_RFQ_LINEA", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "LLAVE_ID")
    private Integer llaveId;

    /*@JoinColumn(name = "ID_RFQ", referencedColumnName = "ID_RFQ")
    @ManyToOne(optional = false)
    private NvcTblRfq idRfq;*/
    private Integer idEstatus;

    @Column(name = "HABILITADA_PARA_SELECCION")
    private Character habilitadaParaSeleccion;

    /* @OneToMany(cascade = CascadeType.ALL, mappedBy = "llaveId", fetch = FetchType.LAZY)
    private Collection<NvcTblReqLineaProv> nvcTblReqLineaProvCollection;*/
    @Size(max = 100)
    @Column(name = "MOTIVO_CAMBIO_ESTATUS", length = 100)
    private String motivoCambioEstatus;

    // private Integer idRfqLinea;
    //@OrderBy("llaveId.detalleDeRequisicion.detalleDeRequisicionPK.idRequisicion, llaveId.detalleDeRequisicion.detalleDeRequisicionPK.idPartida ASC")
    /*@OneToMany(mappedBy = "rfqLinea", fetch = FetchType.LAZY)
    private List<NvcTblDocumentosRfq> documentosRfqList;*/
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rfqLinea", fetch = FetchType.LAZY)
    private List<NvcTblReqLineaProv> nvcTblReqLineaProvList;
    
    @Size(max = 100)
    @Column(name = "COMENTARIO")
    private String comentario;

    @JoinColumns({
        @JoinColumn(name = "ID_REQUISICION", referencedColumnName = "ID_REQUISICION")
        ,
        @JoinColumn(name = "ID_LINEA", referencedColumnName = "ID_PARTIDA")})
    @ManyToOne(optional = false)
    private DetalleRequisicion detalleRequisicion;

    /*public List<NvcTblDocumentosRfq> getNvcTblDocumentosRfqList() {
        if(this.documentosRfqList == null) {
            return new ArrayList<>();
        }
        
        return this.documentosRfqList;
    }*/
}

