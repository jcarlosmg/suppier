package com.metalsa.supplier.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.metalsa.supplier.controller.TokenApiController;
import com.metalsa.supplier.dao.SupplierDAO;
import com.metalsa.supplier.entity.Bitacora;
import com.metalsa.supplier.entity.DcEstatus;
import com.metalsa.supplier.entity.NvcTblCotizaciones;
import com.metalsa.supplier.entity.NvcTblDocsCatalogoPojo;
import com.metalsa.supplier.entity.NvcTblDocsCotizacion;
import com.metalsa.supplier.entity.NvcTblGastoAdicional;
import com.metalsa.supplier.entity.NvcTblReqLineaProv;
import com.metalsa.supplier.entity.SpTblCatLocalizacion;
import com.metalsa.supplier.entity.SpTblCatalogo;
import com.metalsa.supplier.entity.SpTblCatalogoItemDoc;
import com.metalsa.supplier.pojo.AdjuntoPojo;
import com.metalsa.supplier.pojo.ContactsPojo;
import com.metalsa.supplier.pojo.LocationUen;
import com.metalsa.supplier.pojo.LoginPojo2;
import com.metalsa.supplier.pojo.NvcTblDocsCotizacionPojo;
import com.metalsa.supplier.pojo.UenPojo;
import com.metalsa.supplier.pojo.UserPojo;
import com.metalsa.supplier.pojo.UserPojo2;
import com.metalsa.supplier.repository.DcEstatusRepository;
import com.metalsa.supplier.repository.NvcTblDocsCotizacionRepository;
import com.metalsa.supplier.repository.NvcTblGastoAdicionalRepository;
import com.metalsa.supplier.repository.NvcTblReqLineaProvRepository;
import com.metalsa.supplier.repository.SpLocationsRepository;
import com.metalsa.supplier.repository.SpTblBitacoraRepository;
import com.metalsa.supplier.repository.SpTblCatalogoItemDocRepository;
import com.metalsa.supplier.utilities.UploadFile;

@Service
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
	
	@Autowired
    private SpLocationsRepository spLocationsRepo;

	@Autowired
	private NvcTblGastoAdicionalRepository gastoAdicionalRepository;

	@Autowired
	private NvcTblDocsCotizacionRepository docsCotizacionRepository;
	
	@Autowired
    private SpTblCatalogoItemDocRepository docsCatalogoRepository;

	@Autowired
	private DcEstatusRepository estatusRepository;

	@Autowired
	private NvcTblReqLineaProvRepository tblReqLineaProvRepository;
	
    @Autowired
    private TokenApiController tokenApi;
	
	@Autowired
	private SpTblBitacoraRepository bitacoraRepository;
	
	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

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
			// log.debug(e.getMessage(), e);
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
			// log.debug(e.getMessage(), e);
			result = new ArrayList();
		}
		return result;
	}

	@Override
	public int saveCatalog(SpTblCatalogo request) {
		String idCat = null;
		StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Catalogos.SaveCatalogo");
		if(request.getIdCatalogo() != null)
			store.setParameter("p_id_catalogo", Math.toIntExact(request.getIdCatalogo()));
		else
			store.setParameter("p_id_catalogo", 0);
		store.setParameter("p_id_proveedor", request.getIdProveedor());
		store.setParameter("p_nombre_catalogo", request.getNombreCatalogo());
		store.setParameter("p_fecha_inicio_vigencia", formatter.format(request.getFechaInicioVigencia()));
		store.setParameter("p_fecha_fin_vigencia", formatter.format(request.getFechaFinVigencia()));
		store.setParameter("p_publicado", request.getPublicado());
		store.setParameter("p_fecha_creacion", formatter.format(request.getFechaCreacion()));
		store.setParameter("p_fecha_actualizacion", formatter.format(request.getFechaActualizacion()));
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
	
	public UserPojo2 validaProveedor(LoginPojo2 request) {
		StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Validar.ValidaProveedor");
		store.setParameter("p_usuario", request.getUser());
		store.setParameter("p_password", request.getAttribute2());
		store.setParameter("p_token", request.getToken());
		store.setParameter("p_quien", request.getQuien());
		
		store.execute();
		String mensaje = (String) store.getOutputParameterValue("mensaje");
		
		UserPojo2 u = new Gson().fromJson(mensaje, UserPojo2.class);
		if(u.getMensaje().equals("ok")) {
			u.setToken(tokenApi.getJWTToken2(u.getVendor_id(), u.getIdioma(), u.getNombre()));
			u.setVendorName(u.getNombre());
			u.setStatus(true);
		}

		return u;
	}

	@Override
	public void saveLocation(List<SpTblCatLocalizacion> request) {
		spLocationsRepo.saveAll(request);
	}

	@Override
	public boolean saveAdjuntos(List<AdjuntoPojo> adjuntos, Long idItem) {
		boolean doSuccess = false;
		try{
				for (AdjuntoPojo adjunto : adjuntos) {
					

				List<SpTblCatalogoItemDoc> found = docsCatalogoRepository.findByItemNameFile(idItem, adjunto.getNombreArchivo());	
				if(found.size()<1){
					SpTblCatalogoItemDoc modeloCatItem = new SpTblCatalogoItemDoc();
					if(idItem!=null ){					
						docsCatalogoRepository.deleteByName(idItem, adjunto.getNombreArchivo());										
						modeloCatItem.setIdItem(Long.valueOf(idItem.intValue()));
						modeloCatItem.setUrlFtp(UploadFile.FTP_PATH_ITEM_DCOM + idItem + "/");											
					}

					modeloCatItem.setIdTipoDocumento(Long.valueOf(1));
					modeloCatItem.setNombreArchivo(adjunto.getNombreArchivo());
					modeloCatItem.setNombreArchivoFtp(adjunto.getNombreArchivo());
					modeloCatItem.setExtensionArchivo(adjunto.getMime());
					modeloCatItem.setTamanioArchivo((Long) adjunto.getPeso());
					
					modeloCatItem.setFechaCreacion(new Date());
					modeloCatItem.setUsuarioCreacion("usuarioC");
					modeloCatItem.setUsuarioActualizacion("usuarioUp");

					persistDocumento(modeloCatItem, adjunto);
					try{
					InputStream fis = new ByteArrayInputStream(adjunto.getFile()==null?new byte[]{}:adjunto.getFile());
					if(fis.available() > 0)
						UploadFile.upload(fis, modeloCatItem.getUrlFtp(),modeloCatItem.getNombreArchivo(), host, port, user, pass);
					
					}catch(IOException ex){}
				}
			}

			// try{
			// 	//InputStream fis = new ByteArrayInputStream(adjunto.getFile()==null?new byte[]{}:adjunto.getFile());
			// 	//if(fis.available() > 0)
			// 		String path = UploadFile.FTP_PATH_ITEM_DCOM + idItem + "/";
			// 		UploadFile.uploadList(adjuntos, path, host, port, user, pass);
				
			// 	}catch(Exception ex){}

			doSuccess = true;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("------- Error in saveDocCotizacion -------");
			System.out.println(e.getMessage());
			doSuccess = false;
		}

		return doSuccess;
	}


	@Override
	public boolean saveDocCatalog(List<NvcTblDocsCatalogoPojo> catalogos) {
		boolean doSuccess = false;
		try {
			catalogos.forEach((catalogo) -> {
				for (AdjuntoPojo adjunto : catalogo.getAdjuntos()) {
					 SpTblCatalogoItemDoc modeloCatItem = new SpTblCatalogoItemDoc();

					if(catalogo.getIdItem()!=null && catalogo.getIdCatalogo()==null){
						// modeloCatItem = docsCatalogoRepository.findByItemNameFile(catalogo.getIdItem().longValue(), adjunto.getNombreArchivo()); 
						docsCatalogoRepository.deleteByName(catalogo.getIdItem().longValue(), adjunto.getNombreArchivo());
						
						System.out.println("item - " + modeloCatItem.getNombreArchivo());
						modeloCatItem.setIdItem(Long.valueOf(catalogo.getIdItem().intValue()));
						modeloCatItem.setUrlFtp(UploadFile.FTP_PATH_ITEM_DCOM + catalogo.getIdItem() + "/");
												
					}

					if(catalogo.getIdCatalogo()!=null && catalogo.getIdItem()==null){ 
						System.out.println("cat");
						modeloCatItem.setIdCatalogoUen(Long.valueOf(catalogo.getIdCatalogo().intValue()));
						modeloCatItem.setUrlFtp(UploadFile.FTP_PATH_CAT_DCOM + catalogo.getIdCatalogo() + "/"
												+ adjunto.getNombreArchivo());
					}
					modeloCatItem.setIdTipoDocumento(Long.valueOf(1));
					modeloCatItem.setNombreArchivo(adjunto.getNombreArchivo());
					modeloCatItem.setNombreArchivoFtp(adjunto.getNombreArchivo());
					modeloCatItem.setExtensionArchivo(adjunto.getMime());
					modeloCatItem.setTamanioArchivo((Long) adjunto.getPeso());
					
					modeloCatItem.setFechaCreacion(new Date());
					modeloCatItem.setUsuarioCreacion("usuarioC");
					modeloCatItem.setUsuarioActualizacion("usuarioUp");

					persistDocumento(modeloCatItem, adjunto);
					try{
					InputStream fis = new ByteArrayInputStream(adjunto.getFile()==null?new byte[]{}:adjunto.getFile());
					if(fis.available() > 0)
						UploadFile.upload(fis, modeloCatItem.getUrlFtp(),modeloCatItem.getNombreArchivo(), host, port, user, pass);
					
					}catch(IOException ex){}
				}
			});
			doSuccess = true;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("------- Error in saveDocCotizacion -------");
			System.out.println(e.getMessage());
			doSuccess = false;
		}

		return doSuccess;
	}

	public void persistDocumento(SpTblCatalogoItemDoc modeloCatItem, AdjuntoPojo adjunto) {
		BigInteger size = BigInteger.valueOf(adjunto.getPeso());
		docsCatalogoRepository.save(modeloCatItem);
	}

	public void persistDocumento(NvcTblDocsCotizacion modeloCot, AdjuntoPojo adjunto) {
		BigInteger size = BigInteger.valueOf(adjunto.getPeso());
		modeloCot.setDescripcion(adjunto.getDescription());
		modeloCot.setMime(adjunto.getMime());
		modeloCot.setNombreFormato(adjunto.getNombreArchivo());
		modeloCot.setFecha(new Date());
		modeloCot.setTamano(size);
		docsCotizacionRepository.save(modeloCot);
	}

	@Override
	public boolean saveDocCotization(List<NvcTblDocsCotizacionPojo> request) {
		try {
			request.forEach((cotizacion) -> {
				for (AdjuntoPojo adjunto : cotizacion.getAdjuntos()) {
					NvcTblDocsCotizacion modeloCot = new NvcTblDocsCotizacion();
					NvcTblDocsCotizacion modeloCotTem = new NvcTblDocsCotizacion();
					modeloCot.setIdCotizacion(new NvcTblCotizaciones(cotizacion.getIdCotizacion()));
					modeloCot.setRutaRaiz(UploadFile.FTP_PATH_ROOT_DCOM);
					modeloCot.setRuta(UploadFile.FTP_PATH_COT_DCOM);
					modeloCot.setEnviado("N".charAt(0));
					modeloCot.setConsecutivo(0);
					persistDocumento(modeloCot, adjunto);
					modeloCotTem = docsCotizacionRepository.getByIdDocumento(modeloCot.getIdDocumento());
					InputStream fis = new ByteArrayInputStream(adjunto.getFile());
					modeloCotTem.setRuta(UploadFile.FTP_PATH_COT_DCOM + modeloCotTem.getNombre());
					docsCotizacionRepository.save(modeloCotTem);
					UploadFile.upload(fis, UploadFile.FTP_PATH_COT_DCOM, modeloCotTem.getNombre(), host, port, user,
							pass);
				}
			});
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("------- Error in saveDocCotizacion -------");
			System.out.println(e.getMessage());
		}

		return false;
	}

	@Override
	public boolean senQuaotation(List<NvcTblReqLineaProv> request) {
		try {
			request.forEach(x -> x.setCapturadoPor("S"));
			DcEstatus estatus = estatusRepository.findByDescEstatus("COTIZADA");

			request.forEach((lineaProv) -> {
				if (lineaProv.getDeclinada().equals("NO")) {
					lineaProv.setIdEstatus(estatus.getScId().intValue());
				}
			});

			tblReqLineaProvRepository.saveAll(request);

			return true;

		} catch (Exception e) {
			System.out.println("------- Error in sendQuaotation -------");
			System.out.println(e.getMessage());
		}

		return false;
	}

	@Override
	public boolean saveReqLieaProv(List<NvcTblReqLineaProv> lineas) {
		try {
			lineas.forEach(x -> x.setCapturadoPor("S"));
			tblReqLineaProvRepository.saveAll(lineas);
			return true;
		} catch (Exception e) {
		}

		return false;
	}

	@Override
	public boolean deleteGasto(NvcTblGastoAdicional gasto) {
		try {
			gastoAdicionalRepository.delete(gasto);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("------- Error save saveReqLineaProv -------");
			System.out.println(e.getMessage());
		}

		return false;
	}

	@Override
	public boolean saveGastoAdicional(List<NvcTblGastoAdicional> gastos) {
		try {
            for (NvcTblGastoAdicional gasto : gastos) {
                if (gasto.isEliminar()) {
                    gastoAdicionalRepository.delete(gasto);
                } else {
                    gastoAdicionalRepository.save(gasto);
                }
                
            }
            
            return true;
        } catch (Exception e) {
            System.out.println("------- Error save cotizaciones -------");
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
            System.out.println("----------------------------------------");
        }
        
        return false;
	}

	@Override
	public Iterable<NvcTblGastoAdicional> getByIdReqLineaProv(Integer idReqLineaProv) {
		return gastoAdicionalRepository.getByIdReqLineaProv(idReqLineaProv);
	}

	public List<AdjuntoPojo> getFilesByIdCat(Integer id, String type) {
		List<AdjuntoPojo> files = new ArrayList<>();
		if(type.contentEquals("cat"))
		{
			List<SpTblCatalogoItemDoc> docs = docsCatalogoRepository.findDocsByIdCat(Long.valueOf(id.intValue()));
			for(SpTblCatalogoItemDoc doc : docs)
			{
				try {
					files.add(UploadFile.getFilesById(doc,host, port, user,pass));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
			}
		}
		else
		{
			List<SpTblCatalogoItemDoc> docs = docsCatalogoRepository.findDocsByIdItem(Long.valueOf(id.intValue()));
			
			for(SpTblCatalogoItemDoc doc : docs){			
			
				System.out.println("-----------------docs " + doc.getUrlFtp() + doc.getNombreArchivo());
				try {
					files.add(UploadFile.getFilesById(doc,host, port, user,pass));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
			}
		}
		return files;
		
	}

	public void removeFileByItem(AdjuntoPojo adjunto, Integer idItem){
		List<SpTblCatalogoItemDoc> modeloCatItemList  = docsCatalogoRepository.findByItemNameFile(idItem.longValue(), adjunto.getNombreArchivo());
		System.out.println("id " + idItem.longValue() + "-" + adjunto.getNombreArchivo());
		docsCatalogoRepository.deleteByName(idItem.longValue(), adjunto.getNombreArchivo());
		for(SpTblCatalogoItemDoc modeloCatItem : modeloCatItemList){			
			UploadFile.deleteFile(modeloCatItem.getUrlFtp(),modeloCatItem.getNombreArchivo(), host, port, user, pass);		
		}
	}

	@Override
	public List<Bitacora> getBitacora(Long idCatalogo)
	{
		return bitacoraRepository.findLogByIdCatalog(idCatalogo);
	}

	@Override
	public boolean addNewRecord(Bitacora bitacora) {
		Bitacora valid = null;
		valid = bitacoraRepository.save(bitacora);
		if(valid != null)
			return true;
		return false;
	}

}
