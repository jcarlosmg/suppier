package com.metalsa.supplier.pojo;

import lombok.Data;

@Data
public class SitePojo {
    
    Integer id_sucursal_proveedor;
    String vendor_site_code;

    public SitePojo() {
    }
    
    public SitePojo(Integer id_sucursal_proveedor, String vendor_site_code) {
        this.id_sucursal_proveedor = id_sucursal_proveedor;
        this.vendor_site_code = vendor_site_code;
    } 
}
