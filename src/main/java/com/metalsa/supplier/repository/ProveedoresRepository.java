package com.metalsa.supplier.repository;

import java.math.BigDecimal;
import java.util.List;

import com.metalsa.supplier.entity.NvcTblOaProveedoresH;
import com.metalsa.supplier.pojo.IUserPojo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedoresRepository extends PagingAndSortingRepository<NvcTblOaProveedoresH, Long>{

    /**
    * devuelve los proveedores de las UEN's pero que tengan elementos de catalogos activos
    */
    @Query(value = "select distinct prov.* from nvc_tbl_oa_proveedores_h prov \n" +
                "join nvc_tbl_prov_sites_h puen on prov.id_proveedor = puen.id_proveedor \n" +
                "join nvc_tbl_catalogo c on prov.id_proveedor = c.id_proveedor and c.activo = 1\n" +
                "join nvc_tbl_catalogo_uen cuen on c.id_catalogo = cuen.id_catalogo and cuen.id_uen = puen.id_uen and cuen.activo = 1 \n" +
                "join nvc_tbl_catalogo_item item on item.id_catalogo_uen = cuen.id_catalogo_uen and item.item_publicado = 45 and item.activo = 1\n" +
                "where puen.id_uen in (?1) and lower(puen.vendor_site_code) <> 'office'\n" +
                "order by prov.nombre_proveedor",
            nativeQuery = true
    )    
    List<NvcTblOaProveedoresH> findProveedoresByUens(List<Long> ids);

    // Iterable<NvcTblOaProveedoresH> findPorUen(int uen);
    // Iterable<NvcTblOaProveedoresH> findDispDirigidosPorUen(@Param("idUen") int idUen, @Param("query") String query);
    // Iterable<NvcTblOaProveedoresH> findLikeByUenWithFad(@Param("uens") String uens, @Param("query") String query);
    // Iterable<NvcTblOaProveedoresH> findLikeWithFad(@Param("query") String query);
    // Iterable<NvcTblOaProveedoresH> proveedoresPorUenRequisicion(String idUsuario, int uen);
    // Iterable<NvcTblOaProveedoresH> proveedoresPorRequisicion(String idUsuario);
    // List<NvcTblOaProveedoresH> findByIdProveedor(Integer idProveedor);
    
    @Query(name = "NvcTblOaProveedoresH.findLikeNombreByUen_")
    List<NvcTblOaProveedoresH> buscarLikeNombreByUen_(String nombre, BigDecimal idUen);    

    @Query(value = "select vendor_id as vendorId, vendor_name as vendorName, segment1 as segment1, attribute2 as attribute2, decode(invoice_currency_code, 'USD','en','es') as idioma from ap_suppliers where segment1 = ?1 and attribute2= ?2", nativeQuery = true)
    public IUserPojo getUser(String strUser, String pass);
}
