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
// import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "SP_TBL_CATALOGO_ITEM")

public class SpTblCatalogoItem implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTblCatItem")
    @SequenceGenerator(name = "seqTblCatItem", sequenceName = "seq_sp_tbl_catalogo_item", allocationSize = 1)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "ID_ITEM")
    private Long idItem;
    @Column(name = "ID_CATALOGO_UEN")
    private Long idCatalogoUen;
    //@Size(max = 50)
    @Column(name = "CODIGO_ITEM")
    private String codigoItem;
    //@Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    //@Size(max = 50)
    @Column(name = "NUMERO_PARTE_FABRICANTE")
    private String numeroParteFabricante;
    //@Size(max = 50)
    @Column(name = "NUMERO_PARTE_PROVEEDOR")
    private String numeroParteProveedor;
    //@Size(max = 100)
    @Column(name = "FABRICANTE")
    private String fabricante;
    //@Size(max = 100)
    @Column(name = "MATERIAL")
    private String material;
    //@Size(max = 100)
    @Column(name = "COLOR")
    private String color;
    //@Size(max = 100)
    @Column(name = "MEDIDAS")
    private String medidas;
    @Column(name = "UDM")
    private String udm;
    @Column(name = "PRECIO_UNITARIO")
    private Double precioUnitario;
    @Column(name = "PRECIO_MERCADO")
    private Double precioMercado;
    @Column(name = "MUESTRA_PRECIO_MERCADO")
    private Character muestraPrecioMercado;
    @Column(name = "TIEMPO_ENTREGA")
    private Long tiempoEntrega;
    //@Size(max = 256)
    @Column(name = "ID_MONEDA")
    private String idMoneda;
    @Column(name = "ID_IVA")
    private Long idIva;
    @Column(name = "ID_SUBFAMILIA")
    private Long idSubfamilia;
    @Column(name = "ID_ESTATUS")
    private Long idEstatus;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    //@Size(max = 256)
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    //@Size(max = 256)
    @Column(name = "USUARIO_ACTUALIZACION")
    private String usuarioActualizacion;
    //@Size(max = 200)
    @Column(name = "SPOT_MARCA")
    private String spotMarca;
    //@Size(max = 500)
    @Column(name = "SPOT_NOMBRE_GENERICO")
    private String spotNombreGenerico;
    @Column(name = "SPOT_FECHA_NECESIDAD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date spotFechaNecesidad;
    @Column(name = "SPOT_URGENTE")
    private Character spotUrgente;
    @Column(name = "ID_LINEA")
    private Long idLinea;
    @Column(name = "ID_RAZON_URGENCIA")
    private Long idRazonUrgencia;
    //@Size(max = 500)
    @Column(name = "SPOT_COMENTARIOS_COMPRADOR")
    private String spotComentariosComprador;
    @Column(name = "ACTIVO")
    private Long activo;
    @Column(name = "ITEM_PUBLICADO")
    private Long itemPublicado;
    //@Size(max = 256)
    @Column(name = "ADMINISTRADOR")
    private String administrador;
    @Column(name = "NUM_APROBACIONES")
    private Long numAprobaciones;
    @Column(name = "MAX_PRECIO_UNITARIO")
    private Double maxPrecioUnitario;
    @Column(name = "DESCUENTO")
    private Double descuento;
    @Column(name = "ID_TEMPLATE")
    private Long idTemplate;
}
