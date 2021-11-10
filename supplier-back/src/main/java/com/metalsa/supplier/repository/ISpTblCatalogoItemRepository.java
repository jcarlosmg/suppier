package com.metalsa.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

import com.metalsa.supplier.entity.SpTblCatalogoItem;

@Repository
public interface ISpTblCatalogoItemRepository extends JpaRepository<SpTblCatalogoItem, Long>{     

    @Query(value="SELECT c FROM SpTblCatalogoItem c WHERE c.idCatalogoUen = ?1")
    List<SpTblCatalogoItem> findByLoc(Long id);

    @Transactional
	@Modifying
    @Query(value="DELETE FROM SpTblCatalogoItem c WHERE c.idCatalogoUen = ?1")
    void deleteByLoc(Long id);

}
