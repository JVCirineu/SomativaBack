package com.projetoAvSomativa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoAvSomativa.entities.Curso;
import com.projetoAvSomativa.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name = "Cursos", description = "API REST DE GERNECIAMENTO DE CURSOS AV SOMATIVA PARTE 2")
@RestController
@RequestMapping("/produtos")
public class CursoController {
	private final CursoService cursoService;

	@Autowired
	public CursoController(CursoService cursoService) {
		this.cursoService = cursoService;
	}

	@GetMapping("/{id}")
	@Operation (summary = "Localiza produto por ID")
	public ResponseEntity<Curso> buscaCursoControlId(@PathVariable Long id){
		Curso curso = cursoService.buscaCursoId(id);
		if(curso != null) {
			return ResponseEntity.ok(curso);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation (summary = "Apresenta todos os Cursos")
	public ResponseEntity<List<Curso>> buscaTodosCursoControl(){
		List<Curso> Cursos = cursoService.buscaTodosCursos();
		return ResponseEntity.ok(Cursos);
	}

	@PostMapping
	@Operation (summary = "Cadastra um Curso")
	public ResponseEntity<Curso> salvaCursosControl(@RequestBody @Valid Curso curso){
		Curso salvaCurso = cursoService.salvaCurso(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaCurso);
	}

	@PutMapping("/{id}")
	@Operation (summary = "Altera um Curso")
	public ResponseEntity<Curso> alteraCursoControlId(@PathVariable Long id,@RequestBody @Valid Curso curso ){
		Curso alteraCurso = cursoService.alterarCurso(id, curso);
		if(alteraCurso != null) {
			return ResponseEntity.ok(curso);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation (summary = "Apaga um Curso")
	public ResponseEntity<Curso> apagaCursoControl(@PathVariable Long id){
		boolean apagar = cursoService.apagarCurso(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {	
			return ResponseEntity.notFound().build();
		}
	}
}
