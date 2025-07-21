

package com.projeto.centralCursos.controller;

import com.projeto.centralCursos.dto.CursoDTO;
import com.projeto.centralCursos.dto.FormacaoDTO;
import com.projeto.centralCursos.model.Curso;
import com.projeto.centralCursos.model.Formacao;
import com.projeto.centralCursos.model.Professor;
import com.projeto.centralCursos.repository.CursoRepository;
import com.projeto.centralCursos.repository.FormacaoRepository;
import com.projeto.centralCursos.repository.ProfessorRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
@CrossOrigin
public class CursoController {
     private final CursoRepository cursoRepo;
    private final ProfessorRepository professorRepo;
    private final FormacaoRepository formacaoRepo;

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoRepo.findAll();
    }

    @GetMapping("/{id}")
    public Curso buscarPorId(@PathVariable Long id) {
        return cursoRepo.findById(id).orElseThrow();
    }

    @PostMapping
    public Curso cadastrarCurso(@RequestBody CursoDTO dto) {
        Professor professor = professorRepo.findByCpf(dto.getCpfProfessor()).orElse(null);
        if (professor == null) {
            professor = new Professor(null, dto.getNomeProfessor(), dto.getCpfProfessor(), dto.getEmailProfessor(), new ArrayList<>());
            professor = professorRepo.save(professor);
        }

        if (cursoRepo.findByProfessorCpf(professor.getCpf()).size() >= 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Professor já possui 2 cursos.");
        }

        for (FormacaoDTO f : dto.getFormacoes()) {
            Formacao formacao = new Formacao(null, f.getCurso(), f.getInstituicao(), professor);
            formacaoRepo.save(formacao);
        }

        Curso curso = new Curso();
        curso.setNome(dto.getNomeCurso());
        curso.setDescricao(dto.getDescricaoCurso());
        curso.setArea(dto.getAreaCurso());
        curso.setDuracao(dto.getDuracaoCurso());
        curso.setProfessor(professor);
        return cursoRepo.save(curso);
    }

}
