package com.metalsa.supplier.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.metalsa.supplier.entity.NvcTblDocsCatalogoPojo;
import com.metalsa.supplier.entity.SpTblCatLocalizacion;
import com.metalsa.supplier.entity.SpTblCatalogo;
import com.metalsa.supplier.exceptions.ResourceNotFoundException;
import com.metalsa.supplier.pojo.ICustomItemsSelect;
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
    
    @GetMapping("/getProveedorByVendorEncoded")
    public PoVendorPojo getProveedorByVendorEncoded(@CookieValue(name = "vendorId") String proveedor, @CookieValue(name = "idioma") String idioma) {
        PoVendorPojo poVendorPojo = catalogoService.getProveedorByVendorEncoded(proveedor);
        poVendorPojo.setIdioma(idioma);
        return poVendorPojo;
    }
    
    @GetMapping("/uenssp/{idProveedor}")
    @ResponseBody public ResponseEntity<?> uensSP(@PathVariable("idProveedor") String idProveedor) {
        System.out.println("Catalogos uensSP");
        Map<String, Object> response = new HashMap<>();
        List<UenPojo> data = null;
        try {
            System.out.println("inside try" + idProveedor);
            data = catalogoService.uensBySupplier(idProveedor);
        } catch (DataAccessException e) {
            response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            response.put("mensaje", "Error al realizar la consulta en la base de datos de sp-locations");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/saveCatalogo", headers="Accept=application/json", method = RequestMethod.POST)
    @ResponseBody
    public Integer saveCatalogo(@RequestBody SpTblCatalogo request) {
        System.out.println("saveCatalogo controller");
        System.out.println(request.getIdCatalogo());
        System.out.println(request.getIdProveedor());
        System.out.println(request.getNombreCatalogo());
        System.out.println(request.getFechaInicioVigencia());
        System.out.println(request.getFechaFinVigencia());
        System.out.println(request.getPublicado());
        System.out.println(request.getFechaCreacion());
        System.out.println(request.getFechaActualizacion());
        System.out.println(request.getUsuarioCreacion());
        System.out.println(request.getUsuarioActualizacion());
        System.out.println(request.getActivo());
        System.out.println(request.getTipoAvisoTerminacion());
        System.out.println(request.getIdProvPunchout());
        return catalogoService.saveCatalogo(request);
        
    }
    
    
    @RequestMapping(value = "/saveLocations", method = RequestMethod.POST)
    @ResponseBody
    //public CatalogoResponse saveLocations(@RequestBody CatalogoRequest req) {
    public boolean saveLocations(@RequestBody List<SpTblCatLocalizacion> request) {
        System.out.println("");
        SpTblCatLocalizacion req = new SpTblCatLocalizacion();
        SpTblCatLocalizacion req2 = new SpTblCatLocalizacion();
        List<SpTblCatLocalizacion> locsList = new ArrayList<SpTblCatLocalizacion>();
        try {
        	//if (req != null) {
            
            /*req.setIdSpTblCatalogo(BigInteger.valueOf(1));
            req.setIdUen(BigInteger.valueOf(90));
            req.setIdLocalizacion(BigInteger.valueOf(4));
            req.setFechaCreacion(new Date());
            req.setActivo(BigInteger.valueOf(1));
            
            req2.setIdSpTblCatalogo(BigInteger.valueOf(2));
            req2.setIdUen(BigInteger.valueOf(91));
            req2.setIdLocalizacion(BigInteger.valueOf(5));
            req2.setFechaCreacion(new Date());
            req2.setActivo(BigInteger.valueOf(1));
            
            locsList.add(req);
            locsList.add(req2);*/
            //spLocationsRepo.save(locsList);
            catalogoService.saveLocation(request);
        //}
        } catch (Exception e) {
            //log.error("Error en saveCatalogo:" + e.getMessage());
        }
        return true;
    }
    
    @RequestMapping(value = "/saveFilesCat", method = RequestMethod.POST) 
    @ResponseBody
    public boolean saveCotizacionFiles(@RequestBody List<NvcTblDocsCatalogoPojo> request) {
        System.out.println("saveCotizacionFiles");
        return true; //catalogoService.saveDocCatalogo(request);
    }
    
}
