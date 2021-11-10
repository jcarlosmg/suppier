package com.metalsa.supplier.services;

import java.math.BigDecimal;
import java.util.List;

import com.metalsa.supplier.entity.NvcTblOaProveedoresH;
import com.metalsa.supplier.entity.NvcTblReqLineaProv;
import com.metalsa.supplier.pojo.OaIvaUenPojo;
import com.metalsa.supplier.pojo.PoVendorPojo;
import com.metalsa.supplier.pojo.RfqLineaRequest;
import com.metalsa.supplier.pojo.RfqPojo;
import com.metalsa.supplier.entity.Moneda;
import com.metalsa.supplier.entity.UnidadMedida;
import com.metalsa.supplier.pojo.RfqProveedorRequest;
import com.metalsa.supplier.repository.NvcTblReqLineaProvRepository;
import com.metalsa.supplier.repository.PortalProveedorRepository;
import com.metalsa.supplier.repository.ProveedoresRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortalProveedorService {

    @Autowired
    private ProveedoresRepository proveedoresRepo;
    @Autowired
    private PortalProveedorRepository portalProveedorRepository;
    @Autowired
    private NvcTblReqLineaProvRepository tblReqLineaProvRepository;

    public PoVendorPojo getProveedorByVendorEncoded(String vendorEncoded){
        return portalProveedorRepository.getProveedorByVendorEncoded(vendorEncoded);
    }
    
    public PoVendorPojo getProveedorByVendorEncoded2(String vendorEncoded){
        return portalProveedorRepository.getProveedorByVendorEncoded2(vendorEncoded);
    }

    public List<NvcTblOaProveedoresH> getProveedorByLikeName(String name, BigDecimal idUen) {
        return proveedoresRepo.buscarLikeNombreByUen_("%" + name.toUpperCase() + "%", idUen);
    }

    public List<RfqPojo> getCotizacionesProveedor(RfqProveedorRequest request) {
        return portalProveedorRepository.getCotizacionesProveedor(request);
    }

    public List<Moneda> getMonedasActivas() {
        return portalProveedorRepository.getMonedasActivas();
    }
    
    public List<UnidadMedida> getUnidadMedidaByIdioma(String idioma) {
        return portalProveedorRepository.getUnidadMedidaByIdioma(idioma);
    }

    public List<OaIvaUenPojo> getIvaByUen(Integer idUen) {
        return portalProveedorRepository.getIvaByUen(idUen);
    }

    public List<NvcTblReqLineaProv> getLineaByRfqAndSupplier(RfqLineaRequest request) {
        return tblReqLineaProvRepository.findRquisByRfqAndSupplier(request.getIdRfq(), request.getIdProveedor());
    }
    
    public String getTipoTransporte_n(Integer id) {
        return portalProveedorRepository.getTipoTransporte_n(id);
    }
    
    public String getTerminosTransporte_n(Integer id) {
        return portalProveedorRepository.getTerminosTransporte_n(id);
    }
}
