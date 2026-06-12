package com.gamelog.p2.controller;

import com.gamelog.p2.model.Usuario;
import com.gamelog.p2.model.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }


    @PostMapping("/cadastro")
    public String salvarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.inserirUsuario(usuario);


        return "redirect:/";
    }

@GetMapping("/login")
public String mostrarPaginaLogin() {
    return "entrar";
}

@PostMapping("/login")
public String login(@RequestParam("email") String email,
                    @RequestParam("senha") String senha,
                    HttpSession session,
                    Model model) {

    Usuario usuario = usuarioService.obterPorEmail(email);

    if (usuario != null && usuario.getSenha().equals(senha)) {
        session.setAttribute("usuarioLogado", usuario);
        return "redirect:/jogos";
    }

    model.addAttribute("erro", "E-mail ou senha inválidos!");
    return "entrar";
}

@GetMapping("/perfil")
public String perfil(HttpSession session, Model model) {
    Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

    if (usuarioLogado == null) {
        return "redirect:/";
    }

    model.addAttribute("usuario", usuarioLogado);
    return "pagUser";
}

@GetMapping("/logout")
public String logout(HttpSession session) {

    session.invalidate();

    return "redirect:/";
}
}
