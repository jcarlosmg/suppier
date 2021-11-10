package com.metalsa.supplier.repository;

import java.sql.Struct;
import java.util.List;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
// import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;

import com.metalsa.supplier.entity.Moneda;
import com.metalsa.supplier.entity.UnidadMedida;
import com.metalsa.supplier.pojo.OaIvaUenPojo;
import com.metalsa.supplier.pojo.PoVendorPojo;
import com.metalsa.supplier.pojo.RfqLineaPojo;
import com.metalsa.supplier.pojo.RfqPojo;
import com.metalsa.supplier.pojo.RfqProveedorRequest;
import java.sql.CallableStatement;

// import org.hibernate.Session;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
// import org.hibernate.procedure.ProcedureCall;
import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j;

@Log4j
@Repository
public class PortalProveedorRepository {

    @PersistenceContext
    private EntityManager em;

    public PoVendorPojo getProveedorByVendorEncoded(String vendorEncoded) {
        PoVendorPojo poVendorPojo = new PoVendorPojo();
        if (vendorEncoded != null) {
            String nativeQuery = "SELECT vendor_id, vendor_name FROM po_vendors_s WHERE vendor_id  = portal_prov_decodemm_number(?1)";

            List<PoVendorPojo> result = em.createNativeQuery(nativeQuery, PoVendorPojo.class)
                    .setParameter(1, vendorEncoded)
                    .getResultList();

            if (result != null && !result.isEmpty()) {
                poVendorPojo = result.get(0);
            }else {poVendorPojo = null;}
        }
        return poVendorPojo;
    }
    
    public PoVendorPojo getProveedorByVendorEncoded2(String vendorEncoded) {
        PoVendorPojo poVendorPojo = new PoVendorPojo();
        if (vendorEncoded != null) {
        	String nativeQuery = "SELECT vendor_id, vendor_name FROM po_vendors_s WHERE vendor_id  = ?1";

            List<PoVendorPojo> result = em.createNativeQuery(nativeQuery, PoVendorPojo.class)
                    .setParameter(1, vendorEncoded)
                    .getResultList();

            if (result != null && !result.isEmpty()) {
                poVendorPojo = result.get(0);
            }else {poVendorPojo = null;}
        }
        return poVendorPojo;
    }
    
    public List<Moneda> getMonedasActivas() {
        try {
            List<Moneda> resultQuery;
            String nativeQuery = "SELECT id_moneda, moneda FROM NVC_TBL_MONEDAS_H  WHERE active = 1 AND id_moneda not in('MXN') order by id_Moneda asc";
            resultQuery = em.createNativeQuery(nativeQuery, Moneda.class).getResultList();
            return resultQuery;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------ Error in getMonedasActivas PortalProveedor ------");
        }
        return new ArrayList<>();
    }

    public List<UnidadMedida> getUnidadMedidaByIdioma(String idioma) {
        try {
            List<UnidadMedida> resultQuery;
            String nativeQuery = "SELECT id_unidad_de_medida id, unidad_de_medida medida from NVC_VM_OA_UNIDADES_DE_MEDIDA WHERE lenguaje = ?1";
            resultQuery = em.createNativeQuery(nativeQuery, UnidadMedida.class)
                    .setParameter(1, idioma)
                    .getResultList();
            return resultQuery;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------ Error in getUnidadMedidaByIdioma PortalProveedor ------");
        }
        return new ArrayList<>();
    }

    // @Query(value = "call get_rfq_captura_cotizacion_pro(?,?,?,?,?);", nativeQuery = true)
    // List<Car> findCarsAfterYear(@Param("year_in") Integer year_in);
    
    public List<RfqPojo> getCotizacionesProveedor(RfqProveedorRequest params) {
        List<RfqPojo> rfqListResult = new ArrayList<>();
        CallableStatement  ocs = null;
        Connection connection = null;

        try {
            String dateStarStr = null;
            String dateEndStr = null;
            connection = em.unwrap(SessionImpl.class).connection();
            if ((params.getStartDate() != null && !params.getStartDate().equals("")) && (params.getEndDate() != null && !params.getEndDate().equals(""))) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                SimpleDateFormat sdfNew = new SimpleDateFormat("dd/MM/yyyy");
                Date dateStart = sdf.parse(params.getStartDate());
                Date dateEnd = sdf.parse(params.getEndDate());

                dateStarStr = sdfNew.format(dateStart);
                dateEndStr = sdfNew.format(dateEnd);

                System.out.println("START_DATE: " + dateStarStr);
                System.out.println("END_DATE: " + dateEndStr);
            }
 
            ocs = (CallableStatement) connection.prepareCall("{call get_rfq_captura_cotizacion_pro(?,?,?,?,?)}");
            // ocs.setObject(1, params.getUenSelected());
            ocs.setObject(1, params.getIdProveedor());
            //ocs.setObject(1, params.getId_proveedor());
            ocs.setString(2, params.getIdioma());
            ocs.setString(3, dateStarStr);
            ocs.setString(4, dateEndStr);
            ocs.registerOutParameter(5, Types.ARRAY, "RFQ_OBJECT_SP_ARRAY");
            ocs.execute();
            // StoredProcedureQuery store = em.createNamedStoredProcedureQuery("NvcTblOaProveedoresH.get_rfq_captura_cotizacion_pro");
            // store.setParameter("p_id_proveedor", params.getIdProveedor());
            // store.setParameter("p_id_idioma", params.getIdioma());
            // store.setParameter("p_fecha_inicio", dateStarStr);
            // store.setParameter("p_fecha_fin", dateEndStr);
            // store.execute();
            // List rfqList = store.getResultList();

            Array arrayUen = ocs.getArray(5);

            Object[] rfqList = (Object[]) arrayUen.getArray();

            for (int ii = 0; ii < rfqList.length; ii++) {

                RfqPojo rfqObj = new RfqPojo();

                Struct rfqsStructure = (Struct) rfqList[ii];

                if (rfqsStructure != null) {

                    Object[] rfqsObjects = rfqsStructure.getAttributes();

                    if (rfqsObjects != null) {

                        rfqObj.setIdRfq((BigDecimal) rfqsObjects[0]);
                        rfqObj.setFechaCreacion((String) rfqsObjects[1]);
                        rfqObj.setInicioVigencia((String) rfqsObjects[2]);
                        rfqObj.setFinVigencia((String) rfqsObjects[3]);
                        rfqObj.setVencido((String) rfqsObjects[4]);
                        rfqObj.setShipTo((String) rfqsObjects[5]);
                        rfqObj.setBillTo((String) rfqsObjects[6]);
                        rfqObj.setIdIncoterm((BigDecimal) rfqsObjects[7]);
                        rfqObj.setIncotermDesc((String) rfqsObjects[8]);
                        rfqObj.setIdUen((BigDecimal) rfqsObjects[9]);
                        rfqObj.setNombreUen((String) rfqsObjects[10]);

                        Array requisiciones = (Array) rfqsObjects[11];
                        rfqObj.setIdCotizacionProveedor((String) rfqsObjects[12]);
                        Object[] requisicionesList = (Object[]) requisiciones.getArray();

                        List<RfqLineaPojo> requisList = new ArrayList<>();

                        for (int r = 0; r < requisicionesList.length; r++) {

                            Struct requiStructure = (Struct) requisicionesList[r];
                            Object[] requisObjects = requiStructure.getAttributes();
                            RfqLineaPojo requiObj = new RfqLineaPojo();
                            requiObj.setIdRequisicion((BigDecimal) requisObjects[0]);
                            requiObj.setIdPartida((BigDecimal) requisObjects[1]);
                            requiObj.setDescripcion((String) requisObjects[2]);
                            requiObj.setCantidad((BigDecimal) requisObjects[3]);
                            requiObj.setUdm((String) requisObjects[4]);
                            requiObj.setNumAttachments((BigDecimal) requisObjects[5]);
                            requiObj.setNombreComprador((String) requisObjects[6]);
                            requiObj.setCountMessages((BigDecimal) requisObjects[7]);
                            requiObj.setComentarios((String) requisObjects[8]);
                            requiObj.setFechaRequerida((String) requisObjects[9]);
                            requiObj.setIdEstatus((BigDecimal) requisObjects[10]);
                            requiObj.setUrgente((String) requisObjects[11]);
                            requisList.add(requiObj);
                        }

                        rfqObj.setRequisiciones(requisList);
                        rfqListResult.add(rfqObj);
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in getCotizacionesProveedor" + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ocs != null) {
                    ocs.close();
                }
            } catch (Exception e) {

                e.printStackTrace();
                System.out.println("Error in getCotizacionesProveedor" + e.getMessage());
            }
        }

        return rfqListResult;

    }

    public List<OaIvaUenPojo> getIvaByUen(Integer idUen) {

        List<OaIvaUenPojo> ivaResult = new ArrayList<>();
        List<String> result;
        String nativeQuery = "SELECT IVA FROM NVC_VM_OA_IVA WHERE ID_UEN = ?1  and (INACTIVE_DATE>sysdate or INACTIVE_DATE is null) ORDER BY IVA";

        result = em.createNativeQuery(nativeQuery)
                .setParameter(1, idUen)
                .getResultList();

        for (String iva : result) {
            OaIvaUenPojo ivaPojo = new OaIvaUenPojo();
            ivaPojo.setId(iva);
            ivaPojo.setIvaName(iva);
            ivaResult.add(ivaPojo);
        }

        return ivaResult;
    }
    
    
    public String getTipoTransporte_n(Integer id) {
    	String tipoTr = "";
        try {
            String nativeQuery = "select descripcion from Nvc_Tbl_Cat_Medio where id_cat_medio = ?1";
            tipoTr = (String) em.createNativeQuery(nativeQuery)
            		.setParameter(1, id)
            		.getSingleResult();
            return "{\"tipoTransporte\":\"" + tipoTr +  "\"}";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------ Error in getTipoTransporte_n PortalProveedor ------");
        }
        return tipoTr;
    }
    
    public String getTerminosTransporte_n(Integer id) {
    	String tipoTr = "";
        try {
            String nativeQuery = "select descripcion from NVC_TBL_TERM_TRANSPORTE where id_term = ?1";
            tipoTr = (String) em.createNativeQuery(nativeQuery)
            		.setParameter(1, id)
            		.getSingleResult();
            return "{\"tipoTransporte\":\"" + tipoTr +  "\"}";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------ Error in getTipoTransporte_n PortalProveedor ------");
        }
        return tipoTr;
    }
    
    
}
