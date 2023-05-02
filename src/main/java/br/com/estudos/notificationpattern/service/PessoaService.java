package br.com.estudos.notificationpattern.service;

import br.com.estudos.notificationpattern.exception.BusinessException;
import br.com.estudos.notificationpattern.model.dto.pagination.PaginationRequestDTO;
import br.com.estudos.notificationpattern.model.dto.pagination.PaginationResponseDTO;
import br.com.estudos.notificationpattern.model.dto.pessoa.AtualizarPessoaDTO;
import br.com.estudos.notificationpattern.model.dto.pessoa.InserirPessoaDTO;
import br.com.estudos.notificationpattern.model.dto.pessoa.PessoaDTO;
import br.com.estudos.notificationpattern.model.entity.Pessoa;
import br.com.estudos.notificationpattern.model.mapper.PaginationMapper;
import br.com.estudos.notificationpattern.model.mapper.PessoaMapper;
import br.com.estudos.notificationpattern.repository.BaseRepository;
import br.com.estudos.notificationpattern.repository.PessoaRepository;
import br.com.estudos.notificationpattern.service.base.BaseQueryAndValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PessoaService extends BaseQueryAndValidationService<Pessoa> {

    final PessoaRepository pessoaRepository;
    final PessoaMapper pessoaMapper;
    final PaginationMapper<PessoaDTO> paginationMapper;

    @Override
    public BaseRepository<Pessoa, Long> getRepository() {
        return pessoaRepository;
    }

    @Transactional
    public PessoaDTO save(InserirPessoaDTO dto) throws BusinessException {
        var pessoa = pessoaMapper.toEntity(dto);

        return pessoaMapper.toDTO(save(pessoa));
    }

    public PaginationResponseDTO<PessoaDTO> findAllPessoas(PaginationRequestDTO paginationRequestDTO) {
        var pessoas = findAll(paginationRequestDTO)
                .map(pessoaMapper::toDTO);

        return paginationMapper.toDTO(pessoas);
    }

    public PessoaDTO getById(Long id) {
        var pessoa = pessoaRepository.findById(id);

        pessoaIsPresent(pessoa);

        return pessoaMapper.toDTO(pessoa.get());
    }

    public void update(Long id, AtualizarPessoaDTO dto) {
        var pessoa = pessoaRepository.findById(id);

        pessoaIsPresent(pessoa);

        save(pessoaMapper.toEntity(dto, pessoa.get()));
    }

    @Transactional
    public void delete(Long id) {
        var pessoa = pessoaRepository.findById(id);

        pessoaIsPresent(pessoa);

        pessoaRepository.delete(pessoa.get());
    }

    public void pessoaIsPresent(Optional<Pessoa> pessoa) {
        if (!pessoa.isPresent()) {
            addError("Pessoa Não foi encontrada");
            validation();
        }
    }

}

