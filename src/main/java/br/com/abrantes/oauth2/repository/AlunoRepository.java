package br.com.abrantes.oauth2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.abrantes.oauth2.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
