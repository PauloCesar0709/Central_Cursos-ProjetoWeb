

package com.projeto.centralCursos.repository;

import com.projeto.centralCursos.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByCpf(String cpf);

}
