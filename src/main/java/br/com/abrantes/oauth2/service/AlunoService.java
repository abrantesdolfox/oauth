package br.com.abrantes.oauth2.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.abrantes.oauth2.model.Aluno;
import br.com.abrantes.oauth2.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AlunoService {

	private AlunoRepository alunoRepository;
	
	public AlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}
	
	public Page<Aluno> findAllAlunos(Pageable pageable){
		return this.alunoRepository.findAll(pageable);
	}
	
	public Aluno getOneAluno(Long id) {
		return this.alunoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						String.format("Aluno de id: %d não encontrado", id)));
	}
}
