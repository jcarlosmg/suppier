package com.metalsa.supplier.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "DC_ESTATUS")
public class DcEstatus implements Serializable{
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 22)
    private BigDecimal scId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(nullable = false, length = 80)
    private String descEstatus;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(nullable = false, length = 80)
    private String descEsp;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(nullable = false, length = 80)
    private String descIng;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(nullable = false, length = 80)
    private String descPort;
    
    @Size(max = 180)
    @Column(length = 180)
    private String origenEstatus;
    
    @Size(max = 800)
    @Column(length = 800)
    private String descripcion;
    
    @Size(max = 50)
    @Column(length = 50)
    private String active;
}
