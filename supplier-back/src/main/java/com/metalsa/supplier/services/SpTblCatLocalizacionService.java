package com.metalsa.supplier.services;

import com.metalsa.supplier.entity.NvcTblUensActivas;
import com.metalsa.supplier.entity.SpTblCatLocalizacion;
import com.metalsa.supplier.pojo.ICustomItemsSelect;
import com.metalsa.supplier.repository.ISpTblCatLocalizacionRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpTblCatLocalizacionService {
    
    @Autowired
    private ISpTblCatLocalizacionRepository catLocalizacionRepository;

    public List<SpTblCatLocalizacion> findAll(){
        return catLocalizacionRepository.findAll();
    }

    public SpTblCatLocalizacion findById(Long id){
        return catLocalizacionRepository.findById(id).get();
    }

    public List<SpTblCatLocalizacion> findByCatalogo(Long id){
        return catLocalizacionRepository.findByCatalogo(id);
    }

    public List<ICustomItemsSelect> getUensByCatalogo(Long idCat){
        return catLocalizacionRepository.getUensByCatalogo(idCat);
    }

    public SpTblCatLocalizacion save(SpTblCatLocalizacion entity){
        catLocalizacionRepository.save(entity);
         return entity;
    }

    public void delete(Long id){
        catLocalizacionRepository.deleteById(id);
    }
}
