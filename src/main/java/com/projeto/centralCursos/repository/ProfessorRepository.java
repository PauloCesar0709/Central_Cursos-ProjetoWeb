

package com.projeto.centralCursos.repository;

import com.projeto.centralCursos.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
     Optional<Professor> findByCpf(String cpf);

}
