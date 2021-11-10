package com.metalsa.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.metalsa.supplier.entity.NvcTblUensActivas;
import com.metalsa.supplier.entity.SpTblCatLocalizacion;
import com.metalsa.supplier.pojo.ICustomItemsSelect;

@Repository
public interface ISpTblCatLocalizacionRepository extends JpaRepository<SpTblCatLocalizacion, Long>{

    @Query(value = "SELECT x FROM SpTblCatLocalizacion x where x.idSpTblCatalogo = ?1")
    public List<SpTblCatLocalizacion> findByCatalogo(Long id);

    @Query(value = "select u.ORGANIZATION_ID as id, u.ORGANIZATION_NAME as descripcion from OA_UENS u join SP_TBL_CAT_LOCALIZACION x on x.id_uen = u.ORGANIZATION_ID where x.ID_SP_TBL_CATALOGO = ?1", nativeQuery = true)
    public List<ICustomItemsSelect> getUensByCatalogo(Long idCat);

}
