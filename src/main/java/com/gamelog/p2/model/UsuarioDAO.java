package com.gamelog.p2.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class UsuarioDAO {
    @Autowired
    DataSource dataSource;

    private JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirUsuario(Usuario usu) {
        String sql = "INSERT INTO usuario(nome,email,senha) VALUES (?,?,?)";
        Object[] obj = new Object[3];
        obj[0] = usu.getNome();
        obj[1] = usu.getEmail();
        obj[2] = usu.getSenha();
        jdbc.update(sql, obj);
    }

    public void atualizarUsuario(int id, Usuario novo) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";
        Object[] obj = new Object[4];
        obj[0] = novo.getNome();
        obj[1] = novo.getEmail();
        obj[2] = novo.getSenha();
        obj[3] = id;
        jdbc.update(sql, obj);
    }


    public Usuario obterUsuario(int id) {
        String sql = "SELECT * FROM usuario WHERE id=?";

        Map<String, Object> resultado = jdbc.queryForMap(sql, id);
        return Usuario.converterRegistros(new HashMap<>(resultado));
    }

    public List<Usuario> obterTodosUsuarios() {
        String sql = "SELECT * FROM usuario";
        List<Map<String, Object>> listaRegistros = jdbc.queryForList(sql);
        List<Usuario> aux = new ArrayList<>();

        for (Map<String, Object> registro : listaRegistros) {
            aux.add(Usuario.converterRegistros(new HashMap<>(registro)));
        }

        return aux;
    }

    public Usuario obterPorEmail(String email) {
    String sql = "SELECT * FROM usuario WHERE email = ?";
    try {
        Map<String, Object> resultado = jdbc.queryForMap(sql, email);
        return Usuario.converterRegistros(new HashMap<>(resultado));
    } catch (Exception e) {

        return null;
    }
}
}
