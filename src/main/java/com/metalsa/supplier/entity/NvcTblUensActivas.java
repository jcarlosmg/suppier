package com.metalsa.supplier.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;


@Entity(name="PedidosEspeciales")
@Table(name = "nvc_tbl_uens_activas")
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "nvc_tbl_uens_activas.findAll",
            query = "SELECT\n"
            + "    ID,\n"
            + "    ID_UEN,\n"
            + "    FECHA_CREACION,\n"
            + "    FAD,\n"
            + "    NEW_CATEGORY_SET,\n"
            + "    QUOTATION_POLICY_RESTICTED,\n"
            + "    ACTIVA_PEDIDO_ESPECIAL,\n"
            + "    ORGANIZATION_NAME\n"
            + "FROM NVC_TBL_UENS_ACTIVAS uensactivas\n"
            + "JOIN oa_uens uens\n"
            + "on uens.organization_id = uensactivas.id_uen\n",
            resultClass = NvcTblUensActivas.class
    ),
    @NamedNativeQuery(
            name = "nvc_tbl_uens_activas.update",
            query = "UPDATE NVC_TBL_UENS_ACTIVAS "
            + "SET "
            + "    ACTIVA_PEDIDO_ESPECIAL = ?2 "
            + "WHERE ID = ?1 "
    )
})
public class NvcTblUensActivas implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "ID_UEN")
    private Long id_uen;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "FECHA_CREACION")
    private Date fecha_creacion;
    
    @Column(name = "FAD")
    private Integer fad;
    
    @Column(name = "NEW_CATEGORY_SET")
    private Integer new_category_set;
    
    @Column(name = "QUOTATION_POLICY_RESTICTED")
    private Integer quotation_policy_restricted;
    
    @Column(name = "ACTIVA_PEDIDO_ESPECIAL")
    private Integer activa_pedido_especial;
    
    @Column(name = "ORGANIZATION_NAME")
    private String organization_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_uen() {
        return id_uen;
    }

    public void setId_uen(Long id_uen) {
        this.id_uen = id_uen;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Integer getFad() {
        return fad;
    }

    public void setFad(Integer fad) {
        this.fad = fad;
    }

    public Integer getNew_category_set() {
        return new_category_set;
    }

    public void setNew_category_set(Integer new_category_set) {
        this.new_category_set = new_category_set;
    }

    public Integer getQuotation_policy_restricted() {
        return quotation_policy_restricted;
    }

    public void setQuotation_policy_restricted(Integer quotation_policy_restricted) {
        this.quotation_policy_restricted = quotation_policy_restricted;
    }

    public Integer getActiva_pedido_especial() {
        return activa_pedido_especial;
    }

    public void setActiva_pedido_especial(Integer activa_pedido_especial) {
        this.activa_pedido_especial = activa_pedido_especial;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }
    
    private static final long serialVersionUID = 1L;
    

}
