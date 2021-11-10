package com.metalsa.supplier.pojo;

import java.util.List;

import lombok.Data;

@Data
public class NvcTblDocsCotizacionPojo {
    private Integer idCotizacion;
    private List<AdjuntoPojo> adjuntos;
}