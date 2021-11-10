package com.metalsa.supplier.entity;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.REF_CURSOR;
import javax.persistence.StoredProcedureParameter;

@Entity
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "obtenerListaUom",
            resultClasses = Uom.class,
            procedureName = "UTILERIAS_PKG.p_getUoMList",
            parameters = {
                @StoredProcedureParameter(mode = REF_CURSOR, name = "V_CURSOR", type = void.class),
                @StoredProcedureParameter(mode = IN, name = "p_unit_of_measure_sel", type = String.class)
            }
    )
})
@IdClass(Uom.class)
public class Uom implements Serializable{
    @Id
    @Column(name ="UOM_CODE")
    private String uomCode;
    
    @Id
    @Column(name ="UNIT_OF_MEASURE")
    private String unitOfMeasure;

    public Uom() {
    }

    public Uom(String uomCode, String unitOfMeasure) {
        this.uomCode = uomCode;
        this.unitOfMeasure = unitOfMeasure;
    }
    
    public String getUomCode() {
        return uomCode;
    }

    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.uomCode);
        hash = 73 * hash + Objects.hashCode(this.unitOfMeasure);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Uom other = (Uom) obj;
        if (!Objects.equals(this.uomCode, other.uomCode)) {
            return false;
        }
        return Objects.equals(this.unitOfMeasure, other.unitOfMeasure);
    }
    
    

    @Override
    public String toString() {
        return "Uom{" + "uomCode=" + uomCode + ", unitOfMeasure=" + unitOfMeasure + '}';
    }
    
    
}

