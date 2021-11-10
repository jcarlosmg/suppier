package com.metalsa.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.metalsa.supplier.entity.SpTblCatalogo;
import com.metalsa.supplier.entity.Uom;
import com.metalsa.supplier.pojo.ICustomItemsSelect;
import com.metalsa.supplier.pojo.PoVendorPojo;

@Repository
public interface ISpTblCatalogoRepository extends JpaRepository<SpTblCatalogo, Long>{

    List<SpTblCatalogo> findByLocalization();

    @Query(value = "select u.ORGANIZATION_ID as id, u.ORGANIZATION_NAME as descripcion from OA_UENS u join SP_TBL_CAT_LOCALIZACION x on x.id_uen = u.ORGANIZATION_ID where x.ID_SP_TBL_CATALOGO = ?1", nativeQuery = true)
    public List<ICustomItemsSelect> getUensByCatalogo(Long idCat);

    @Query(value = "SELECT id_moneda as key, moneda as descripcion FROM NVC_TBL_MONEDAS_H  WHERE active = 1 AND id_moneda not in('MXN') order by id_Moneda asc", nativeQuery = true)
    public List<ICustomItemsSelect> getMonedasActivas();
    
    @Query(value = "SELECT id_unidad_de_medida as key, unidad_de_medida as descripcion from NVC_VM_OA_UNIDADES_DE_MEDIDA WHERE lenguaje = ?1", nativeQuery = true)
    public List<ICustomItemsSelect> getUnidadMedidaByIdioma(String idioma);

    @Query(value = "SELECT vendor_id, vendor_name FROM po_vendors_s WHERE vendor_id  = portal_prov_decodemm_number(?1)", nativeQuery = true)
    public List<PoVendorPojo> getProveedorByVendorEncoded(String vendorEncoded);
}
