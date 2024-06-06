package com.zenessence.todosimple.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; 
    }

    @GetMapping("/index")
    public String showHomePage() {
        return "index";  
    }

    @GetMapping("/agendamentoPage")
    public String showAgendamentoPage() {
        return "agendamento";  
    }

    @GetMapping("/avaliacaoPage")
    public String showAvaliacaoPage() {
        return "avaliacao"; 
    }

    @GetMapping("/cadastro")
    public String showCadastro() {
        return "cadastro"; 
    }

    @GetMapping("/agendamentoGerencia")
    public String showAgendamentoGerencia() {
        return "agendamentoGerencia"; 
    }

    @GetMapping("/avaliacaoGerencia")
    public String showAvaliacaoGerencia() {
        return "avaliacaoGerencia"; 
    }

    @GetMapping("/gestaoPerformancePage")
    public String showGestao() {
        return "gestaoPerformance"; 
    }

    @GetMapping("/gestaoPerformanceNext")
    public String showGestaoNext() {
        return "gestaoPerformanceNext"; 
    }

    @GetMapping("/cadastroServico")
    public String showCadastroServico() {
        return "cadastroServico"; 
    }

}
