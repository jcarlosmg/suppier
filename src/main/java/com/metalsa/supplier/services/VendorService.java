package com.metalsa.supplier.services;

import java.sql.Struct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Struct;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.metalsa.supplier.pojo.IUserPojo;
import com.metalsa.supplier.pojo.PoVendorPojo;
import com.metalsa.supplier.pojo.RfqLineaPojo;
import com.metalsa.supplier.pojo.RfqPojo;
import com.metalsa.supplier.pojo.RfqProveedorRequest;
import com.metalsa.supplier.pojo.UserPojo;
import com.metalsa.supplier.repository.ProveedoresRepository;

import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
import oracle.jdbc.OracleCallableStatement;

@Log4j
@Service
public class VendorService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ProveedoresRepository proveedoresRepository;

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
       
    public PoVendorPojo getProveedorByVendorLogin(String user, String pass) {
        PoVendorPojo poVendorPojo = new PoVendorPojo();
    	IUserPojo u =proveedoresRepository.getUser(user, pass);
        if (Objects.nonNull(u)) {            
            log.debug("message " + Integer.valueOf(u.getVendorId()));
            poVendorPojo.setVendorId(Integer.valueOf(u.getVendorId()));
            poVendorPojo.setVendorName(u.getVendorName());
            poVendorPojo.setIdioma(u.getIdioma());
        }else {poVendorPojo = null;}
        
        return poVendorPojo;
    }
}
