package com.metalsa.supplier.dao;

import java.util.List;

import com.metalsa.supplier.entity.NvcTblDocsCatalogoPojo;
import com.metalsa.supplier.entity.SpTblCatLocalizacion;
import com.metalsa.supplier.entity.SpTblCatalogo;
import com.metalsa.supplier.pojo.UenPojo;

public interface SupplierDAO {

	List<UenPojo> searchByPL(String idProveedor);

	int saveCatalog(SpTblCatalogo request);

	void saveLocation(List<SpTblCatLocalizacion> request);

	boolean saveDocCatalog(List<NvcTblDocsCatalogoPojo> request);

}
