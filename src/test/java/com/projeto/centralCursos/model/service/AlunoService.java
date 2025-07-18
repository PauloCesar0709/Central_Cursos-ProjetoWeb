
package com.projeto.centralCursos.model.service;

import com.centralcursos.model.*;
import com.centralcursos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlunoService {
    @Autowired private AlunoRepository alunoRepo;
    @Autowired private CursoRepository cursoRepo;

    public Aluno inscreverCurso(String cpf, Long cursoId) throws Exception {
        Aluno aluno = alunoRepo.findById(cpf)
            .orElseThrow(() -> new Exception("Aluno não encontrado"));

        if (aluno.getCursos().size() >= 3) {
            throw new Exception("O aluno já está inscrito em 3 cursos.");
        }

        Curso curso = cursoRepo.findById(cursoId)
            .orElseThrow(() -> new Exception("Curso não encontrado"));

        aluno.getCursos().add(curso);
        return alunoRepo.save(aluno);
    }

    public Aluno cadastrarAluno(Aluno aluno) {
        return alunoRepo.save(aluno);
    }
}

}
