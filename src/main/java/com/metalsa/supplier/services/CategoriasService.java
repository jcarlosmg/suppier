package com.metalsa.supplier.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import com.metalsa.supplier.pojo.ClasificacionArbol;
import com.metalsa.supplier.pojo.FamiliaPojo;

import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j
@Service
public class CategoriasService {

    @PersistenceContext
    private EntityManager em;
    
    public List<FamiliaPojo> getFamiliaFirstLevel(Integer idUen, String idIdioma) {
        List<FamiliaPojo> result;
        try {
            StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Familia.FirstLevel");
            store.setParameter("p_id_uen", idUen);
            store.setParameter("p_id_idioma", idIdioma);
            store.setParameter("p_tipo_requisicion", 1);
            store.execute();
            result = store.getResultList();
            log.debug("result - >" + result);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            result = new ArrayList();
        }
        return result;
    }

    public List<FamiliaPojo> getFamiliaSecondLevel(Integer idFamilyParent, String idIdioma) {
        List<FamiliaPojo> result;
        try {
            StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Familia.SecondLevel");
            store.setParameter("p_id_family_parent", idFamilyParent);
            store.setParameter("p_id_idioma", idIdioma);
            store.execute();
            result = store.getResultList();

        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            result = new ArrayList();
        }
        return result;

    }
    
    public List<FamiliaPojo> getFamiliaThirdLevel(Integer idFamilyParent, String idIdioma) {
        List<FamiliaPojo> result;
        try {
            StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Familia.ThirdLevel");
            store.setParameter("p_id_family_parent", idFamilyParent);
            store.setParameter("p_id_idioma", idIdioma);
            store.execute();
            result = store.getResultList();
            // log.debug("result - >" + result);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            result = new ArrayList();
        }
        return result;
    }
    
    //public List<Map> getReclasificacion(Integer idSubFamila) {
    public List<ClasificacionArbol> getReclasificacion(Integer idSubFamila) {
        //List<Map> result;
    	List<ClasificacionArbol> result;
        try {
            StoredProcedureQuery store = em.createNamedStoredProcedureQuery("Catalogos.GetArbolByIdSub");
            store.setParameter("p_id_sub_familia", idSubFamila);
            store.execute();
            result = store.getResultList();
            log.debug("result - >" + result);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            result = new ArrayList();
        }
        return result;
    }
}
