package com.metalsa.supplier.services;

import java.util.List;

import com.metalsa.supplier.entity.SpTblCatalogoItem;
import com.metalsa.supplier.repository.ISpTblCatalogoItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpTblCatalogoItemService {

    @Autowired
    private ISpTblCatalogoItemRepository catalogoItemRepository;

    public List<SpTblCatalogoItem> findAll(){
        return catalogoItemRepository.findAll();
    }

    public List<SpTblCatalogoItem> findByLoc(Long id){
        return catalogoItemRepository.findByLoc(id);
    }    

    public SpTblCatalogoItem save(SpTblCatalogoItem entity){
        catalogoItemRepository.save(entity);
         return entity;
    }

    public List<SpTblCatalogoItem> saveList(List<SpTblCatalogoItem> entityList){
        for(int i=0; i<entityList.size(); i++) {
			SpTblCatalogoItem entity = entityList.get(i);
            catalogoItemRepository.save(entity);
        }
         return entityList;
    }

    public void deleteByList(List<SpTblCatalogoItem> entityList){
        for(int i=0; i<entityList.size(); i++) {
			SpTblCatalogoItem entity = entityList.get(i);
            if(entity.getIdItem()!=null) catalogoItemRepository.deleteById(entity.getIdItem());
        }
        
    }

    public void deleteByLoc(Long id){
        catalogoItemRepository.deleteByLoc(id);
    }
    
}
