package com.metalsa.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.metalsa.supplier.entity.NvcTblDocsCotizacion;

@Repository
public interface NvcTblDocsCotizacionRepository extends JpaRepository<NvcTblDocsCotizacion, Integer>{

    NvcTblDocsCotizacion getByIdDocumento(Integer idDocumento);
}
