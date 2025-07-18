
package com.projeto.centralCursos.model.controller;

import com.centralcursos.model.*;
import com.centralcursos.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alunos")
@CrossOrigin(origins = "*")
public class AlunoController {
    @Autowired private AlunoService alunoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarAluno(@RequestBody Aluno aluno) {
        return ResponseEntity.ok(alunoService.cadastrarAluno(aluno));
    }

    @PostMapping("/inscrever/{cpf}/{cursoId}")
    public ResponseEntity<?> inscreverCurso(@PathVariable String cpf, @PathVariable Long cursoId) {
        try {
            Aluno alunoAtualizado = alunoService.inscreverCurso(cpf, cursoId);
            return ResponseEntity.ok(alunoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

}
