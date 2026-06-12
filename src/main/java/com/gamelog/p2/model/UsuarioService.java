package com.gamelog.p2.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    UsuarioDAO udao;

    public void inserirUsuario(Usuario usu) {
        udao.inserirUsuario(usu);
    }

    public Usuario obterUsuario(int id) {
        return udao.obterUsuario(id);
    }

    public List<Usuario> obterTodosUsuarios() {
        return udao.obterTodosUsuarios();
    }

    public void atualizarUsuario(int id, Usuario novo) {
        udao.atualizarUsuario(id, novo);
    }

    public Usuario obterPorEmail(String email) {
    return udao.obterPorEmail(email);
}

}
