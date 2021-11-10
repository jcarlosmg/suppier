package com.metalsa.supplier.pojo;

import java.math.BigDecimal;
import java.util.List;

public class RfqPojo {

    
    private BigDecimal idRfq;
    private String fechaCreacion;
    private String inicioVigencia;
    private String finVigencia;
    private String vencido;
    private String shipTo;
    private String billTo;
    private BigDecimal idIncoterm;
    private String incotermDesc;
    private BigDecimal idUen;
    private String nombreUen;

    private String enviado;
    private String cargoA;
    private String estatusRfq;
    private String idCotizacionProveedor;

    private List<RfqLineaPojo> requisiciones;

    public BigDecimal getIdRfq() {
        return idRfq;
    }

    public void setIdRfq(BigDecimal idRfq) {
        this.idRfq = idRfq;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEnviado() {
        return enviado;
    }

    public void setEnviado(String enviado) {
        this.enviado = enviado;
    }

    public String getCargoA() {
        return cargoA;
    }

    public void setCargoA(String cargoA) {
        this.cargoA = cargoA;
    }

    public String getEstatusRfq() {
        return estatusRfq;
    }

    public void setEstatusRfq(String estatusRfq) {
        this.estatusRfq = estatusRfq;
    }

    public List<RfqLineaPojo> getRequisiciones() {
        return requisiciones;
    }

    public void setRequisiciones(List<RfqLineaPojo> requisiciones) {
        this.requisiciones = requisiciones;
    }

    public BigDecimal getIdUen() {
        return idUen;
    }

    public void setIdUen(BigDecimal idUen) {
        this.idUen = idUen;
    }

    public String getNombreUen() {
        return nombreUen;
    }

    public void setNombreUen(String nombreUen) {
        this.nombreUen = nombreUen;
    }

    public String getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(String inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public String getFinVigencia() {
        return finVigencia;
    }

    public void setFinVigencia(String fin_vigencia) {
        this.finVigencia = fin_vigencia;
    }

    public String getVencido() {
        return vencido;
    }

    public void setVencido(String vencido) {
        this.vencido = vencido;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public String getBillTo() {
        return billTo;
    }

    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }

    public BigDecimal getIdIncoterm() {
        return idIncoterm;
    }

    public void setIdIncoterm(BigDecimal idIncoterm) {
        this.idIncoterm = idIncoterm;
    }

    public String getIncotermDesc() {
        return incotermDesc == null ? "N/A" : incotermDesc;
    }

    public void setIncotermDesc(String incotermDesc) {
        this.incotermDesc = incotermDesc;
    }

    public String getIdCotizacionProveedor() {
        return idCotizacionProveedor;
    }

    public void setIdCotizacionProveedor(String idCotizacionProveedor) {
        this.idCotizacionProveedor = idCotizacionProveedor;
    }


    
}
