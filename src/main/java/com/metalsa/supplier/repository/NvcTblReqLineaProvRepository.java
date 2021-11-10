package com.metalsa.supplier.repository;

import java.math.BigInteger;
import java.util.List;

import com.metalsa.supplier.entity.NvcTblReqLineaProv;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mlopez
 */

@Repository
public interface NvcTblReqLineaProvRepository extends PagingAndSortingRepository<NvcTblReqLineaProv, Integer> {

    @Query(value = "SELECT rlp FROM NvcTblReqLineaProv rlp "
            + "INNER JOIN rlp.idRfqProv rp "
            + "WHERE rp.idRfq.idRfq = :idRfq "
            + "AND rp.nvcTblProvSitesH.nvcTblProvSitesHPK.idProveedor = :idProveedor "
            + "AND rlp.idEstatus = (SELECT es.scId FROM DcEstatus es WHERE es.descEstatus = 'PENDIENTE') "
            + "AND rlp.declinada in ('NO', 'TP')"
           // + "AND rp.nvcTblProvSitesH.nvcTblProvSitesHPK.idSucursalProveedor = :idSucProveedor "
            + " ORDER BY rlp.rfqLinea.detalleRequisicion.requisicion, "
            + "rlp.rfqLinea.detalleRequisicion.linea"
            )
    List<NvcTblReqLineaProv> findRquisByRfqAndSupplier(@Param("idRfq") Integer idRfq, @Param("idProveedor") Long idProveedor);
}