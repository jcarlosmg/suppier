package com.metalsa.supplier.entity;

import java.util.List;

import com.metalsa.supplier.pojo.AdjuntoPojo;

import lombok.Data;

@Data
public class NvcTblDocsCatalogoPojo {
    private Integer idCatalogo;
    private Integer idItem;
    private List<AdjuntoPojo> adjuntos;
}
