package com.gamelog.p2.model;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogoService {

    @Autowired
    private JogoDAO jdao;

    public void inserirJogo(Jogo jogo) {
        jdao.inserirJogo(jogo);
    }

    public Jogo obterJogo(int id) {
        return jdao.obterJogo(id);
    }

    // Corrigido para bater com o método atualizado do JogoDAO
    public List<Jogo> obterTodosJogosPorUsuario(int usuarioId) {
        return jdao.obterTodosJogosPorUsuario(usuarioId);
    }

    public void atualizarJogo(int id, Jogo novo) {
        jdao.atualizarJogo(id, novo);
    }

    public void deletarJogo(int id) {
        jdao.deletarJogo(id);
    }
}
