package com.metalsa.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

import com.metalsa.supplier.entity.CatItemDoc;
import com.metalsa.supplier.entity.SpTblCatalogoItemDoc;

@Repository
public interface SpTblCatalogoItemDocRepository extends JpaRepository<SpTblCatalogoItemDoc, Integer>{
    // CatItemDoc getByIdItemDoc(Integer idCatalogoItemDoc);

     @Query(value="select u from SpTblCatalogoItemDoc u where idCatalogoUen = ?1")
    List<SpTblCatalogoItemDoc> findDocsByIdCat(Long idCat);
	
	@Query(value="select u from SpTblCatalogoItemDoc u where idItem = ?1")
    List<SpTblCatalogoItemDoc> findDocsByIdItem(Long idItem);

    @Query(value="select u from SpTblCatalogoItemDoc u where u.idItem = ?1 and u.nombreArchivo = ?2")
    List<SpTblCatalogoItemDoc> findByItemNameFile(Long idItem, String nombreArchivo);

    @Transactional
    @Modifying
    @Query(value="delete from SpTblCatalogoItemDoc u where u.idItem = ?1 and u.nombreArchivo = ?2")
    void deleteByName(Long idItem, String nombreArchivo);
}
