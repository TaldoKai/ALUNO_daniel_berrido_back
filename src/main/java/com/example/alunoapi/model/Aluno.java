package com.example.alunoapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alunos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, message = "Nome deve ter mais de 2 caracteres")
    @Column(nullable = false)
    private String nome;
    
    @NotBlank(message = "Curso é obrigatório")
    @Size(min = 3, message = "Curso deve ter mais de 2 caracteres")
    @Column(nullable = false)
    private String curso;
    
    @Column
    private String telefone;
}
