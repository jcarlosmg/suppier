package com.metalsa.supplier.pojo;

import lombok.Data;

@Data
public class FamiliaPojo {
    Long idFamilia;
    String nombreFamilia;
    String segmento1;
    String segmento2;
    String segmento3;

    public FamiliaPojo() {
    }
    
    public FamiliaPojo(Long idFamilia, String nombreFamilia, String segmento1, String segmento2, String segmento3) {
        this.idFamilia = idFamilia;
        this.nombreFamilia = nombreFamilia;
        this.segmento1 = segmento1;
        this.segmento2 = segmento2;
        this.segmento3 = segmento3;
    }
}

