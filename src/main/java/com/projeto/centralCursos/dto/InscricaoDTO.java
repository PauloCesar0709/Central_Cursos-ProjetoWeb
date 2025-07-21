
package com.projeto.centralCursos.dto;

import lombok.Data;

@Data
public class InscricaoDTO {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Long cursoId;

}
