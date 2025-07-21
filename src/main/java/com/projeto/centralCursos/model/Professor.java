
package com.projeto.centralCursos.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String cpf;
    private String email;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Formacao> formacoes = new ArrayList<>();
}
