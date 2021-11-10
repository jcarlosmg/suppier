package com.metalsa.supplier.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.metalsa.supplier.dao.SupplierDAO;
import com.metalsa.supplier.entity.NvcTblDocsCatalogoPojo;
import com.metalsa.supplier.entity.SpTblCatLocalizacion;
import com.metalsa.supplier.entity.SpTblCatalogo;
import com.metalsa.supplier.entity.SpTblCatalogoItemDoc;
import com.metalsa.supplier.pojo.AdjuntoPojo;
import com.metalsa.supplier.pojo.ContactsPojo;
import com.metalsa.supplier.pojo.LocationUen;
import com.metalsa.supplier.pojo.UenPojo;
import com.metalsa.supplier.utilities.UploadFile;

@Repository
public class SupplierDaoImpl implements SupplierDAO {
	
	@Value("${ftpPT.host}")
    private String host;
    
    @Value("${ftpPT.port}")
    private Integer port;
    
    @Value("${ftpPT.user}")
    private String user;
    
    @Value("${ftpPT.password}")
    private String pass;

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<UenPojo> searchByPL(String idProveedor) {
		List<UenPojo> result;
		try {
			StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Catalogos.UensBySupplier");
			store.setParameter("p_id_proveedor", idProveedor);
			store.execute();
			result = store.getResultList();

			// locations
			for (UenPojo uen : result) {
				uen.setLocations(this.locationsByUen(Integer.toString(uen.getOrganization_id())));
				uen.setContacts(this.contactsBySiteSupplier(idProveedor, Integer.toString(uen.getOrganization_id())));
			}

		} catch (Exception e) {
			//log.debug(e.getMessage(), e);
			result = new ArrayList();
		}
		return result;
	}

	private List<ContactsPojo> contactsBySiteSupplier(String idProveedor, String id_uen) {
		List<ContactsPojo> result;

		try {
			StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Catalogos.ContactBySiteSupplierUen");
			// store.setParameter("p_ids_sites_array", "15053,11582");
			store.setParameter("p_id_proveedor", idProveedor);
			store.setParameter("p_id_uen", id_uen);
			store.execute();
			result = store.getResultList();
		} catch (Exception e) {
			// log.debug(e.getMessage(), e );
			result = new ArrayList();
		}
		return result;
	}

	private List<LocationUen> locationsByUen(String id_uen) {
		List<LocationUen> result;

		try {
			StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Catalogos.LocationsByUen");
			// store.setParameter("p_ids_sites_array", "15053,11582");
			store.setParameter("p_id_uen", id_uen);
			store.execute();
			result = store.getResultList();
		} catch (Exception e) {
			//log.debug(e.getMessage(), e);
			result = new ArrayList();
		}
		return result;
	}

	@Override
	public int saveCatalog(SpTblCatalogo request) {
		String idCat = null;
		 StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Catalogos.SaveCatalogo");
         store.setParameter("p_id_catalogo", request.getIdCatalogo());
         store.setParameter("p_id_proveedor", request.getIdProveedor());
         store.setParameter("p_nombre_catalogo", request.getNombreCatalogo());
         store.setParameter("p_fecha_inicio_vigencia", request.getFechaInicioVigencia());
         store.setParameter("p_fecha_fin_vigencia", request.getFechaFinVigencia());
         store.setParameter("p_publicado", request.getPublicado());
         store.setParameter("p_fecha_creacion", request.getFechaCreacion());
         store.setParameter("p_fecha_actualizacion", request.getFechaActualizacion());
         store.setParameter("p_usuario_creacion", request.getUsuarioCreacion());
         store.setParameter("p_usuario_actualizacion", request.getUsuarioActualizacion());
         store.setParameter("p_activo", request.getActivo());
         store.setParameter("p_tipo_aviso_terminacion", request.getTipoAvisoTerminacion());
         store.setParameter("p_id_prov_punchout", request.getIdProvPunchout());
         store.execute();
         idCat = (String) store.getOutputParameterValue("out_value");
         String mes = (String) store.getOutputParameterValue("out_message");
		return Integer.parseInt(idCat);
	}

	@Override
	public void saveLocation(List<SpTblCatLocalizacion> request) {
		Session currentSession = em.unwrap(Session.class);
		currentSession.save(request);
	}

	@Override
	public boolean saveDocCatalog(List<NvcTblDocsCatalogoPojo> catalogos) {
		boolean doSuccess = false;
		 try {
	            catalogos.forEach((catalogo) -> {
	                //cotizacion.getAdjuntos().forEach((adjunto) -> {
	                for (AdjuntoPojo adjunto : catalogo.getAdjuntos()){
	                    SpTblCatalogoItemDoc modeloCatItem = new SpTblCatalogoItemDoc();
	                    SpTblCatalogoItemDoc modeloCatItemTem = new SpTblCatalogoItemDoc();
	                    modeloCatItem.setIdItem(Long.valueOf(catalogo.getIdCatalogo().intValue()));
	                    modeloCatItem.setIdTipoDocumento(Long.valueOf(1));
	                    modeloCatItem.setNombreArchivo(adjunto.getNombreArchivo());
	                    modeloCatItem.setNombreArchivoFtp(UploadFile.FTP_PATH_COT_DCOM);
	                    modeloCatItem.setExtensionArchivo(adjunto.getMime());
	                    modeloCatItem.setTamanioArchivo((double)adjunto.getPeso());
	                    modeloCatItem.setUrlFtp(UploadFile.FTP_PATH_CAT_DCOM + catalogo.getIdCatalogo() + "/" + modeloCatItem.getNombreArchivo());
	                    modeloCatItem.setFechaCreacion(new Date());
	                    modeloCatItem.setUsuarioCreacion("usuarioC");
	                    modeloCatItem.setUsuarioActualizacion("usuarioUp");
	                    
	                    persistDocumento(modeloCatItem, adjunto);
	                    //modeloCatItemTem = docsCatalogoRepository.getByIdCatalogoItemDoc(modeloCatItem.getIdCatalogoItemDoc());
	                    InputStream fis = new ByteArrayInputStream(adjunto.getFile());
	                    //modeloCatItemTem.setUrlFtp(UploadFile.FTP_PATH_CAT_DCOM + catalogo.getIdCatalogo() + "/" + modeloCatItemTem.getNombreArchivo());
	                    //System.out.println("");
	                    //docsCotizacionRepository.save(modeloCotTem);
	                    UploadFile.upload(fis, UploadFile.FTP_PATH_CAT_DCOM + catalogo.getIdCatalogo() + "/", modeloCatItem.getNombreArchivo(), host, port, user, pass);
	                }
	                //listaCot.add(modeloCot);
	            });

	            //docsCotizacionRepository.save(listaCot);
	            doSuccess = true;
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("------- Error in saveDocCotizacion -------");
	            System.out.println(e.getMessage());
	            doSuccess = false;
	        }
	        
	        return doSuccess;
	}
	
	public void persistDocumento(SpTblCatalogoItemDoc modeloCot, AdjuntoPojo adjunto) {
        BigInteger size = BigInteger.valueOf(adjunto.getPeso());
        /*modeloCot.setDescripcion(adjunto.getDescription());
        modeloCot.setMime(adjunto.getMime());
        modeloCot.setNombreFormato(adjunto.getNombreArchivo());
        modeloCot.setFecha(new Date());
        modeloCot.setTamano(size);*/
        Session currentSession = em.unwrap(Session.class);
		currentSession.save(modeloCot);
    }

}
