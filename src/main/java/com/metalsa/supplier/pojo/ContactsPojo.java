package com.metalsa.supplier.pojo;

import lombok.Data;

@Data
public class ContactsPojo {
    Integer vendor_contact_id;
    String first_name;
    String last_name;
    String email_stop;

    public ContactsPojo() {
    }

    public ContactsPojo(Integer vendor_contact_id, String first_name, String last_name, String email_stop) {
        this.vendor_contact_id = vendor_contact_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email_stop = email_stop;
    }
    
}
