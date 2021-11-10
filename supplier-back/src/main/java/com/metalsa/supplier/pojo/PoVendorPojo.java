package com.metalsa.supplier.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class PoVendorPojo implements Serializable {
    @Id
    private Integer vendorId;
    private String vendorName;
    @Transient
    private String idioma;
}
