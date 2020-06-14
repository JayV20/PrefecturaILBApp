package com.example.prefecturailb.common.pojo;

import com.google.firebase.database.Exclude;

public class Materia {
    public static final String ASISTENCIAS="asistencias";
    public static final String EXPRESION="expresion";
    public static final String FALTAS="faltas";
    public static final String RETARDOS="retardos";
    public static final String GRUPO="grupo";
    public static final String VALMAESTRO="valMaestro";
    public static final String VALPREFECTO="valPrefecto";

    private String asistencias;
    private String expresion;
    private String faltas;
    private String grupo;
    private String valMaestro;
    private String valPrefecto;
    @Exclude
    private String retardos;
    @Exclude
    private String nombre;

    public Materia() {

    }

    @Exclude
    public String getNombre() {
        return nombre;
    }
    @Exclude
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(String asistencias) {
        this.asistencias = asistencias;
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    public String getFaltas() {
        return faltas;
    }

    public void setFaltas(String faltas) {
        this.faltas = faltas;
    }
    @Exclude
    public String getRetardos() {
        return retardos;
    }
    @Exclude
    public void setRetardos(String retardos) {
        this.retardos = retardos;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getValMaestro() {
        return valMaestro;
    }

    public void setValMaestro(String valMaestro) {
        this.valMaestro = valMaestro;
    }

    public String getValPrefecto() {
        return valPrefecto;
    }

    public void setValPrefecto(String valPrefecto) {
        this.valPrefecto = valPrefecto;
    }
}
