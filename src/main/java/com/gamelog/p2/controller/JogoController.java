package com.gamelog.p2.controller;

import java.util.List;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.gamelog.p2.model.Jogo;
import com.gamelog.p2.model.EstadoJogo;
import com.gamelog.p2.model.Usuario;
import com.gamelog.p2.model.JogoService;

@Controller
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoService jogoService;

    // 1. LISTAR OS JOGOS
    @GetMapping
    public String listarJogos(HttpSession session, Model model) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            return "redirect:/login";
        }

        List<Jogo> lista = jogoService.obterTodosJogosPorUsuario(usuarioLogado.getId());

        model.addAttribute("listaJogos", lista);
        model.addAttribute("estados", EstadoJogo.values());
        model.addAttribute("jogo", new Jogo());
        model.addAttribute("usuario", usuarioLogado);

        return "pagUser";
    }

    // 2. INSERIR NOVO JOGO
    @PostMapping("/novo")
    public String inserirJogo(@ModelAttribute Jogo jogo, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            return "redirect:/login";
        }

        jogo.setUsuarioId(usuarioLogado.getId());
        jogoService.inserirJogo(jogo);

        return "redirect:/jogos";
    }

    // 3. CARREGAR DADOS PARA EDITAR
    @GetMapping("/editar/{id}")
    public String exibirEdicao(@PathVariable("id") int id, HttpSession session, Model model) { // Corrigida a assinatura
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            return "redirect:/login";
        }

        Jogo jogo = jogoService.obterJogo(id);

        if (jogo == null || jogo.getUsuarioId() != usuarioLogado.getId()) {
            return "redirect:/jogos";
        }

        model.addAttribute("jogo", jogo);
        model.addAttribute("estados", EstadoJogo.values());
        return "jogos-editar";
    }

    // 4. ATUALIZAR JOGO
    @PostMapping("/atualizar/{id}")
    public String atualizarJogo(@PathVariable("id") int id, @ModelAttribute Jogo jogo, HttpSession session) { // Corrigidas as vírgulas
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            return "redirect:/login";
        }

        Jogo jogoExistente = jogoService.obterJogo(id);
        if (jogoExistente != null && jogoExistente.getUsuarioId() == usuarioLogado.getId()) {
            jogoService.atualizarJogo(id, jogo);
        }

        return "redirect:/jogos";
    }

    // 5. DELETAR JOGO
    @GetMapping("/deletar/{id}")
    public String deletarJogo(@PathVariable("id") int id, HttpSession session) { // Corrigida a assinatura
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            return "redirect:/login";
        }

        Jogo jogo = jogoService.obterJogo(id);
        if (jogo != null && jogo.getUsuarioId() == usuarioLogado.getId()) {
            jogoService.deletarJogo(id);
        }

        return "redirect:/jogos";
    }
}
