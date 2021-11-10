package com.metalsa.supplier.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.metalsa.supplier.dao.SupplierDAO;
import com.metalsa.supplier.entity.Bitacora;
import com.metalsa.supplier.entity.NvcTblDocsCatalogoPojo;
import com.metalsa.supplier.entity.NvcTblGastoAdicional;
import com.metalsa.supplier.entity.NvcTblReqLineaProv;
import com.metalsa.supplier.entity.SpTblCatLocalizacion;
import com.metalsa.supplier.entity.SpTblCatalogo;
import com.metalsa.supplier.pojo.AdjuntoPojo;
import com.metalsa.supplier.pojo.NvcTblDocsCotizacionPojo;
import com.metalsa.supplier.pojo.UenPojo;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
	
	@Autowired
	private SupplierDAO service;
    
    @GetMapping("/uenssp/{idProveedor}")
    @ResponseBody public ResponseEntity<?> uensSP(@PathVariable("idProveedor") String idProveedor) {
        Map<String, Object> response = new HashMap<>();
        List<UenPojo> data = null;
        try {
            System.out.println("inside try" + idProveedor);
            data = service.searchByPL(idProveedor);
        } catch (DataAccessException e) {
            response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            response.put("mensaje", "Error al realizar la consulta en la base de datos de sp-locations");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/saveCatalogo", headers="Accept=application/json", method = RequestMethod.POST)
    @ResponseBody
    public Integer saveCatalogo(@RequestBody SpTblCatalogo request) throws ParseException {
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
        return service.saveCatalog(request);
        
    }
    
    
    @RequestMapping(value = "/saveLocations", method = RequestMethod.POST)
    @ResponseBody
    //public CatalogoResponse saveLocations(@RequestBody CatalogoRequest req) {
    public boolean saveLocations(@RequestBody List<SpTblCatLocalizacion> request) {
        try {
            service.saveLocation(request);
        } catch (Exception e) {
        }
        return true;
    }
    
    @RequestMapping(value = "/saveFilesCat", method = RequestMethod.POST) 
    @ResponseBody
    public boolean saveCotizacionFiles(@RequestBody List<NvcTblDocsCatalogoPojo> request) {
        return service.saveDocCatalog(request);
    }        
    
    @GetMapping("/getFilesByIdCat/{id}/{type}")
    @ResponseBody public ResponseEntity<?> findFilesByIdCat(@PathVariable("id") Integer id, @PathVariable("type") String type){
    	List<AdjuntoPojo> data = service.getFilesByIdCat(id, type);
		return new ResponseEntity<>(data, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/saveFiles", method = RequestMethod.POST) 
    @ResponseBody
    public boolean saveFiles(@RequestBody List<NvcTblDocsCotizacionPojo> request) {
        return service.saveDocCotization(request);
    }

    @RequestMapping(value = "/removeFileByItem/{item}", method = RequestMethod.POST) 
    @ResponseBody
    public void removeFileByItem(@PathVariable("item") Integer item, @RequestBody AdjuntoPojo adjunto) {
        service.removeFileByItem(adjunto, item);
    }
    
    @RequestMapping(value = "/sendQuotation", method = RequestMethod.POST) 
    @ResponseBody
    public boolean sendQuotation(@RequestBody List<NvcTblReqLineaProv> request) {
        return service.senQuaotation(request);
    }
    
    @RequestMapping(value = "/saveReqLineaProv", method = RequestMethod.PUT)
    @ResponseBody
    public boolean saveReqLineaProv(@RequestBody List<NvcTblReqLineaProv> request) {
        return service.saveReqLieaProv(request);
    }
    
    @RequestMapping(value = "/deleteGasto", method = RequestMethod.POST) 
    @ResponseBody
    public boolean deleteGastosAdicionales(@RequestBody NvcTblGastoAdicional request) {
        return service.deleteGasto(request);
    }
    
    @RequestMapping(value = "/saveGastosAdicionales", method = RequestMethod.POST)
    @ResponseBody
    public boolean saveGastosAdicionales(@RequestBody List<NvcTblGastoAdicional> request) {
        return service.saveGastoAdicional(request);
    }
    
    @RequestMapping(value = "/getGastosAdicionalesByIdReqLineaProv/{idReqLineaProv}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<NvcTblGastoAdicional> getGastosAdicionalesByIdReqLineaProv(@PathVariable Integer idReqLineaProv) {
        return service.getByIdReqLineaProv(idReqLineaProv);
    }
    
    @GetMapping("/bitacora/{idCatalogo}")
    @ResponseBody
    public ResponseEntity<?> getBitacora(@PathVariable("idCatalogo") Long idCatalogo)
    {
    	List<Bitacora> bitacora = service.getBitacora(idCatalogo);
    	return new ResponseEntity<>(bitacora, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/bitacora", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> setBitacora(@RequestBody Bitacora bitacora)
    {
    	boolean isValid = service.addNewRecord(bitacora);
    	return new ResponseEntity<>(isValid, HttpStatus.OK);
    }
    
}
