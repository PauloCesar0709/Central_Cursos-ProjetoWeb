/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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
