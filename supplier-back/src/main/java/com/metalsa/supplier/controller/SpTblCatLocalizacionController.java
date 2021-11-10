package com.metalsa.supplier.controller;

import com.metalsa.supplier.entity.NvcTblUensActivas;
import com.metalsa.supplier.entity.SpTblCatLocalizacion;
import com.metalsa.supplier.pojo.ICustomItemsSelect;
import com.metalsa.supplier.services.SpTblCatLocalizacionService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// @CrossOrigin(origins = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/suppliers/localizacion")
public class SpTblCatLocalizacionController {

    @Autowired
    private SpTblCatLocalizacionService catLocalizacionService;

    @RequestMapping("/findAll")
	public List<SpTblCatLocalizacion> findAll() {
        return catLocalizacionService.findAll();
	}

    @GetMapping(value = "/findById/{id}")
    @ResponseBody
	public SpTblCatLocalizacion findById(@PathVariable Long id) {
        return catLocalizacionService.findById(id);
	}

    @GetMapping(value = "/findByCatalogo/{id}")
    @ResponseBody
	public List<SpTblCatLocalizacion> findByCatalogo(@PathVariable Long id) {
        return catLocalizacionService.findByCatalogo(id);
	}
    
    @GetMapping(value = "/findUensByCatalogo/{idCat}")
    @ResponseBody
	public List<ICustomItemsSelect> findUensByCatalogo(@PathVariable Long idCat) {
        return catLocalizacionService.getUensByCatalogo(idCat);
	}

    // @GetMapping(value = "/items")
	// @ResponseBody
	// public ResponseEntity<ICustomItemsSelect> items() throws ResourceNotFoundException {
	// 	List<ICustomItemsSelect> items = tipoPopsRepository.getItems();
	// 	return new ResponseEntity(items, HttpStatus.OK);
	// }

    @PostMapping(value = "/save")
	public SpTblCatLocalizacion save(@RequestBody SpTblCatLocalizacion entity) {
        return catLocalizacionService.save(entity);
	}
    
}
