package com.metalsa.supplier.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.metalsa.supplier.exceptions.ResourceNotFoundException;
import com.metalsa.supplier.pojo.ClasificacionArbol;
import com.metalsa.supplier.pojo.FamiliaPojo;
import com.metalsa.supplier.services.CategoriasService;

// import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/suppliers/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasService categoriasService;

    @GetMapping(value = "/familiaFirstLevel/{id}/{idioma}")
	@ResponseBody
	public ResponseEntity<List<FamiliaPojo>> getFamiliaFirstLevel(@PathVariable("id") Integer id, @PathVariable("idioma") String idioma) throws ResourceNotFoundException {
		List<FamiliaPojo> objList = categoriasService.getFamiliaFirstLevel(id, idioma);
		return new ResponseEntity(objList, HttpStatus.OK);
	}

    @GetMapping(value = "/familiaSecondLevel/{id}/{idioma}")
	@ResponseBody
	public ResponseEntity<List<FamiliaPojo>> getFamiliaSecondLevel(@PathVariable("id") Integer id, @PathVariable("idioma") String idioma) throws ResourceNotFoundException {
		List<FamiliaPojo> objList = categoriasService.getFamiliaSecondLevel(id, idioma);
		return new ResponseEntity(objList, HttpStatus.OK);
	}

    @GetMapping(value = "/familiaThirdLevel/{id}/{idioma}")
	@ResponseBody
	public ResponseEntity<List<FamiliaPojo>> getFamiliaThirdLevel(@PathVariable("id") Integer id, @PathVariable("idioma") String idioma) throws ResourceNotFoundException {
		List<FamiliaPojo> objList = categoriasService.getFamiliaThirdLevel(id, idioma);
		return new ResponseEntity(objList, HttpStatus.OK);
	}
    
    @GetMapping("/getClasificacion/{subfamilia}")
    public ClasificacionArbol getClasificacion(@PathVariable("subfamilia") Integer subfamilia) {
        List<ClasificacionArbol> responseList = categoriasService.getReclasificacion(subfamilia);
        ClasificacionArbol response = new ClasificacionArbol();
        
        if(responseList!=null && !responseList.isEmpty())
            response = responseList.get(0);
        return response;
    }
    
}
