package com.metalsa.supplier.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.StoredProcedureQuery;

import com.metalsa.supplier.dao.SupplierDAO;
import com.metalsa.supplier.entity.NvcTblDocsCatalogoPojo;
import com.metalsa.supplier.entity.SpTblCatLocalizacion;
import com.metalsa.supplier.entity.SpTblCatalogo;
import com.metalsa.supplier.exceptions.ResourceNotFoundException;
import com.metalsa.supplier.pojo.ICustomItemsSelect;
import com.metalsa.supplier.pojo.PoVendorPojo;
import com.metalsa.supplier.pojo.UenPojo;
import com.metalsa.supplier.repository.ISpTblCatalogoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpTblCatalogoService {
    
    @Autowired
    private ISpTblCatalogoRepository catalogoRepository;
    
    @Autowired
    private SupplierDAO service;

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

    public List<UenPojo> uensBySupplier(String idProveedor) {
        List<UenPojo> result;
        result = service.searchByPL(idProveedor);
        return result;
    }

	public Integer saveCatalogo(SpTblCatalogo request) {
		int resp = 0;
        try {
        	resp = service.saveCatalog(request);
        } catch (Exception e) {
            //log.debug(e.getMessage(), e);
            resp = 0;
        }
        return resp;
	}

	public void saveLocation(List<SpTblCatLocalizacion> request) {
		service.saveLocation(request);
	}

	public boolean saveDocCatalogo(List<NvcTblDocsCatalogoPojo> request) {
		return service.saveDocCatalog(request);
	}

}
