
package com.projeto.centralCursos.model.controller;

import com.centralcursos.model.*;
import com.centralcursos.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
        
@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
public class CursoController {
    
    @Autowired private CursoService cursoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCurso(@RequestBody Curso curso) {
        try {
            Curso novoCurso = cursoService.cadastrarCurso(curso);
            return ResponseEntity.ok(novoCurso);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public List<Curso> listarCursos() {
        return cursoService.listarCursos();
    }
}
}
