package com.metalsa.supplier.pojo;

import lombok.Data;

@Data
public class LocationUen {
    Integer id_localizacion;
    String nombre_localizacion;
    
    public LocationUen() {
    }

    public LocationUen(Integer id_localizacion, String nombre_localizacion) {
        this.id_localizacion = id_localizacion;
        this.nombre_localizacion = nombre_localizacion;
    }
}
