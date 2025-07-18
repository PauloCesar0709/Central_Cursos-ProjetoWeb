
package com.projeto.centralCursos.model.service;

import com.centralcursos.model.*;
import com.centralcursos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoService {
      @Autowired private CursoRepository cursoRepo;
      @Autowired private ProfessorRepository professorRepo;

      public Curso cadastrarCurso(Curso curso) throws Exception {
        Professor professor = professorRepo.findById(curso.getProfessor().getCpf())
            .orElseThrow(() -> new Exception("Professor não encontrado"));

        if (professor.getCursos().size() >= 2) {
            throw new Exception("O professor já cadastrou 2 cursos.");
        }

        curso.setProfessor(professor);
        return cursoRepo.save(curso);
    }

      public List<Curso> listarCursos() {
        return cursoRepo.findAll();
    }
}

}
