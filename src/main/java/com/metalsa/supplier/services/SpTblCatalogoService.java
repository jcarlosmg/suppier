package com.metalsa.supplier.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metalsa.supplier.controller.TokenApiController;
import com.metalsa.supplier.entity.NvcTblDocsCotizacion;
import com.metalsa.supplier.entity.SpTblCatalogo;
import com.metalsa.supplier.exceptions.ResourceNotFoundException;
import com.metalsa.supplier.pojo.ICustomItemsSelect;
import com.metalsa.supplier.pojo.IUserPojo;
import com.metalsa.supplier.pojo.LoginPojo;
import com.metalsa.supplier.pojo.PoVendorPojo;
import com.metalsa.supplier.pojo.UserPojo;
import com.metalsa.supplier.repository.ISpTblCatalogoRepository;
import com.metalsa.supplier.repository.NvcTblDocsCotizacionRepository;


@Service
public class SpTblCatalogoService {
    
    @Autowired
    private ISpTblCatalogoRepository catalogoRepository;
    
    @Autowired
    private TokenApiController tokenApi;
    
    @Autowired
    private NvcTblDocsCotizacionRepository docsCotizacionRepository;

    public List<SpTblCatalogo> findAll(){
        return catalogoRepository.findAll();
    }

    public SpTblCatalogo findById(Long id) throws ResourceNotFoundException{
        return catalogoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("entity not found for this id :: " + id));
    }

    public List<SpTblCatalogo> findByLocalization(){
        return catalogoRepository.findByLocalization();
    }

    public List<ICustomItemsSelect> getMonedasActivas(){
        return catalogoRepository.getMonedasActivas();
    }

    public List<ICustomItemsSelect> getUnidadMedidaByIdioma(String idioma){
        return catalogoRepository.getUnidadMedidaByIdioma(idioma);
    }

    public SpTblCatalogo save(SpTblCatalogo entity){
         catalogoRepository.save(entity);
         return entity;
    }

    public void delete(Long id){
        catalogoRepository.deleteById(id);
    }
    
    public PoVendorPojo getProveedorByVendorEncoded(String vendorEncoded) {
    	List<PoVendorPojo> listVendors = new ArrayList<>();
    	PoVendorPojo obj = new PoVendorPojo();
    	if(vendorEncoded != null || vendorEncoded != "")
    		listVendors = catalogoRepository.getProveedorByVendorEncoded(vendorEncoded);
    		if(listVendors != null && !listVendors.isEmpty())
    			obj = listVendors.get(0);
    	return obj;
    }
    
    public boolean deleteDocCotizacion(NvcTblDocsCotizacion doc) {
        try {
            
            docsCotizacionRepository.delete(doc);
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------- Error in deleteDocCotizacion -------");
            System.out.println(e.getMessage());
        }
        
        return false;
    }
	
    public void guardarIdCotizacion(String idCotizacion, String idRfq) {
        catalogoRepository.guardarIdCotizacion(idCotizacion, idRfq);
    	
        
    }
    
    public UserPojo getUser(LoginPojo user) {
    	UserPojo userData = new UserPojo();
    	IUserPojo u = catalogoRepository.getUser(user.getUser());
    	if(Objects.nonNull(u)) {
    		if(user.getAttribute2().equals(u.getAttribute2())) {
    			userData.setSegment1(user.getUser());
    			userData.setVendorId(u.getVendorId());
    			userData.setVendorName(u.getVendorName());
    			userData.setStatus(true);
    			userData.setIdioma(u.getIdioma());
    			userData.setToken(tokenApi.getJWTToken2(u.getVendorId(), u.getIdioma(), u.getVendorName()));
    			
    			return userData;
    		}else {
    			userData.setSegment1(user.getUser());
    			userData.setStatus(false);
    		}
    		
    	}
    	return userData;
    }
    
}
