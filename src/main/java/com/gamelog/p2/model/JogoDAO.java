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
public class JogoDAO {

    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirJogo(Jogo jogo) {
        String sql = "INSERT INTO Jogo(nomeJogo, plataforma, descricao, nota, estadojogo, usuario_id) VALUES (?,?,?,?,?,?)";
        Object[] obj = new Object[6];
        obj[0] = jogo.getNomeJogo();
        obj[1] = jogo.getPlataforma();
        obj[2] = jogo.getDescricao();
        obj[3] = jogo.getNota();
        obj[4] = jogo.getEstadoJogo() != null ? jogo.getEstadoJogo().name() : null;
        obj[5] = jogo.getUsuarioId();
        jdbc.update(sql, obj);
    }

    public void atualizarJogo(int id, Jogo novo) {
        String sql = "UPDATE jogo SET nomeJogo = ?, plataforma = ?, descricao = ?, nota = ?, estadojogo = ? WHERE id = ?";
        Object[] obj = new Object[6];
        obj[0] = novo.getNomeJogo();
        obj[1] = novo.getPlataforma();
        obj[2] = novo.getDescricao();
        obj[3] = novo.getNota();
        obj[4] = novo.getEstadoJogo() != null ? novo.getEstadoJogo().name() : null;
        obj[5] = id;
        jdbc.update(sql, obj);
    }

    public List<Jogo> obterTodosJogosPorUsuario(int usuarioId) {
        String sql = "SELECT * FROM jogo WHERE usuario_id = ?";
        List<Map<String, Object>> listaRegistros = jdbc.queryForList(sql, usuarioId);
        List<Jogo> aux = new ArrayList<>();

        for (Map<String, Object> registro : listaRegistros) {
            aux.add(Jogo.converterRegistros(new HashMap<>(registro)));
        }
        return aux;
    }

    public Jogo obterJogo(int id) {
        String sql = "SELECT * FROM jogo WHERE id=?";
        try {
            Map<String, Object> resultado = jdbc.queryForMap(sql, id);
            return Jogo.converterRegistros(new HashMap<>(resultado));
        } catch (Exception e) {
            return null;
        }
    }

    public void deletarJogo(int id) {
        String sql = "DELETE FROM jogo WHERE id = ?";
        jdbc.update(sql, id);
    }
}
