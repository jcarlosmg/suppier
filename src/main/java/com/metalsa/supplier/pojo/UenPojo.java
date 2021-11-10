package com.metalsa.supplier.pojo;

import java.util.List;
import lombok.Data;

@Data
public class UenPojo {
    
    Integer organization_id;
    String organization_name;
    List<LocationUen> locations;
    List<ContactsPojo> contacts;

    public UenPojo() {
    }

    public UenPojo(Integer organization_id, String organization_name) {
        this.organization_id = organization_id;
        this.organization_name = organization_name;
    }

    public UenPojo(Integer organization_id, String organization_name, List<LocationUen> locations) {
        this.organization_id = organization_id;
        this.organization_name = organization_name;
        this.locations = locations;
    }

    public UenPojo(Integer organization_id, String organization_name, List<LocationUen> locations, List<ContactsPojo> contacts) {
        this.organization_id = organization_id;
        this.organization_name = organization_name;
        this.locations = locations;
        this.contacts = contacts;
    }
}
