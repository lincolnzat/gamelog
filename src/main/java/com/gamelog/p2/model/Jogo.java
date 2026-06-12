package com.gamelog.p2.model;

import java.util.HashMap;

public class Jogo {

    private int id;
    private String nomeJogo, plataforma, descricao;
    private double nota;
    private EstadoJogo estadojogo;
    private int usuarioId;

    public Jogo() {
    }

    public Jogo(String nomeJogo, String plataforma, String descricao, double nota, EstadoJogo estadojogo, int usuarioId) {
        this.nomeJogo = nomeJogo;
        this.plataforma = plataforma;
        this.descricao = descricao;
        this.nota = nota;
        this.estadojogo = estadojogo;
        this.usuarioId = usuarioId;
    }

    public Jogo(int id, String nomeJogo, String plataforma, String descricao, double nota, EstadoJogo estadojogo, int usuarioId) {
        this.id = id;
        this.nomeJogo = nomeJogo;
        this.plataforma = plataforma;
        this.descricao = descricao;
        this.nota = nota;
        this.estadojogo = estadojogo;
        this.usuarioId = usuarioId;
    }

    public int getId() {
        return id;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getNota() {
        return nota;
    }

    public EstadoJogo getEstadoJogo() {
        return estadojogo;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public void setEstadoJogo(EstadoJogo estadojogo) {
        this.estadojogo = estadojogo;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public static Jogo converterRegistros(HashMap<String, Object> registros) {

        int idJogo = (int) registros.get("id");
        String nome = (String) registros.get("nomejogo");
        String plat = (String) registros.get("plataforma");
        String desc = (String) registros.get("descricao");

        double notaJogo = Double.parseDouble(registros.get("nota").toString());

        String estadoStr = (String) registros.get("estadojogo");
        EstadoJogo est = estadoStr != null ? EstadoJogo.valueOf(estadoStr) : null;

        int usuId = (int) registros.get("usuario_id");

        return new Jogo(idJogo, nome, plat, desc, notaJogo, est, usuId);
    }
}
