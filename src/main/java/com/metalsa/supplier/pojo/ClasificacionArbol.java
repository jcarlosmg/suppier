package com.metalsa.supplier.pojo;

import javax.persistence.Entity;

import lombok.Data;

@Data
public class ClasificacionArbol {

	private Long id_Categoria;
    private Long id_familia;
    private Long id_sub_familia;
    private String palabra;
    
	public ClasificacionArbol(Long id_Categoria, Long id_familia, Long id_sub_familia) {
		
		this.id_Categoria = id_Categoria;
		this.id_familia = id_familia;
		this.id_sub_familia = id_sub_familia;
	}

	public ClasificacionArbol(Long id_Categoria, Long id_familia, Long id_sub_familia, String palabra) {
		
		this.id_Categoria = id_Categoria;
		this.id_familia = id_familia;
		this.id_sub_familia = id_sub_familia;
		this.palabra = palabra;
	}

	public ClasificacionArbol() {
		super();
	}
	
	
    
    
}
