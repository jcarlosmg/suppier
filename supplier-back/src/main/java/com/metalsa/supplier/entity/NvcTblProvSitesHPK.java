package com.metalsa.supplier.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author edgar.leal
 */
@Embeddable
public class NvcTblProvSitesHPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROVEEDOR")
    private Long idProveedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SUCURSAL_PROVEEDOR")
    private Long idSucursalProveedor;

    public NvcTblProvSitesHPK() {
    }

    public NvcTblProvSitesHPK(Long idProveedor, Long idSucursalProveedor) {
        this.idProveedor = idProveedor;
        this.idSucursalProveedor = idSucursalProveedor;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Long getIdSucursalProveedor() {
        return idSucursalProveedor;
    }

    public void setIdSucursalProveedor(Long idSucursalProveedor) {
        this.idSucursalProveedor = idSucursalProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        hash += (idSucursalProveedor != null ? idSucursalProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblProvSitesHPK)) {
            return false;
        }
        NvcTblProvSitesHPK other = (NvcTblProvSitesHPK) object;
        if (!this.idProveedor.equals(other.idProveedor)) {
            return false;
        }
        return this.idSucursalProveedor.equals(other.idSucursalProveedor);
    }

    @Override
    public String toString() {
        return "com.metalsa.core.model.NvcTblProvSitesHPK[ idProveedor=" + idProveedor + ", idSucursalProveedor=" + idSucursalProveedor + " ]";
    }
    
}
