package com.example.membrosclube;

public class MembrosClass {

// MembroClass para usar o método getter e setter que usaremos

    Integer id;
    String name;
    String rg;
    String telefone;
    String email;

    public MembrosClass(String name, String rg, String telefone, String email) {
        this.name = name;
        this.rg = rg;
        this.telefone = telefone;
        this.email = email;
    }

    public MembrosClass(Integer id, String name, String rg, String telefone, String email) {
        this.id = id;
        this.name = name;
        this.rg = rg;
        this.telefone = telefone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}