package com.metalsa.supplier.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author mlopez
 */
@Data
@Entity
@Table(name = "NVC_TBL_DOCS_COTIZACION")
public class NvcTblDocsCotizacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqDocsCoti")
    @SequenceGenerator(name = "seqDocsCoti", sequenceName = "NVC_SEQ_DOCS_COTIZACION", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_documento", nullable = false)
    private Integer idDocumento;

    private Character enviado;

    @Size(max = 150)
    @Column(length = 150)
    private String descripcion;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Size(max = 100)
    @Column(length = 100)
    private String mime;

    private BigInteger tamano;

    @Size(max = 300)
    @Column(length = 300)
    private String ruta;

    @Size(max = 100)
    @Column(name = "nombre_formato", length = 100)
    private String nombreFormato;

    private Integer consecutivo;

    @Size(max = 100)
    @Column(name = "RUTA_RAIZ", length = 300)
    private String rutaRaiz;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_modifica")
    private Date fechaModifica;

    @Column(name = "usuario_modifica")
    private String usuarioModifica;

    @Column(name = "id_usuario")
    private String idUsuario;

    @Column(name = "nombre")
    private String nombre;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(referencedColumnName = "ID_COTIZACION", name = "ID_COTIZACION", nullable = true)
    private NvcTblCotizaciones idCotizacion;

}

