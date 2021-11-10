package com.metalsa.supplier.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.metalsa.supplier.pojo.ClasificacionArbol;
import com.metalsa.supplier.pojo.ContactsPojo;
import com.metalsa.supplier.pojo.FamiliaPojo;
import com.metalsa.supplier.pojo.LocationUen;
import com.metalsa.supplier.pojo.SitePojo;
import com.metalsa.supplier.pojo.UenPojo;

import lombok.Data;

@Entity
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "Familia.FirstLevel",
            procedureName = "nvc_pkg_families_spx.get_families_first_level",
            resultSetMappings = "FamiliaPojo.FamiliaLang",
            parameters = {
                @StoredProcedureParameter(name = "p_id_uen", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_idioma", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "out_value", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "out_message", type = String.class, mode = ParameterMode.OUT),
                @StoredProcedureParameter(name = "out_cursor", type = void.class, mode = ParameterMode.REF_CURSOR),
                @StoredProcedureParameter(name = "p_tipo_requisicion", type = Integer.class, mode = ParameterMode.IN)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "Familia.SecondLevel",
            procedureName = "nvc_pkg_families_spx.get_families_second_level",
            resultSetMappings = "FamiliaPojo.FamiliaLang",
            parameters = {
                @StoredProcedureParameter(name = "p_id_family_parent", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_idioma", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "out_value", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "out_message", type = String.class, mode = ParameterMode.OUT),
                @StoredProcedureParameter(name = "out_cursor", type = void.class, mode = ParameterMode.REF_CURSOR)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "Familia.ThirdLevel",
            procedureName = "nvc_pkg_families_spx.get_families_third_level",
            resultSetMappings = "FamiliaPojo.FamiliaLang",
            parameters = {
                @StoredProcedureParameter(name = "p_id_family_parent", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_id_idioma", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "out_value", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "out_message", type = String.class, mode = ParameterMode.OUT),
                @StoredProcedureParameter(name = "out_cursor", type = void.class, mode = ParameterMode.REF_CURSOR)
            }
    ),
    
    @NamedStoredProcedureQuery(
            name = "Catalogos.GetArbolByIdSub", 
            procedureName = "nvc_pkg_families_spx.get_arbol_by_id_sub",
            resultSetMappings = "Catalogo.ArbolByIdSub",
            parameters = {
                @StoredProcedureParameter(name = "p_id_sub_familia", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "out_cursor", type = void.class, mode = ParameterMode.REF_CURSOR)
            }
    ),
    
    @NamedStoredProcedureQuery(
                name = "Catalogos.UensBySupplier", 
                procedureName = "pkg_catalogo_sp.getUensBySupplier",
                resultSetMappings = "Catalogo.Uens",
                parameters = {
                    @StoredProcedureParameter(name = "p_id_proveedor", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "out_cursor", type = void.class, mode = ParameterMode.REF_CURSOR)
                }
        ),
    @NamedStoredProcedureQuery(
                name = "Catalogos.SitesBySupplierUen", 
                procedureName = "pkg_catalogo_sp.getSitesBySupplierUen",
                resultSetMappings = "Catalogo.Sites",
                parameters = {
                    @StoredProcedureParameter(name = "p_id_proveedor", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_id_uen", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "out_cursor", type = void.class, mode = ParameterMode.REF_CURSOR)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "Catalogos.ContactBySiteSupplierUen", 
                procedureName = "pkg_catalogo_sp.getContactBySiteSupplierUen",
                resultSetMappings = "Catalogo.Contacts",
                parameters = {
                    @StoredProcedureParameter(name = "p_id_proveedor", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_id_uen", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "out_cursor", type = void.class, mode = ParameterMode.REF_CURSOR)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "Catalogos.LocationsByUen", 
                procedureName = "pkg_catalogo_sp.getLocationsByUen",
                resultSetMappings = "Catalogo.Locations",
                parameters = {
                    @StoredProcedureParameter(name = "p_id_uen", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "out_cursor", type = void.class, mode = ParameterMode.REF_CURSOR)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "Catalogos.SaveCatalogo", 
                procedureName = "pkg_catalogo_sp.saveCatalogo",
                parameters = {
                    @StoredProcedureParameter(name = "p_id_catalogo", type = Integer.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_id_proveedor", type = Integer.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_nombre_catalogo", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_fecha_inicio_vigencia", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_fecha_fin_vigencia", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_publicado", type = Integer.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_fecha_creacion", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_fecha_actualizacion", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_usuario_creacion", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_usuario_actualizacion", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_activo", type = Integer.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_tipo_aviso_terminacion", type = Integer.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_id_prov_punchout", type = Integer.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "out_value", type = String.class, mode = ParameterMode.OUT),
                    @StoredProcedureParameter(name = "out_message", type = String.class, mode = ParameterMode.OUT)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "Validar.ValidaProveedor", 
                procedureName = "pkg_sp_utils.valida_proveedor",
                parameters = {
                    @StoredProcedureParameter(name = "p_usuario", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_password", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_token", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_quien", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "mensaje", type = String.class, mode = ParameterMode.OUT)
                }
        )
})

@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "FamiliaPojo.FamiliaLang",
            classes = @ConstructorResult(
                    targetClass = FamiliaPojo.class,
                    columns = {
                        @ColumnResult(name = "id_familia", type = Long.class),
                        @ColumnResult(name = "nombre_familia", type = String.class),
                        @ColumnResult(name = "segmento_1", type = String.class),
                        @ColumnResult(name = "segmento_2", type = String.class),
                        @ColumnResult(name = "segmento_3", type = String.class)
                    }
            )
    ),
    @SqlResultSetMapping(
            name = "Catalogo.Uens",
            classes = @ConstructorResult(
                    targetClass = UenPojo.class,
                    columns = {
                        @ColumnResult(name = "organization_id", type = Integer.class),
                        @ColumnResult(name = "organization_name", type = String.class)
                        
                    }
            )
    ),
    @SqlResultSetMapping(
            name = "Catalogo.Sites",
            classes = @ConstructorResult(
                    targetClass = SitePojo.class,
                    columns = {
                        @ColumnResult(name = "id_sucursal_proveedor", type = Integer.class),
                        @ColumnResult(name = "vendor_site_code", type = String.class)
                        
                    }
            )
    ),
    @SqlResultSetMapping(
            name = "Catalogo.Contacts",
            classes = @ConstructorResult(
                    targetClass = ContactsPojo.class,
                    columns = {
                        @ColumnResult(name = "vendor_contact_id", type = Integer.class),
                        @ColumnResult(name = "first_name", type = String.class),
                        @ColumnResult(name = "last_name", type = String.class),
                        @ColumnResult(name = "email_stop", type = String.class)
                    }
            )
    ),
    @SqlResultSetMapping(
            name = "Catalogo.Locations",
            classes = @ConstructorResult(
                    targetClass = LocationUen.class,
                    columns = {
                        @ColumnResult(name = "id_localizacion", type = Integer.class),
                        @ColumnResult(name = "nombre_localizacion", type = String.class)
                    }
            )
    ),
    @SqlResultSetMapping(
            name = "Catalogo.ArbolByIdSub",
            classes = @ConstructorResult(
                    targetClass = ClasificacionArbol.class,
                    columns = {
                        @ColumnResult(name = "id_Categoria", type = Long.class),
                        @ColumnResult(name = "id_familia", type = Long.class),
                        @ColumnResult(name = "id_sub_familia", type = Long.class)
                        
                    }
            )
    )
})

@Data
@Table(name = "NVC_TBL_GASTO_ADICIONAL")
public class NvcTblGastoAdicional implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGastoAdicional")
    @SequenceGenerator(name = "seqGastoAdicional", sequenceName = "SEQ_GASTO_ADICIONAL", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_GASTO_ADICIONAL")
    private Integer idGastoAdicional;

    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;

    @Size(max = 18)
    @Column(name = "ID_MONEDA")
    private String idMoneda;

    @Size(max = 200)
    @Column(name = "DETALLE_GASTO")
    private String detalleGasto;

    @Column(name = "PRECIO_UNITARIO")
    private BigDecimal precioUnitario;
    
    private String idUnidadDeMedida;

    @Column(name = "TIEMPO_ENTREGA")
    private Integer tiempoEntrega;

    @Column(name = "ID_CLASIFICACION")
    private Integer idClasificacion;
    
    @Column(name = "ID_CATEGORIA")
    private Integer idCategoria;
    
    @Column(name = "ID_FAMILIA")
    private Integer idFamilia;
    
    @Column(name = "ID_SUBFAMILIA")
    private Integer idSubfamilia;
   
    @JoinColumn(name = "ID_REQ_LINEA_PROV", referencedColumnName = "ID_REQ_LINEA_PROV")
    @ManyToOne(optional = false)
    private NvcTblReqLineaProv idReqLineaProv;
    
    @Transient
    private boolean eliminar;

}

