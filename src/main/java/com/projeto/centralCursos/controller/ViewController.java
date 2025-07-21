
package com.projeto.centralCursos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/aluno")
    public String aluno() {
        return "aluno";
    }
    
    @GetMapping("/professor")
    public String professor() {
        return "professor";
    }
    
}
