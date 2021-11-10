package com.metalsa.supplier.pojo;

import java.math.BigDecimal;
import java.util.Date;


public class RfqLineaPojo {
    private BigDecimal idRequisicion;
    private BigDecimal idPartida;
    private String descripcion;
    private String estatus;
    private BigDecimal cantidad;
    private String udm;
    private String nombreProveedor;
    private String nombreComprador;
    private BigDecimal idProvInterno;
    private BigDecimal idProvExterno;
    private String estatusCotizacion;
    private BigDecimal seleccionada;
    private String urgente;
    private BigDecimal numAttachments;
    private BigDecimal countMessages;
    private String fechaRequerida;
    private String comentarios;
    private BigDecimal idEstatus;

    public BigDecimal getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(BigDecimal idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public BigDecimal getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(BigDecimal idPartida) {
        this.idPartida = idPartida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getUdm() {
        return udm;
    }

    public void setUdm(String udm) {
        this.udm = udm;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }
    
    public BigDecimal getIdProvInterno() {
        return idProvInterno;
    }

    public void setIdProvInterno(BigDecimal idProvInterno) {
        this.idProvInterno = idProvInterno;
    }

    public BigDecimal getIdProvExterno() {
        return idProvExterno;
    }

    public void setIdProvExterno(BigDecimal idProvExterno) {
        this.idProvExterno = idProvExterno;
    }

    public String getEstatusCotizacion() {
        return estatusCotizacion;
    }

    public void setEstatusCotizacion(String estatusCotizacion) {
        this.estatusCotizacion = estatusCotizacion;
    } 

    public BigDecimal getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(BigDecimal seleccionada) {
        this.seleccionada = seleccionada;
    }

    public String getUrgente() {
        return urgente;
    }

    public void setUrgente(String urgente) {
        this.urgente = urgente;
    }

    public BigDecimal getNumAttachments() {
        return numAttachments;
    }

    public void setNumAttachments(BigDecimal numAttachments) {
        this.numAttachments = numAttachments;
    }

    public BigDecimal getCountMessages() {
        return countMessages;
    }

    public void setCountMessages(BigDecimal countMessages) {
        this.countMessages = countMessages;
    }

    public String getFechaRequerida() {
        return fechaRequerida;
    }

    public void setFechaRequerida(String fechaRequerida) {
        this.fechaRequerida = fechaRequerida;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }
       
}
