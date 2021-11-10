package com.metalsa.supplier.pojo;

import java.math.BigInteger;
import lombok.Data;

/**
 *
 * @author mlopez
 */
@Data
public class RfqLineaRequest {
    private Long idProveedor;
    private int idRfq;
    private Long idSucProveedor;
}