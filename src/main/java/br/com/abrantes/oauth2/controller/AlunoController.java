package br.com.abrantes.oauth2.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.abrantes.oauth2.model.Aluno;
import br.com.abrantes.oauth2.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
	
	private AlunoService alunoService;
	
	public AlunoController(AlunoService alunoService) {
		this.alunoService = alunoService;
	}
	
	@GetMapping
	public ResponseEntity<Page<Aluno>> findAllAlunos(@PageableDefault(
			page = 0, size = 10, sort = "id",
			direction = Sort.Direction.ASC) Pageable pageable){
		
		var listAlunos = this.alunoService.findAllAlunos(pageable);
		
		if(!listAlunos.isEmpty()) {
			for(Aluno aluno : listAlunos) {
				Long id = aluno.getId();
				aluno.add(linkTo(methodOn(AlunoController.class).getOneAluno(id)).withSelfRel());
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listAlunos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> getOneAluno(@PathVariable Long id){
		
		var aluno = this.alunoService.getOneAluno(id);
		
		aluno.add(linkTo(methodOn(AlunoController.class).findAllAlunos(null)).withRel("Lista de alunos"));
		
		return ResponseEntity.status(HttpStatus.OK).body(aluno);
	}

}
