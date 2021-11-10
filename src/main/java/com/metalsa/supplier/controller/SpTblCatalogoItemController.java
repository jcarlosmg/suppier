package com.metalsa.supplier.controller;

import java.util.List;

import com.metalsa.supplier.dao.SupplierDAO;
import com.metalsa.supplier.entity.SpTblCatalogoItem;
import com.metalsa.supplier.exceptions.ResourceNotFoundException;
import com.metalsa.supplier.services.SpTblCatalogoItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// @CrossOrigin(origins = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/suppliers/item")
public class SpTblCatalogoItemController {
    
    @Autowired
    private SpTblCatalogoItemService catalogoItemService;
        
    @RequestMapping("/findAll")
	public List<SpTblCatalogoItem> findAll() {
        return catalogoItemService.findAll();
	}

    @GetMapping(value = "/findByLoc/{id}")
    @ResponseBody
	public  List<SpTblCatalogoItem> findByLoc(@PathVariable Long id) {
        return catalogoItemService.findByLoc(id);
	}
        
    @PostMapping(value = "/save")
	public SpTblCatalogoItem save(@RequestBody SpTblCatalogoItem entity) {
        return catalogoItemService.save(entity);
	}

    @PostMapping(value = "/saveList")
	public List<SpTblCatalogoItem> saveList(@RequestBody List<SpTblCatalogoItem> objResultList )
			throws ResourceNotFoundException {
        return catalogoItemService.saveList(objResultList);
		// return objResultList;
	}

    @PostMapping(value = "/deleteByList")
	public ResponseEntity<String> deleteByList(@RequestBody List<SpTblCatalogoItem> objResultList) {
               catalogoItemService.deleteByList(objResultList);
        return new ResponseEntity("{\"STATUS\" : \"OK\"}", HttpStatus.OK);
	}
    
    @PostMapping(value = "/deleteByLoc/{id}")
	public ResponseEntity<String> deleteByLoc(@PathVariable Long id) {
               catalogoItemService.deleteByLoc(id);
        return new ResponseEntity("{\"STATUS\" : \"OK\"}", HttpStatus.OK);
	}
}
