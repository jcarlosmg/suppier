package com.metalsa.supplier.services;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.metalsa.supplier.dao.SupplierDAO;
import com.metalsa.supplier.entity.Bitacora;
import com.metalsa.supplier.entity.CatItemDoc;
import com.metalsa.supplier.entity.NvcTblDocsCatalogoPojo;
import com.metalsa.supplier.entity.SpTblCatalogoItem;
import com.metalsa.supplier.pojo.AdjuntoPojo;
import com.metalsa.supplier.pojo.ClasificacionArbol;
import com.metalsa.supplier.pojo.ICustomItemsSelect;
import com.metalsa.supplier.pojo.FamiliaPojo;
import com.metalsa.supplier.repository.ISpTblCatalogoItemRepository;
import com.metalsa.supplier.repository.SpTblBitacoraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpTblCatalogoItemService {

    @Autowired
    private ISpTblCatalogoItemRepository catalogoItemRepository;

    @Autowired
    private CategoriasService categoriasService;

    @Autowired
    private SpTblBitacoraRepository bitacoraRepository;

    @Autowired
	private SupplierDAO serviceDao;

    public List<SpTblCatalogoItem> findAll(){
        return catalogoItemRepository.findAll();
    }

    public List<SpTblCatalogoItem> findByLoc(Long id){

        List<SpTblCatalogoItem> itemList = catalogoItemRepository.findByLoc(id);

        itemList.forEach((item) -> {            
            List<ClasificacionArbol> result = categoriasService.getReclasificacion(item.getIdSubfamilia()!=null ?item.getIdSubfamilia().intValue():null);
            List<FamiliaPojo> listCat = categoriasService.getFamiliaFirstLevel(id.intValue(), "US");
            item.setCategoriasCombo(listCat);
            if(result.size()>0){
                item.setIdCategoria(result.get(0).getId_Categoria());
                item.setIdFamilia(result.get(0).getId_familia());
                
                List<FamiliaPojo> listFam = categoriasService.getFamiliaSecondLevel(item.getIdCategoria().intValue(), "US");
                List<FamiliaPojo> listSubFam = categoriasService.getFamiliaThirdLevel(item.getIdFamilia().intValue(), "US");
                
                item.setFamiliasCombo(listFam);
                item.setSubfamiliasCombo(listSubFam);
            }
            item.setImagesList(addAdjuntos(item.getCatItemDocList(),item));

        });

        return itemList;
    }    

    public SpTblCatalogoItem save(SpTblCatalogoItem entity){
        catalogoItemRepository.save(entity);
        return entity;
    }

    public List<SpTblCatalogoItem> saveList(List<SpTblCatalogoItem> entityList){
        for(SpTblCatalogoItem entity: entityList) {
            List<AdjuntoPojo> imagesList = entity.getAdjuntos()!=null && entity.getAdjuntos().size()>0 ? 
                                                      entity.getAdjuntos() : null;  
                                                      
            if(entity.getPrecioMercado()==null){
                 entity.setPrecioMercado(entity.getPrecioUnitario());
                 entity.setDescuento(0.0D);
            }    
            if(entity.getPrecioUnitario()==null){
                 entity.setPrecioUnitario(entity.getPrecioMercado());
                 entity.setDescuento(0.0D);
            }    
            catalogoItemRepository.save(entity);
            serviceDao.saveAdjuntos(imagesList, entity.getIdItem());
        }
         return entityList;
    }

    public void deleteByList(List<SpTblCatalogoItem> entityList){
        for(SpTblCatalogoItem entity: entityList) {
            if(entity.getIdItem()!=null) {
               entity.setStatus(0);
               catalogoItemRepository.save(entity);            
               recordBitacora("Elimination", new Date(), entity.getIdItem(), 
                              "Item " + entity.getDescripcion() + " - " + entity.getIdItem() + "was deleted",
                              "NA",
                              entity.getIdCatalogoUen(),
                              entity.getUsuarioActualizacion() //// añadir vendor
                              ); 
            }
        }
        
    }

    public void deleteByLoc(Long id){
        catalogoItemRepository.deleteByLoc(id);
    }

    private List<NvcTblDocsCatalogoPojo> addAdjuntos(List<CatItemDoc> catItemDocList, SpTblCatalogoItem item){
        List<NvcTblDocsCatalogoPojo> docsList = new ArrayList<>();
        NvcTblDocsCatalogoPojo docs = new NvcTblDocsCatalogoPojo();
        List<AdjuntoPojo> adjuntosList = new ArrayList<>();

        catItemDocList.forEach((doc) -> { 
            AdjuntoPojo file = new AdjuntoPojo();
            file.setDescription(doc.getNombreArchivo());
            file.setMime(doc.getExtensionArchivo());
            file.setNombreArchivo(doc.getNombreArchivo());
            file.setPeso(doc.getTamanioArchivo().longValue());
            file.setFile(null);
            //adjuntosList.add(file);
        });
        adjuntosList = serviceDao.getFilesByIdCat(item.getIdItem().intValue(), "item");
        // Log.debug(" adjuntosList " + adjuntosList.size());
        docs.setAdjuntos(adjuntosList);
        docs.setIdItem(item.getIdItem().intValue());
        docsList.add(docs);

        return docsList;
    }

    private void recordBitacora(String action, Date actionDate, Long item, String resume,
                     String status, Long idCatalogo, String vendor){
        Bitacora bitacora = new Bitacora();
        bitacora.setAction(action);
        bitacora.setDateOfAction(new Date());        
        bitacora.setIdItem(item);
        bitacora.setResume(resume);
        bitacora.setStatus(status);
        bitacora.setVendor(vendor);
        bitacora.setIdCatalogo(idCatalogo);

        bitacoraRepository.save(bitacora);
    }
    
}
