
package com.projeto.centralCursos.repository;

import com.projeto.centralCursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByProfessorCpf(String cpf);

}
