

package com.projeto.centralCursos.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.*;
import com.projeto.centralCursos.model.Inscricao;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    
    @Column(unique = true)
    private String cpf;
    
    private String email;
    private String telefone;
    
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore 
    private List<Inscricao> inscricoes = new ArrayList<>();  
    
}
