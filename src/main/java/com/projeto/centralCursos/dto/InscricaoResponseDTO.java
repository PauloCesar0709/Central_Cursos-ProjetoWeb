package com.projeto.centralCursos.dto;

import lombok.Data;

@Data
public class InscricaoResponseDTO {
    private Long id;
    private String nomeAluno;
    private String nomeCurso;

    public InscricaoResponseDTO(Long id, String nomeAluno, String nomeCurso) {
        this.id = id;
        this.nomeAluno = nomeAluno;
        this.nomeCurso = nomeCurso;
    }
}
