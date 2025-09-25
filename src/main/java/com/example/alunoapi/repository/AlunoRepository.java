package com.example.alunoapi.repository;

import com.example.alunoapi.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
    // Métodos de busca personalizados
    List<Aluno> findByNomeContainingIgnoreCase(String nome);
    List<Aluno> findByCursoContainingIgnoreCase(String curso);
    List<Aluno> findByNomeContainingIgnoreCaseOrCursoContainingIgnoreCase(String nome, String curso);
}
