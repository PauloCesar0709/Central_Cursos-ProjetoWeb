

package com.projeto.centralCursos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private String area;
    private int duracao;

    @ManyToOne
    @JoinColumn(name = "professor_cpf")
    private Professor professor;
     


}
