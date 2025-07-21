package com.projeto.centralCursos.controller;

import com.projeto.centralCursos.dto.InscricaoDTO;
import com.projeto.centralCursos.dto.InscricaoResponseDTO;
import com.projeto.centralCursos.model.*;
import com.projeto.centralCursos.repository.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/inscricoes")
@RequiredArgsConstructor
public class InscricaoController {
    private final AlunoRepository alunoRepo;
    private final CursoRepository cursoRepo;
    private final InscricaoRepository inscricaoRepo;

    @PostMapping
    public ResponseEntity<?> inscrever(@Valid @RequestBody InscricaoDTO dto) {
        try {
            // 1. Validação básica
            if (dto.getCursoId() == null) {
                return ResponseEntity.badRequest().body("ID do curso é obrigatório");
            }

            // 2. Busca ou cria o aluno
            Aluno aluno = alunoRepo.findByCpf(dto.getCpf())
                .orElseGet(() -> {
                    Aluno novoAluno = new Aluno();
                    novoAluno.setNome(dto.getNome());
                    novoAluno.setCpf(dto.getCpf());
                    novoAluno.setEmail(dto.getEmail());
                    novoAluno.setTelefone(dto.getTelefone());
                    return alunoRepo.save(novoAluno);
                });

            // 3. Valida regra de negócio (máximo 3 cursos)
            if (inscricaoRepo.countByAlunoCpf(aluno.getCpf()) >= 3) {
                return ResponseEntity.badRequest()
                    .body("Limite de 3 cursos por aluno atingido");
            }

            // 4. Verifica se já está inscrito no curso
            if (inscricaoRepo.existsByAlunoCpfAndCursoId(aluno.getCpf(), dto.getCursoId())) {
                return ResponseEntity.badRequest()
                    .body("Aluno já está inscrito neste curso");
            }

            // 5. Busca o curso
            Curso curso = cursoRepo.findById(dto.getCursoId())
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, 
                    "Curso não encontrado com ID: " + dto.getCursoId()
                ));

            // 6. Cria e salva a inscrição
            Inscricao inscricao = new Inscricao();
            inscricao.setAluno(aluno);
            inscricao.setCurso(curso);
            
            Inscricao inscricaoSalva = inscricaoRepo.save(inscricao);
            
            // 7. Retorna DTO limpo
            return ResponseEntity.ok(new InscricaoResponseDTO(
                inscricaoSalva.getId(),
                aluno.getNome(),
                curso.getNome()
            ));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Erro ao processar inscrição: " + e.getMessage());
        }
    }
}
