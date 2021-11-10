package com.metalsa.supplier.pojo;

import lombok.Data;

@Data
public class AdjuntoPojo {

    private String nombreArchivo;
    private Long peso;
    private String mime;
    private String description;
    private byte[] file;
    private String token;


    public byte[] getFile() {
        return file == null ? null : (byte[]) file.clone();
    }
    
    public void setFile(byte[] file) {
        if(null == file){
            this.file = null;
        }else{
            this.file = (byte[]) file.clone();
        }
    }
    
}
