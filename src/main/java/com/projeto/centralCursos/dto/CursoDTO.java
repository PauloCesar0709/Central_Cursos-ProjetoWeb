
package com.projeto.centralCursos.dto;

import lombok.Data;
import java.util.List;

@Data
public class CursoDTO {
    private String nomeCurso;
    private String descricaoCurso;
    private String areaCurso;
    private String duracaoCurso;
    private String nomeProfessor;
    private String cpfProfessor;
    private String emailProfessor;
    private List<FormacaoDTO> formacoes;

}
