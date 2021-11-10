package com.metalsa.supplier.pojo;

public class Token {

    private String idUsuario;
    private String nombreUsuario;
    private Integer idRol;
    private String idioma;
    private String idIdioma;
    private String vistaSeleccion;
    private String vistaAprobacion;

    public Token(){};
    
    public Token(String idUsuario, String nombreUsuario, Integer idRol, String idioma, String idIdioma, String vistaSeleccion, String vistaAprobacion){
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.idRol = idRol;
        this.idioma = idioma;
        this.idIdioma = idIdioma;
        this.vistaSeleccion = vistaSeleccion;
        this.vistaAprobacion = vistaAprobacion;
    };
    
    /**
     * @return the idUsuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the idRol
     */
    public Integer getIdRol() {
        return idRol;
    }

    /**
     * @param idRol the idRol to set
     */
    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(String idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getVistaSeleccion() {
        return vistaSeleccion;
    }

    public void setVistaSeleccion(String vistaSeleccion) {
        this.vistaSeleccion = vistaSeleccion;
    }

    public String getVistaAprobacion() {
        return vistaAprobacion;
    }

    public void setVistaAprobacion(String vistaAprobacion) {
        this.vistaAprobacion = vistaAprobacion;
    }

}
