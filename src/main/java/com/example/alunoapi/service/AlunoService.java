package com.example.alunoapi.service;

import com.example.alunoapi.model.Aluno;
import com.example.alunoapi.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    // Listar todos os alunos
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }
    
    // Buscar aluno por ID
    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }
    
    // Salvar novo aluno
    public Aluno salvar(Aluno aluno) {
        validarAluno(aluno);
        return alunoRepository.save(aluno);
    }
    
    // Atualizar aluno existente
    public Aluno atualizar(Long id, Aluno alunoAtualizado) {
        Optional<Aluno> alunoExistente = alunoRepository.findById(id);
        if (alunoExistente.isPresent()) {
            validarAluno(alunoAtualizado);
            Aluno aluno = alunoExistente.get();
            aluno.setNome(alunoAtualizado.getNome());
            aluno.setCurso(alunoAtualizado.getCurso());
            aluno.setTelefone(alunoAtualizado.getTelefone());
            return alunoRepository.save(aluno);
        }
        return null;
    }
    
    // Deletar aluno
    public boolean deletar(Long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Buscar alunos por nome
    public List<Aluno> buscarPorNome(String nome) {
        return alunoRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    // Buscar alunos por curso
    public List<Aluno> buscarPorCurso(String curso) {
        return alunoRepository.findByCursoContainingIgnoreCase(curso);
    }
    
    // Buscar alunos por nome ou curso
    public List<Aluno> buscarPorNomeOuCurso(String termo) {
        return alunoRepository.findByNomeContainingIgnoreCaseOrCursoContainingIgnoreCase(termo, termo);
    }

    // Método privado para validações adicionais
    private void validarAluno(Aluno aluno) {
        if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        
        if (aluno.getNome().trim().length() <= 2) {
            throw new IllegalArgumentException("Nome deve ter mais de 2 caracteres");
        }
        
        if (aluno.getCurso() == null || aluno.getCurso().trim().isEmpty()) {
            throw new IllegalArgumentException("Curso é obrigatório");
        }
        
        if (aluno.getCurso().trim().length() <= 2) {
            throw new IllegalArgumentException("Curso deve ter mais de 2 caracteres");
        }
        
        // Validação opcional para telefone (se fornecido)
        if (aluno.getTelefone() != null && !aluno.getTelefone().trim().isEmpty()) {
            String telefone = aluno.getTelefone().replaceAll("[^0-9]", "");
            if (telefone.length() < 10 || telefone.length() > 11) {
                throw new IllegalArgumentException("Telefone deve ter entre 10 e 11 dígitos");
            }
        }
    }
}
