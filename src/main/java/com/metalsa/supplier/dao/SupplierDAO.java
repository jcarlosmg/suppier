package com.metalsa.supplier.dao;

import java.util.List;

import com.metalsa.supplier.entity.Bitacora;
import com.metalsa.supplier.entity.NvcTblDocsCatalogoPojo;
import com.metalsa.supplier.entity.NvcTblGastoAdicional;
import com.metalsa.supplier.entity.NvcTblReqLineaProv;
import com.metalsa.supplier.entity.SpTblCatLocalizacion;
import com.metalsa.supplier.entity.SpTblCatalogo;
import com.metalsa.supplier.pojo.LoginPojo2;
import com.metalsa.supplier.pojo.AdjuntoPojo;
import com.metalsa.supplier.pojo.NvcTblDocsCotizacionPojo;
import com.metalsa.supplier.pojo.UenPojo;
import com.metalsa.supplier.pojo.UserPojo2;

public interface SupplierDAO {

	List<UenPojo> searchByPL(String idProveedor);

	int saveCatalog(SpTblCatalogo request);

	void saveLocation(List<SpTblCatLocalizacion> request);

	boolean saveAdjuntos(List<AdjuntoPojo> adjuntos, Long idItem);

	boolean saveDocCatalog(List<NvcTblDocsCatalogoPojo> request);

	boolean saveDocCotization(List<NvcTblDocsCotizacionPojo> request);

	boolean senQuaotation(List<NvcTblReqLineaProv> request);

	boolean saveReqLieaProv(List<NvcTblReqLineaProv> request);

	boolean deleteGasto(NvcTblGastoAdicional request);

	boolean saveGastoAdicional(List<NvcTblGastoAdicional> gasto);

	Iterable<NvcTblGastoAdicional> getByIdReqLineaProv(Integer idReqLineaProv);
	
	UserPojo2 validaProveedor(LoginPojo2 request);

	List<AdjuntoPojo> getFilesByIdCat(Integer idCatalogo, String type);
	
	void removeFileByItem(AdjuntoPojo adjunto, Integer idItem);

	List<Bitacora> getBitacora(Long idCatalogo);

	boolean addNewRecord(Bitacora bitacora);

}
