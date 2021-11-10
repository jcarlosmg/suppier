package com.metalsa.supplier.repository;

import java.util.List;

import com.metalsa.supplier.entity.Uom;
import com.metalsa.supplier.pojo.UomProcedure;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUomReciboRepository extends PagingAndSortingRepository<Uom,Long>,UomProcedure{
    @Override
    @Procedure(name = "obtenerListaUom")
    List<Uom> obtenerListaUom(@Param("p_unit_of_measure_sel") String unidad);
}
