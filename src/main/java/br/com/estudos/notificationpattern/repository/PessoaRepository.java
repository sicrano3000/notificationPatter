package br.com.estudos.notificationpattern.repository;

import br.com.estudos.notificationpattern.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
