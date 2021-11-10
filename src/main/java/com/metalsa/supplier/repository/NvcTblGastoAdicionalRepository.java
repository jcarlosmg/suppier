package com.metalsa.supplier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.metalsa.supplier.entity.NvcTblGastoAdicional;
import com.metalsa.supplier.pojo.FamiliaPojo;

@Repository
public interface NvcTblGastoAdicionalRepository extends JpaRepository<NvcTblGastoAdicional, Integer>{
    
    @Query(value = "SELECT n FROM NvcTblGastoAdicional n WHERE n.idReqLineaProv.idReqLineaProv = :idReqLineaProv order by n.idGastoAdicional")
    List<NvcTblGastoAdicional> getByIdReqLineaProv(@Param("idReqLineaProv") Integer idReqLineaProv);
    @Procedure(name = "Familia.FirstLevel")
    List<FamiliaPojo> getFamiliaFirstLevel(@Param("p_id_uen") Integer idUen,@Param("p_id_idioma") String idIdioma);
    @Procedure(name = "Familia.SecondLevel")
    List<FamiliaPojo> getFamiliaSecondLevel(@Param("p_id_family_parent") Integer idFamilyParent,@Param("p_id_idioma") String idIdioma);
    @Procedure(name = "Familia.Familia.ThirdLevel")
    List<FamiliaPojo> getFamiliaThirdLevel(@Param("p_id_family_parent") Integer idFamilyParent,@Param("p_id_idioma") String idIdioma);
}

