package com.example.alunoapi.controller;

import com.example.alunoapi.model.Aluno;
import com.example.alunoapi.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alunos")
@CrossOrigin(origins = "http://localhost:4200")
public class AlunoController {
    
    @Autowired
    private AlunoService alunoService;
    
    // GET /api/alunos - Listar todos os alunos
    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodos() {
        List<Aluno> alunos = alunoService.listarTodos();
        return ResponseEntity.ok(alunos);
    }
    
    // GET /api/alunos/{id} - Buscar aluno por ID
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoService.buscarPorId(id);
        if (aluno.isPresent()) {
            return ResponseEntity.ok(aluno.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    // POST /api/alunos - Criar novo aluno
    @PostMapping
    public ResponseEntity<Aluno> criar(@Valid @RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.salvar(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }
    
    // PUT /api/alunos/{id} - Atualizar aluno existente
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @Valid @RequestBody Aluno aluno) {
        Aluno alunoAtualizado = alunoService.atualizar(id, aluno);
        if (alunoAtualizado != null) {
            return ResponseEntity.ok(alunoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }
    
    // DELETE /api/alunos/{id} - Deletar aluno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = alunoService.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    // GET /api/alunos/buscar?nome={nome} - Buscar alunos por nome
    @GetMapping("/buscar")
    public ResponseEntity<List<Aluno>> buscarPorNome(@RequestParam(required = false) String nome,
                                                     @RequestParam(required = false) String curso,
                                                     @RequestParam(required = false) String termo) {
        List<Aluno> alunos;
        
        if (termo != null && !termo.isEmpty()) {
            alunos = alunoService.buscarPorNomeOuCurso(termo);
        } else if (nome != null && !nome.isEmpty()) {
            alunos = alunoService.buscarPorNome(nome);
        } else if (curso != null && !curso.isEmpty()) {
            alunos = alunoService.buscarPorCurso(curso);
        } else {
            alunos = alunoService.listarTodos();
        }
        
        return ResponseEntity.ok(alunos);
    }
}
