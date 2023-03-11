package br.com.estudos.notificationpattern.service;

import br.com.estudos.notificationpattern.exception.BusinessException;
import br.com.estudos.notificationpattern.model.entity.Pessoa;
import br.com.estudos.notificationpattern.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa save(Pessoa pessoa) throws BusinessException {
        pessoa.validation();

        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

}
