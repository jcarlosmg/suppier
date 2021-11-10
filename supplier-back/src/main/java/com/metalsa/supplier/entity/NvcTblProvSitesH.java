package com.metalsa.supplier.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edgar.leal
 */
@Entity
@Table(name = "NVC_TBL_PROV_SITES_H")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblProvSitesH.findAll", query = "SELECT n FROM NvcTblProvSitesH n")
    , @NamedQuery(name = "NvcTblProvSitesH.findByIdProveedor", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.nvcTblProvSitesHPK.idProveedor = :idProveedor")
    , @NamedQuery(name = "NvcTblProvSitesH.findByIdSucursalProveedor", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.nvcTblProvSitesHPK.idSucursalProveedor = :idSucursalProveedor")
    , @NamedQuery(name = "NvcTblProvSitesH.findByLastUpdateDate", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.lastUpdateDate = :lastUpdateDate")
    , @NamedQuery(name = "NvcTblProvSitesH.findByLastUpdatedBy", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.lastUpdatedBy = :lastUpdatedBy")
    , @NamedQuery(name = "NvcTblProvSitesH.findByVendorSiteCode", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.vendorSiteCode = :vendorSiteCode")
    , @NamedQuery(name = "NvcTblProvSitesH.findByVendorSiteCodeAlt", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.vendorSiteCodeAlt = :vendorSiteCodeAlt")
    , @NamedQuery(name = "NvcTblProvSitesH.findByLastUpdateLogin", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.lastUpdateLogin = :lastUpdateLogin")
    , @NamedQuery(name = "NvcTblProvSitesH.findByCreationDate", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.creationDate = :creationDate")
    , @NamedQuery(name = "NvcTblProvSitesH.findByCreatedBy", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.createdBy = :createdBy")
    , @NamedQuery(name = "NvcTblProvSitesH.findByAddressLine1", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.addressLine1 = :addressLine1")
    , @NamedQuery(name = "NvcTblProvSitesH.findByAddressLinesAlt", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.addressLinesAlt = :addressLinesAlt")
    , @NamedQuery(name = "NvcTblProvSitesH.findByAddressLine2", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.addressLine2 = :addressLine2")
    , @NamedQuery(name = "NvcTblProvSitesH.findByAddressLine3", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.addressLine3 = :addressLine3")
    , @NamedQuery(name = "NvcTblProvSitesH.findByAddressLine4", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.addressLine4 = :addressLine4")
    , @NamedQuery(name = "NvcTblProvSitesH.findByCity", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.city = :city")
    , @NamedQuery(name = "NvcTblProvSitesH.findByState", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.state = :state")
    , @NamedQuery(name = "NvcTblProvSitesH.findByZip", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.zip = :zip")
    , @NamedQuery(name = "NvcTblProvSitesH.findByProvince", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.province = :province")
    , @NamedQuery(name = "NvcTblProvSitesH.findByCountry", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.country = :country")
    , @NamedQuery(name = "NvcTblProvSitesH.findByAreaCode", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.areaCode = :areaCode")
    , @NamedQuery(name = "NvcTblProvSitesH.findByPhone", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.phone = :phone")
    , @NamedQuery(name = "NvcTblProvSitesH.findByCustomerNum", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.customerNum = :customerNum")
    , @NamedQuery(name = "NvcTblProvSitesH.findByInactiveDate", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.inactiveDate = :inactiveDate")
    , @NamedQuery(name = "NvcTblProvSitesH.findByFax", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.fax = :fax")
    , @NamedQuery(name = "NvcTblProvSitesH.findByFaxAreaCode", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.faxAreaCode = :faxAreaCode")
    , @NamedQuery(name = "NvcTblProvSitesH.findByAttributeCategory", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.attributeCategory = :attributeCategory")
    , @NamedQuery(name = "NvcTblProvSitesH.findByAttribute1", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.attribute1 = :attribute1")
    , @NamedQuery(name = "NvcTblProvSitesH.findByTerminos", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.terminos = :terminos")
    , @NamedQuery(name = "NvcTblProvSitesH.findByBanderaImpresion", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.banderaImpresion = :banderaImpresion")
    , @NamedQuery(name = "NvcTblProvSitesH.findByIntercia", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.intercia = :intercia")
    , @NamedQuery(name = "NvcTblProvSitesH.findByIdUen", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.idUen = :idUen")
    , @NamedQuery(name = "NvcTblProvSitesH.findByCounty", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.county = :county")
    , @NamedQuery(name = "NvcTblProvSitesH.findByAddressStyle", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.addressStyle = :addressStyle")
    , @NamedQuery(name = "NvcTblProvSitesH.findByLanguage", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.language = :language")
    , @NamedQuery(name = "NvcTblProvSitesH.findByEmailAddress", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.emailAddress = :emailAddress")
    , @NamedQuery(name = "NvcTblProvSitesH.findByMatchOption", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.matchOption = :matchOption")
    , @NamedQuery(name = "NvcTblProvSitesH.findBySegment1", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.segment1 = :segment1")
    , @NamedQuery(name = "NvcTblProvSitesH.findByVendorName", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.vendorName = :vendorName")
    , @NamedQuery(name = "NvcTblProvSitesH.findByEnabledFlag", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.enabledFlag = :enabledFlag")
    , @NamedQuery(name = "NvcTblProvSitesH.findByStartDateActive", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.startDateActive = :startDateActive")
    , @NamedQuery(name = "NvcTblProvSitesH.findByEndDateActive", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.endDateActive = :endDateActive")
    , @NamedQuery(name = "NvcTblProvSitesH.findByOrgId", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.orgId = :orgId")
    , @NamedQuery(name = "NvcTblProvSitesH.findByAttribute2", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.attribute2 = :attribute2")
    , @NamedQuery(name = "NvcTblProvSitesH.findByAttribute4", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.attribute4 = :attribute4")
    , @NamedQuery(name = "NvcTblProvSitesH.findByAttribute3", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.attribute3 = :attribute3")
    , @NamedQuery(name = "NvcTblProvSitesH.findByActive", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.active = :active")
    , @NamedQuery(name = "NvcTblProvSitesH.findByTermsId", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.termsId = :termsId")
    , @NamedQuery(name = "NvcTblProvSitesH.findByAttribute6", query = "SELECT n FROM NvcTblProvSitesH n WHERE n.attribute6 = :attribute6")})
public class NvcTblProvSitesH implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NvcTblProvSitesHPK nvcTblProvSitesHPK;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    @Size(max = 45)
    @Column(name = "VENDOR_SITE_CODE")
    private String vendorSiteCode;
    @Size(max = 960)
    @Column(name = "VENDOR_SITE_CODE_ALT")
    private String vendorSiteCodeAlt;
    @Column(name = "LAST_UPDATE_LOGIN")
    private Long lastUpdateLogin;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "CREATED_BY")
    private Long createdBy;
    @Size(max = 720)
    @Column(name = "ADDRESS_LINE1")
    private String addressLine1;
    @Size(max = 1680)
    @Column(name = "ADDRESS_LINES_ALT")
    private String addressLinesAlt;
    @Size(max = 720)
    @Column(name = "ADDRESS_LINE2")
    private String addressLine2;
    @Size(max = 720)
    @Column(name = "ADDRESS_LINE3")
    private String addressLine3;
    @Size(max = 720)
    @Column(name = "ADDRESS_LINE4")
    private String addressLine4;
    @Size(max = 75)
    @Column(name = "CITY")
    private String city;
    @Size(max = 450)
    @Column(name = "STATE")
    private String state;
    @Size(max = 60)
    @Column(name = "ZIP")
    private String zip;
    @Size(max = 450)
    @Column(name = "PROVINCE")
    private String province;
    @Size(max = 75)
    @Column(name = "COUNTRY")
    private String country;
    @Size(max = 30)
    @Column(name = "AREA_CODE")
    private String areaCode;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "PHONE")
    private String phone;
    @Size(max = 75)
    @Column(name = "CUSTOMER_NUM")
    private String customerNum;
    @Column(name = "INACTIVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inactiveDate;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "FAX")
    private String fax;
    @Size(max = 30)
    @Column(name = "FAX_AREA_CODE")
    private String faxAreaCode;
    @Size(max = 90)
    @Column(name = "ATTRIBUTE_CATEGORY")
    private String attributeCategory;
    @Size(max = 450)
    @Column(name = "ATTRIBUTE1")
    private String attribute1;
    @Size(max = 450)
    @Column(name = "TERMINOS")
    private String terminos;
    @Size(max = 450)
    @Column(name = "BANDERA_IMPRESION")
    private String banderaImpresion;
    @Size(max = 450)
    @Column(name = "INTERCIA")
    private String intercia;
    @Column(name = "ID_UEN")
    private BigInteger idUen;
    @Size(max = 450)
    @Column(name = "COUNTY")
    private String county;
    @Size(max = 90)
    @Column(name = "ADDRESS_STYLE")
    private String addressStyle;
    @Size(max = 90)
    @Column(name = "LANGUAGE")
    private String language;
    @Size(max = 4000)
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Size(max = 75)
    @Column(name = "MATCH_OPTION")
    private String matchOption;
    @Size(max = 30)
    @Column(name = "SEGMENT1")
    private String segment1;
    @Size(max = 240)
    @Column(name = "VENDOR_NAME")
    private String vendorName;
    @Size(max = 1)
    @Column(name = "ENABLED_FLAG")
    private String enabledFlag;
    @Column(name = "START_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateActive;
    @Column(name = "END_DATE_ACTIVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateActive;
    @Column(name = "ORG_ID")
    private BigInteger orgId;
    @Size(max = 150)
    @Column(name = "ATTRIBUTE2")
    private String attribute2;
    @Size(max = 150)
    @Column(name = "ATTRIBUTE4")
    private String attribute4;
    @Size(max = 150)
    @Column(name = "ATTRIBUTE3")
    private String attribute3;
    @Column(name = "ACTIVE")
    private BigInteger active;
    @Column(name = "TERMS_ID")
    private BigInteger termsId;
    @Size(max = 220)
    @Column(name = "ATTRIBUTE6")
    private String attribute6;

    public NvcTblProvSitesH() {
    }

    public NvcTblProvSitesH(NvcTblProvSitesHPK nvcTblProvSitesHPK) {
        this.nvcTblProvSitesHPK = nvcTblProvSitesHPK;
    }

    public NvcTblProvSitesH(Long idProveedor, Long idSucursalProveedor) {
        this.nvcTblProvSitesHPK = new NvcTblProvSitesHPK(idProveedor, idSucursalProveedor);
    }

    public NvcTblProvSitesHPK getNvcTblProvSitesHPK() {
        return nvcTblProvSitesHPK;
    }

    public void setNvcTblProvSitesHPK(NvcTblProvSitesHPK nvcTblProvSitesHPK) {
        this.nvcTblProvSitesHPK = nvcTblProvSitesHPK;
    }

    public Date getLastUpdateDate() {
        return new Date (lastUpdateDate.getTime());
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate =new Date (lastUpdateDate.getTime());
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getVendorSiteCode() {
        return vendorSiteCode;
    }

    public void setVendorSiteCode(String vendorSiteCode) {
        this.vendorSiteCode = vendorSiteCode;
    }

    public String getVendorSiteCodeAlt() {
        return vendorSiteCodeAlt;
    }

    public void setVendorSiteCodeAlt(String vendorSiteCodeAlt) {
        this.vendorSiteCodeAlt = vendorSiteCodeAlt;
    }

    public Long getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Long lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public Date getCreationDate() {
        return new Date (creationDate.getTime());
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = new Date (creationDate.getTime());
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLinesAlt() {
        return addressLinesAlt;
    }

    public void setAddressLinesAlt(String addressLinesAlt) {
        this.addressLinesAlt = addressLinesAlt;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public Date getInactiveDate() {
        return new Date (inactiveDate.getTime());
    }

    public void setInactiveDate(Date inactiveDate) {
        this.inactiveDate =new Date (inactiveDate.getTime());
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFaxAreaCode() {
        return faxAreaCode;
    }

    public void setFaxAreaCode(String faxAreaCode) {
        this.faxAreaCode = faxAreaCode;
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

    public String getTerminos() {
        return terminos;
    }

    public void setTerminos(String terminos) {
        this.terminos = terminos;
    }

    public String getBanderaImpresion() {
        return banderaImpresion;
    }

    public void setBanderaImpresion(String banderaImpresion) {
        this.banderaImpresion = banderaImpresion;
    }

    public String getIntercia() {
        return intercia;
    }

    public void setIntercia(String intercia) {
        this.intercia = intercia;
    }

    public BigInteger getIdUen() {
        return idUen;
    }

    public void setIdUen(BigInteger idUen) {
        this.idUen = idUen;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddressStyle() {
        return addressStyle;
    }

    public void setAddressStyle(String addressStyle) {
        this.addressStyle = addressStyle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMatchOption() {
        return matchOption;
    }

    public void setMatchOption(String matchOption) {
        this.matchOption = matchOption;
    }

    public String getSegment1() {
        return segment1;
    }

    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public Date getStartDateActive() {
        return new Date (startDateActive.getTime());
    }

    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = new Date (startDateActive.getTime());
    }

    public Date getEndDateActive() {
        return new Date (endDateActive.getTime());
    }

    public void setEndDateActive(Date endDateActive) {
        this.endDateActive =new Date (endDateActive.getTime());
    }

    public BigInteger getOrgId() {
        return orgId;
    }

    public void setOrgId(BigInteger orgId) {
        this.orgId = orgId;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public BigInteger getActive() {
        return active;
    }

    public void setActive(BigInteger active) {
        this.active = active;
    }

    public BigInteger getTermsId() {
        return termsId;
    }

    public void setTermsId(BigInteger termsId) {
        this.termsId = termsId;
    }

    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nvcTblProvSitesHPK != null ? nvcTblProvSitesHPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblProvSitesH)) {
            return false;
        }
        NvcTblProvSitesH other = (NvcTblProvSitesH) object;
        return this.nvcTblProvSitesHPK.equals(other.nvcTblProvSitesHPK);
           
    }

    @Override
    public String toString() {
        return "com.metalsa.core.model.NvcTblProvSitesH[ nvcTblProvSitesHPK=" + nvcTblProvSitesHPK + " ]";
    }
    
}

