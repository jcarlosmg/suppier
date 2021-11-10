package com.metalsa.supplier.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.metalsa.supplier.dao.impl.SupplierDaoImpl;
import com.metalsa.supplier.entity.Moneda;
import com.metalsa.supplier.entity.NvcTblDocsCotizacion;
import com.metalsa.supplier.entity.NvcTblOaProveedoresH;
import com.metalsa.supplier.entity.NvcTblReqLineaProv;
import com.metalsa.supplier.entity.UnidadMedida;

import com.metalsa.supplier.pojo.LoginPojo;
import com.metalsa.supplier.pojo.LoginPojo2;
import com.metalsa.supplier.pojo.OaIvaUenPojo;
import com.metalsa.supplier.pojo.PoVendorPojo;
import com.metalsa.supplier.pojo.RfqLineaRequest;
import com.metalsa.supplier.pojo.RfqPojo;
import com.metalsa.supplier.pojo.RfqProveedorRequest;
import com.metalsa.supplier.pojo.UserPojo;
import com.metalsa.supplier.pojo.UserPojo2;
import com.metalsa.supplier.services.PortalProveedorService;
import com.metalsa.supplier.services.SpTblCatalogoService;

@RestController
@RequestMapping("/api/suppliers/proveedor")
public class PortalProveedorController {

    @Autowired
    private PortalProveedorService portalProveedorService;
    @Autowired
    private SpTblCatalogoService catalogoService;
    
    @Autowired
	private SupplierDaoImpl service;

    @GetMapping("/getProveedorByVendorEncoded/{proveedor}/{idioma}")
    public PoVendorPojo getProveedorByVendorEncoded(@PathVariable String proveedor, @PathVariable String idioma) {    	

    	 Map<String, String> poVendorPojo = new HashMap();
         PoVendorPojo vendor  = portalProveedorService.getProveedorByVendorEncoded(proveedor);
         poVendorPojo.put("vendor_name", vendor.getVendorName());
         poVendorPojo.put("vendor_id", vendor.getVendorId().toString());
         poVendorPojo.put("proveedor",vendor.getVendorName());
         poVendorPojo.put("idioma",vendor.getIdioma());
         vendor.setIdioma(idioma);
         return vendor;
    }
    
    @GetMapping("/getProveedorByVendorEncoded2/{proveedor}/{idioma}")
    public PoVendorPojo getProveedorByVendorEncoded2(@PathVariable String proveedor, @PathVariable String idioma) {    	

    	 Map<String, String> poVendorPojo = new HashMap();
         PoVendorPojo vendor  = portalProveedorService.getProveedorByVendorEncoded2(proveedor);
         poVendorPojo.put("vendor_name", vendor.getVendorName());
         poVendorPojo.put("vendor_id", vendor.getVendorId().toString());
         poVendorPojo.put("proveedor",vendor.getVendorName());
         poVendorPojo.put("idioma",vendor.getIdioma());
         vendor.setIdioma(idioma);
         return vendor;
         
    }

    @RequestMapping(value = "/getCotizacionesProveedor", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<RfqPojo> getCotizacionensProveedor(@RequestBody RfqProveedorRequest request) {
        return portalProveedorService.getCotizacionesProveedor(request);
    }

    @RequestMapping(value = "/getProveedorByLikeName/{proveedor}/{idUen}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<NvcTblOaProveedoresH> getProveedoresByLikeName(@PathVariable String proveedor, @PathVariable BigDecimal idUen) {
        return portalProveedorService.getProveedorByLikeName(proveedor, idUen);
    }

    @RequestMapping(value = "/getIvaByUen/{idUen}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<OaIvaUenPojo> getIvaByUen(@PathVariable Integer idUen) {
        return portalProveedorService.getIvaByUen(idUen);
    }

    @RequestMapping(value = "/getMonedasActivas", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Moneda> getMonedasActivas() {
        return portalProveedorService.getMonedasActivas();
    }

    @RequestMapping(value = "/getUnidadMedidaByIdioma/{idioma}", method = RequestMethod.GET)
    @ResponseBody
    public List<UnidadMedida> getUnidadMedidaByIdioma(@PathVariable String idioma) {
        return portalProveedorService.getUnidadMedidaByIdioma(idioma);
    }

    @RequestMapping(value = "/getLineaCotByRfqAndSupplier", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<NvcTblReqLineaProv> getLineaCotByRfqAndSupplier(@RequestBody RfqLineaRequest request) {
        List<NvcTblReqLineaProv>  list = portalProveedorService.getLineaByRfqAndSupplier(request);
        return list;
    }
    
    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST) 
    @ResponseBody
    public boolean deleteFiles(@RequestBody NvcTblDocsCotizacion request) {
        return catalogoService.deleteDocCotizacion(request);
    }
    
    @GetMapping("/guardarIdCotizacion/{idRfq}/{idCotizacion}")
    public void guardarIdCotizacion(@PathVariable String idRfq, @PathVariable String idCotizacion) {
        catalogoService.guardarIdCotizacion(idCotizacion, idRfq);
    }
    
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    @ResponseBody
    public UserPojo getUser(@RequestBody LoginPojo request) {    	
    	UserPojo resp = catalogoService.getUser(request);
    	return resp;

    }
    
    @RequestMapping(value = "/validaProveedor", headers="Accept=application/json", method = RequestMethod.POST)
    @ResponseBody
    public UserPojo2 validaProveedor(@RequestBody LoginPojo2 request) throws ParseException {
        return service.validaProveedor(request);
        
    }
    
    @GetMapping(path = "/tipoTransporte_n/{id}")
    @ResponseBody
    public String gettipoTransporte_n(@PathVariable Integer id) {
        String tipoTr = "";
        
        tipoTr = portalProveedorService.getTipoTransporte_n(id);
        
        return tipoTr;
    }
    
    @GetMapping(path = "/getTerminosTransporte_n/{id}")
    @ResponseBody
    public String getTerminosTransporte_n(@PathVariable Integer id) {
        String tipoTr = "";
        tipoTr = portalProveedorService.getTerminosTransporte_n(id);
        
        return tipoTr;
    }
    
}
