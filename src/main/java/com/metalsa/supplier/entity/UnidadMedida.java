package com.metalsa.supplier.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author mlopez
 */
@Entity(name = "nvc_vm_oa_unidades_de_medida")
@Data
public class UnidadMedida implements Serializable{
    
    @Id
    private String id;
    private String medida;
    
    public UnidadMedida(String id, String medida) {
        this.id= id;
        this.medida = medida;
    }
    
    public UnidadMedida(){
        
    }
}