

package com.projeto.centralCursos.repository;

import com.projeto.centralCursos.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
     int countByAlunoCpf(String cpf);
     boolean existsByAlunoCpfAndCursoId(String cpf, Long cursoId);

}
