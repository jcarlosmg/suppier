package com.metalsa.supplier.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.metalsa.supplier.entity.NvcTblUensActivas;

@Repository
public interface INvcTblUensActivasRepository extends JpaRepository<NvcTblUensActivas, Long>{
    @Query(name = "nvc_tbl_uens_activas.findAll")
    public List<NvcTblUensActivas> getAllList();

   
    
    @Modifying
    @Query(name = "nvc_tbl_uens_activas.update")
    public int updateById(Long id, Integer status);
    
}

