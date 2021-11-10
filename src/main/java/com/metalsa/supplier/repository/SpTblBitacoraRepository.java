package com.metalsa.supplier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.metalsa.supplier.entity.Bitacora;

@Repository
public interface SpTblBitacoraRepository extends JpaRepository<Bitacora, Long>{

	@Query(value="select b from Bitacora b where idCatalogo = ?1")
    List<Bitacora> findLogByIdCatalog(Long idCat);
}
