package com.example.caminata.model;

public class person {
    private String name;
    private String ci;
    private String sexo;
    private String telefono;

    public person(String name, String ci, String sexo, String telefono) {
        this.name = name;
        this.ci = ci;
        this.sexo = sexo;
        this.telefono = telefono;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
