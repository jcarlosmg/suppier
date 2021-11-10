package com.metalsa.supplier.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.metalsa.supplier.entity.NvcTblDocsCatalogoPojo;
import com.metalsa.supplier.entity.NvcTblDocsCotizacion;
import com.metalsa.supplier.entity.NvcTblGastoAdicional;
import com.metalsa.supplier.entity.NvcTblReqLineaProv;
import com.metalsa.supplier.entity.SpTblCatLocalizacion;
import com.metalsa.supplier.entity.SpTblCatalogo;
import com.metalsa.supplier.exceptions.ResourceNotFoundException;
import com.metalsa.supplier.pojo.ICustomItemsSelect;
import com.metalsa.supplier.pojo.NvcTblDocsCotizacionPojo;
import com.metalsa.supplier.pojo.PoVendorPojo;
import com.metalsa.supplier.pojo.UenPojo;
import com.metalsa.supplier.services.SpTblCatalogoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/suppliers/catalogo")
public class SpTblCatalogoController {

    @Autowired
    private SpTblCatalogoService catalogoService;

    @RequestMapping("/findAll")
    @ResponseBody
	public ResponseEntity<List<SpTblCatalogo>> findAll() {
        List<SpTblCatalogo> objList = catalogoService.findAll();
        return new ResponseEntity(objList, HttpStatus.OK);
        // return catalogoService.findAll();
	}

    @GetMapping(value = "/findById/{id}")
    @ResponseBody
	public SpTblCatalogo findById(@PathVariable Long id)throws ResourceNotFoundException {
        return catalogoService.findById(id);        
	}

    @GetMapping(value = "/findByLocalization")
    @ResponseBody
	public List<SpTblCatalogo> findByLocalization() {
        return catalogoService.findByLocalization();
	}

    @GetMapping(value = "/getMonedasActivas")
    @ResponseBody
	public List<ICustomItemsSelect> getMonedasActivas() {
        return catalogoService.getMonedasActivas();
	}

    @GetMapping(value = "/getUnidadMedidaByIdioma")
    @ResponseBody
	public List<ICustomItemsSelect> getUnidadMedidaByIdioma() {
        return catalogoService.getUnidadMedidaByIdioma("US");
	}

    @PostMapping(value = "/save")
	public SpTblCatalogo save(@RequestBody SpTblCatalogo entity) {
        return catalogoService.save(entity);
	}
    
}
