package com.metalsa.supplier.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Array;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.ParameterMode;

/**
 *
 * @author edgar.leal
 */
@Entity
@Table(name = "NVC_TBL_OA_PROVEEDORES_H")
@XmlRootElement
@NamedNativeQueries({
    @NamedNativeQuery(name = "NvcTblOaProveedoresH.findPorUen",
            query = "select * from Nvc_Tbl_Oa_Proveedores_H where id_proveedor in ( select pro.id_proveedor from Nvc_Tbl_Oa_Proveedores_H pro INNER JOIN NVC_TBL_PROV_SITES_H ps on pro.ID_PROVEEDOR = ps.id_proveedor where id_uen = ?1 group by pro.id_proveedor) order by NOMBRE_PROVEEDOR asc",
            resultClass = NvcTblOaProveedoresH.class
    )
    ,
    @NamedNativeQuery(name = "NvcTblOaProveedoresH.findDispDirigidosPorUen",
            query = "SELECT * FROM Nvc_Tbl_Oa_Proveedores_H WHERE id_proveedor IN ( select pro.id_proveedor from Nvc_Tbl_Oa_Proveedores_H pro \n" +
                    "INNER JOIN NVC_TBL_PROV_SITES_H ps on pro.ID_PROVEEDOR = ps.id_proveedor \n" +
                    "LEFT JOIN (SELECT * FROM PROVEEDOR_DIRIGIDO WHERE ACTIVO = 1) pd on pro.id_proveedor = pd.id_proveedor and ps.id_uen = pd.id_uen \n" +
                    "WHERE pro.active = 1 AND ps.id_uen = :idUen AND UPPER(pro.nombre_proveedor) LIKE UPPER(:query) AND pd.id_proveedor IS NULL GROUP BY pro.id_proveedor) \n" +
                    "ORDER BY nombre_proveedor ASC",
            resultClass = NvcTblOaProveedoresH.class
    )
    ,
    @NamedNativeQuery(name = "NvcTblOaProveedoresH.findLikeByUenWithFad",
            query = "SELECT * FROM Nvc_Tbl_Oa_Proveedores_H WHERE id_proveedor IN ( select pro.id_proveedor from Nvc_Tbl_Oa_Proveedores_H pro \n" +
                    "INNER JOIN NVC_TBL_PROV_SITES_H ps on pro.ID_PROVEEDOR = ps.id_proveedor \n" +
                    "LEFT JOIN NVC_TBL_FAD f on f.id_prov = pro.id_proveedor and f.id_site = ps.id_sucursal_proveedor \n" +
                    "WHERE pro.active = 1 AND ps.id_uen IN (:uens) AND UPPER(pro.nombre_proveedor) LIKE UPPER(:query) GROUP BY pro.id_proveedor) \n" +
                    "ORDER BY nombre_proveedor ASC",
            resultClass = NvcTblOaProveedoresH.class
    )
    ,
    @NamedNativeQuery(name = "NvcTblOaProveedoresH.findLikeWithFad",
            query = "SELECT * FROM Nvc_Tbl_Oa_Proveedores_H WHERE id_proveedor IN ( select pro.id_proveedor from Nvc_Tbl_Oa_Proveedores_H pro \n" +
                    "INNER JOIN NVC_TBL_PROV_SITES_H ps on pro.ID_PROVEEDOR = ps.id_proveedor \n" +
                    "LEFT JOIN NVC_TBL_FAD f on f.id_prov = pro.id_proveedor and f.id_site = ps.id_sucursal_proveedor \n" +
                    "WHERE pro.active = 1 AND UPPER(pro.nombre_proveedor) LIKE UPPER(:query) GROUP BY pro.id_proveedor) \n" +
                    "ORDER BY nombre_proveedor ASC",
            resultClass = NvcTblOaProveedoresH.class
    )
    ,    
 @NamedNativeQuery(name = "NvcTblOaProveedoresH.proveedoresPorUenRequisicion",
            query = "select p.* from Nvc_Tbl_Oa_Proveedores_H p\n"
            + "where p.id_proveedor in (\n"
            + "SELECT PR.ID_PROV_INTERNO\n"
            + "            FROM NVC_TBL_REQ_LINEA_PROV LP\n"
            + "            JOIN REQUISICION R ON R.ID_USUARIO = ?1 AND R.ID_UEN = ?2\n"
            + "            JOIN NVC_TBL_RFQ_LINEA RL\n"
            + "                ON  RL.LLAVE_ID = LP.LLAVE_ID\n"
            + "                AND RL.ID_REQUISICION IN(R.ID_REQUISICION) \n"
            + "            JOIN DETALLE_DE_REQUISICION DET\n"
            + "                ON DET.ID_REQUISICION = RL.ID_REQUISICION\n"
            + "                AND DET.ID_PARTIDA = RL.ID_LINEA\n"
            + "                AND DET.ID_ESTATUS = 27\n"
            + "            JOIN NVC_TBL_RFQ RF\n"
            + "                ON RF.ID_RFQ = RL.ID_RFQ \n"
            + "            JOIN NVC_TBL_RFQ_PROV PR\n"
            + "                ON PR.llave_id = LP.ID_RFQ_PROV             \n"
            + "         WHERE LP.ID_ESTATUS = 48\n"
            + "         GROUP BY PR.ID_PROV_INTERNO)\n"
            + "         order by p.NOMBRE_PROVEEDOR asc",
            resultClass = NvcTblOaProveedoresH.class
    )
    ,
@NamedNativeQuery(name = "NvcTblOaProveedoresH.proveedoresPorRequisicion",
            query = "select p.* from Nvc_Tbl_Oa_Proveedores_H p\n"
            + "where p.id_proveedor in (\n"
            + "SELECT PR.ID_PROV_INTERNO\n"
            + "            FROM NVC_TBL_REQ_LINEA_PROV LP\n"
            + "            JOIN REQUISICION R ON R.ID_USUARIO = ?1 \n"
            + "            JOIN NVC_TBL_RFQ_LINEA RL\n"
            + "                ON  RL.LLAVE_ID = LP.LLAVE_ID\n"
            + "                AND RL.ID_REQUISICION IN(R.ID_REQUISICION) \n"
            + "            JOIN DETALLE_DE_REQUISICION DET\n"
            + "                ON DET.ID_REQUISICION = RL.ID_REQUISICION\n"
            + "                AND DET.ID_PARTIDA = RL.ID_LINEA\n"
            + "                AND DET.ID_ESTATUS = 27\n"
            + "            JOIN NVC_TBL_RFQ RF\n"
            + "                ON RF.ID_RFQ = RL.ID_RFQ \n"
            + "            JOIN NVC_TBL_RFQ_PROV PR\n"
            + "                ON PR.llave_id = LP.ID_RFQ_PROV             \n"
            + "         WHERE LP.ID_ESTATUS = 48\n"
            + "         GROUP BY PR.ID_PROV_INTERNO)\n"
            + "         order by p.NOMBRE_PROVEEDOR asc",
            resultClass = NvcTblOaProveedoresH.class
    )
    ,
@NamedNativeQuery(name = "NvcTblOaProveedoresH.findLikeNombreByUen_", query = "SELECT distinct n.* FROM NVC_TBL_OA_PROVEEDORES_H n INNER JOIN \n"
            + "ap_supplier_sites_all site on (n.id_proveedor = site.vendor_id) INNER JOIN \n"
            + "NVC_TBL_PROV_SITES_H h  ON (h.id_proveedor = n.id_proveedor)\n"
            + "WHERE upper (n.NOMBRE_PROVEEDOR) like ?1 and n.EMPLOYEE_ID is null  and n.active=1\n"
            + "and site.AUTO_TAX_CALC_FLAG in ('Y','N') and h.id_uen=?2 and h.active =1", resultClass = NvcTblOaProveedoresH.class)

})
@NamedQueries({
    @NamedQuery(name = "NvcTblOaProveedoresH.findAll", query = "SELECT n FROM NvcTblOaProveedoresH n")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByIdProveedor", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.idProveedor = :idProveedor")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByLastUpdateDate", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.lastUpdateDate = :lastUpdateDate")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByLastUpdatedBy", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.lastUpdatedBy = :lastUpdatedBy")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByNombreProveedor", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.nombreProveedor = :nombreProveedor")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByVendorNameAlt", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.vendorNameAlt = :vendorNameAlt")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByRfc", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.rfc = :rfc")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findBySummaryFlag", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.summaryFlag = :summaryFlag")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByEnabledFlag", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.enabledFlag = :enabledFlag")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findBySegment2", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.segment2 = :segment2")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findBySegment3", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.segment3 = :segment3")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findBySegment4", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.segment4 = :segment4")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findBySegment5", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.segment5 = :segment5")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByLastUpdateLogin", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.lastUpdateLogin = :lastUpdateLogin")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByCreationDate", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.creationDate = :creationDate")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByCreatedBy", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.createdBy = :createdBy")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByEmployeeId", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.employeeId = :employeeId")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByCustomerNum", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.customerNum = :customerNum")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findBySetOfBooksId", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.setOfBooksId = :setOfBooksId")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByStartDateActive", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.startDateActive = :startDateActive")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByEndDateActive", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.endDateActive = :endDateActive")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByInspectionRequiredFlag", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.inspectionRequiredFlag = :inspectionRequiredFlag")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByReceiptRequiredFlag", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.receiptRequiredFlag = :receiptRequiredFlag")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByAttributeCategory", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.attributeCategory = :attributeCategory")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByAttribute1", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.attribute1 = :attribute1")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByAttribute2", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.attribute2 = :attribute2")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByAttribute3", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.attribute3 = :attribute3")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByAttribute4", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.attribute4 = :attribute4")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByAttribute5", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.attribute5 = :attribute5")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByGlobalAttribute1", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.globalAttribute1 = :globalAttribute1")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByGlobalAttribute2", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.globalAttribute2 = :globalAttribute2")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByGlobalAttribute3", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.globalAttribute3 = :globalAttribute3")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByGlobalAttribute4", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.globalAttribute4 = :globalAttribute4")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByGlobalAttribute5", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.globalAttribute5 = :globalAttribute5")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByMatchOption", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.matchOption = :matchOption")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByActive", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.active = :active")
    , @NamedQuery(name = "NvcTblOaProveedoresH.findByVendorTypeLookupCode", query = "SELECT n FROM NvcTblOaProveedoresH n WHERE n.vendorTypeLookupCode = :vendorTypeLookupCode")})
    @NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "NvcTblOaProveedoresH.get_rfq_captura_cotizacion_pro",
                procedureName = "get_rfq_captura_cotizacion_pro",
                parameters = {
                    @StoredProcedureParameter(name = "p_id_proveedor", type = Integer.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_id_idioma", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_fecha_inicio", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "p_fecha_fin", type = String.class, mode = ParameterMode.IN),
                    @StoredProcedureParameter(name = "out_rfq_list", type = Array.class, mode = ParameterMode.REF_CURSOR)
                }
        )})
public class NvcTblOaProveedoresH implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROVEEDOR")
    private BigDecimal idProveedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_UPDATED_BY")
    private BigInteger lastUpdatedBy;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 720)
    @Column(name = "NOMBRE_PROVEEDOR")
    private String nombreProveedor;
    @Size(max = 960)
    @Column(name = "VENDOR_NAME_ALT")
    private String vendorNameAlt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 90)
    @Column(name = "RFC")
    private String rfc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "SUMMARY_FLAG")
    private String summaryFlag;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "ENABLED_FLAG")
    private String enabledFlag;
    @Size(max = 90)
    @Column(name = "SEGMENT2")
    private String segment2;
    @Size(max = 90)
    @Column(name = "SEGMENT3")
    private String segment3;
    @Size(max = 90)
    @Column(name = "SEGMENT4")
    private String segment4;
    @Size(max = 90)
    @Column(name = "SEGMENT5")
    private String segment5;
    @Column(name = "LAST_UPDATE_LOGIN")
    private BigInteger lastUpdateLogin;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private BigInteger createdBy;
    @Column(name = "EMPLOYEE_ID")
    private BigInteger employeeId;
    @Size(max = 75)
    @Column(name = "CUSTOMER_NUM")
    private String customerNum;
    @Column(name = "SET_OF_BOOKS_ID")
    private BigInteger setOfBooksId;
    @Column(name = "START_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateActive;
    @Column(name = "END_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateActive;
    @Size(max = 3)
    @Column(name = "INSPECTION_REQUIRED_FLAG")
    private String inspectionRequiredFlag;
    @Size(max = 3)
    @Column(name = "RECEIPT_REQUIRED_FLAG")
    private String receiptRequiredFlag;
    @Size(max = 90)
    @Column(name = "ATTRIBUTE_CATEGORY")
    private String attributeCategory;
    @Size(max = 450)
    @Column(name = "ATTRIBUTE1")
    private String attribute1;
    @Size(max = 450)
    @Column(name = "ATTRIBUTE2")
    private String attribute2;
    @Size(max = 450)
    @Column(name = "ATTRIBUTE3")
    private String attribute3;
    @Size(max = 450)
    @Column(name = "ATTRIBUTE4")
    private String attribute4;
    @Size(max = 450)
    @Column(name = "ATTRIBUTE5")
    private String attribute5;
    @Size(max = 450)
    @Column(name = "GLOBAL_ATTRIBUTE1")
    private String globalAttribute1;
    @Size(max = 450)
    @Column(name = "GLOBAL_ATTRIBUTE2")
    private String globalAttribute2;
    @Size(max = 450)
    @Column(name = "GLOBAL_ATTRIBUTE3")
    private String globalAttribute3;
    @Size(max = 450)
    @Column(name = "GLOBAL_ATTRIBUTE4")
    private String globalAttribute4;
    @Size(max = 450)
    @Column(name = "GLOBAL_ATTRIBUTE5")
    private String globalAttribute5;
    @Size(max = 75)
    @Column(name = "MATCH_OPTION")
    private String matchOption;
    @Column(name = "ACTIVE")
    private BigInteger active;
    @Size(max = 30)
    @Column(name = "VENDOR_TYPE_LOOKUP_CODE")
    private String vendorTypeLookupCode;

    public NvcTblOaProveedoresH() {
    }

    public NvcTblOaProveedoresH(BigDecimal idProveedor) {
        this.idProveedor = idProveedor;
    }

    public NvcTblOaProveedoresH(BigDecimal idProveedor, Date lastUpdateDate, BigInteger lastUpdatedBy, String nombreProveedor, String rfc, String summaryFlag, String enabledFlag) {
        this.idProveedor = idProveedor;
        this.lastUpdateDate = new Date(lastUpdateDate.getTime());
        this.lastUpdatedBy = lastUpdatedBy;
        this.nombreProveedor = nombreProveedor;
        this.rfc = rfc;
        this.summaryFlag = summaryFlag;
        this.enabledFlag = enabledFlag;
    }

    public BigDecimal getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(BigDecimal idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Date getLastUpdateDate() {

        if (lastUpdateDate != null) {
            return new Date(lastUpdateDate.getTime());
        } else {
            Date d = new Date();
            return new Date(d.getTime());
        }

    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        if (lastUpdateDate != null) {
            this.lastUpdateDate = new Date(lastUpdateDate.getTime());
        }
    }

    public BigInteger getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(BigInteger lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getVendorNameAlt() {
        return vendorNameAlt;
    }

    public void setVendorNameAlt(String vendorNameAlt) {
        this.vendorNameAlt = vendorNameAlt;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getSummaryFlag() {
        return summaryFlag;
    }

    public void setSummaryFlag(String summaryFlag) {
        this.summaryFlag = summaryFlag;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getSegment2() {
        return segment2;
    }

    public void setSegment2(String segment2) {
        this.segment2 = segment2;
    }

    public String getSegment3() {
        return segment3;
    }

    public void setSegment3(String segment3) {
        this.segment3 = segment3;
    }

    public String getSegment4() {
        return segment4;
    }

    public void setSegment4(String segment4) {
        this.segment4 = segment4;
    }

    public String getSegment5() {
        return segment5;
    }

    public void setSegment5(String segment5) {
        this.segment5 = segment5;
    }

    public BigInteger getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(BigInteger lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public Date getCreationDate() {
        if (creationDate != null) {
            return new Date(creationDate.getTime());
        } else {
            Date d = new Date();
            return new Date(d.getTime());
        }
    }

    public void setCreationDate(Date creationDate) {
        if (creationDate != null) {
            this.creationDate = new Date(creationDate.getTime());
        }
    }

    public BigInteger getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BigInteger createdBy) {
        this.createdBy = createdBy;
    }

    public BigInteger getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(BigInteger employeeId) {
        this.employeeId = employeeId;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public BigInteger getSetOfBooksId() {
        return setOfBooksId;
    }

    public void setSetOfBooksId(BigInteger setOfBooksId) {
        this.setOfBooksId = setOfBooksId;
    }

    public Date getStartDateActive() {
        if (startDateActive != null) {
            return new Date(startDateActive.getTime());
        } else {
            Date d = new Date();
            return new Date(d.getTime());
        }
    }

    public void setStartDateActive(Date startDateActive) {
        if (startDateActive != null) {
            this.startDateActive = new Date(startDateActive.getTime());
        }
    }

    public Date getEndDateActive() {
        if (endDateActive != null) {
            return new Date(endDateActive.getTime());
        } else {
            Date d = new Date();
            return new Date(d.getTime());
        }

    }

    public void setEndDateActive(Date endDateActive) {
        if (endDateActive != null) {
            this.endDateActive = new Date(endDateActive.getTime());
        }
    }

    public String getInspectionRequiredFlag() {
        return inspectionRequiredFlag;
    }

    public void setInspectionRequiredFlag(String inspectionRequiredFlag) {
        this.inspectionRequiredFlag = inspectionRequiredFlag;
    }

    public String getReceiptRequiredFlag() {
        return receiptRequiredFlag;
    }

    public void setReceiptRequiredFlag(String receiptRequiredFlag) {
        this.receiptRequiredFlag = receiptRequiredFlag;
    }

    public String getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory(String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getGlobalAttribute1() {
        return globalAttribute1;
    }

    public void setGlobalAttribute1(String globalAttribute1) {
        this.globalAttribute1 = globalAttribute1;
    }

    public String getGlobalAttribute2() {
        return globalAttribute2;
    }

    public void setGlobalAttribute2(String globalAttribute2) {
        this.globalAttribute2 = globalAttribute2;
    }

    public String getGlobalAttribute3() {
        return globalAttribute3;
    }

    public void setGlobalAttribute3(String globalAttribute3) {
        this.globalAttribute3 = globalAttribute3;
    }

    public String getGlobalAttribute4() {
        return globalAttribute4;
    }

    public void setGlobalAttribute4(String globalAttribute4) {
        this.globalAttribute4 = globalAttribute4;
    }

    public String getGlobalAttribute5() {
        return globalAttribute5;
    }

    public void setGlobalAttribute5(String globalAttribute5) {
        this.globalAttribute5 = globalAttribute5;
    }

    public String getMatchOption() {
        return matchOption;
    }

    public void setMatchOption(String matchOption) {
        this.matchOption = matchOption;
    }

    public BigInteger getActive() {
        return active;
    }

    public void setActive(BigInteger active) {
        this.active = active;
    }

    public String getVendorTypeLookupCode() {
        return vendorTypeLookupCode;
    }

    public void setVendorTypeLookupCode(String vendorTypeLookupCode) {
        this.vendorTypeLookupCode = vendorTypeLookupCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblOaProveedoresH)) {
            return false;
        }
        NvcTblOaProveedoresH other = (NvcTblOaProveedoresH) object;
        return this.idProveedor.equals(other.idProveedor);

    }

    @Override
    public String toString() {
        return "com.metalsa.core.model.NvcTblOaProveedoresH[ idProveedor=" + idProveedor + " ]";
    }

}
