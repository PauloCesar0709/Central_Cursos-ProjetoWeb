
package com.projeto.centralCursos.controller;

import com.projeto.centralCursos.dto.InscricaoDTO;
import com.projeto.centralCursos.model.Aluno;
import com.projeto.centralCursos.model.Curso;
import com.projeto.centralCursos.model.Inscricao;
import com.projeto.centralCursos.repository.AlunoRepository;
import com.projeto.centralCursos.repository.CursoRepository;
import com.projeto.centralCursos.repository.InscricaoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/inscricoes")
@RequiredArgsConstructor
@CrossOrigin
public class InscricaoController {
    private final AlunoRepository alunoRepo;
    private final CursoRepository cursoRepo;
    private final InscricaoRepository inscricaoRepo;

    @PostMapping
    public Inscricao inscrever(@RequestBody InscricaoDTO dto) {
        Aluno aluno = alunoRepo.findByCpf(dto.getCpf()).orElse(null);
        if (aluno == null) {
            aluno = new Aluno(null, dto.getNome(), dto.getCpf(), dto.getEmail(), dto.getTelefone());
            aluno = alunoRepo.save(aluno);
        }

        if (inscricaoRepo.countByAlunoCpf(aluno.getCpf()) >= 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você já está inscrito em 3 cursos.");
        }

        if (inscricaoRepo.existsByAlunoCpfAndCursoId(aluno.getCpf(), dto.getCursoId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você já está inscrito neste curso.");
        }

        Curso curso = cursoRepo.findById(dto.getCursoId()).orElseThrow();
        Inscricao inscricao = new Inscricao(null, aluno, curso);
        return inscricaoRepo.save(inscricao);
    }

}
