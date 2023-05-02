package br.com.estudos.notificationpattern.model.mapper;

import br.com.estudos.notificationpattern.model.dto.pessoa.AtualizarPessoaDTO;
import br.com.estudos.notificationpattern.model.dto.pessoa.InserirPessoaDTO;
import br.com.estudos.notificationpattern.model.dto.pessoa.PessoaDTO;
import br.com.estudos.notificationpattern.model.entity.Pessoa;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {PessoaMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PessoaMapper {

    Pessoa toEntity(InserirPessoaDTO dto);

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Pessoa toEntity(AtualizarPessoaDTO dto, @MappingTarget Pessoa entity);

    @Mappings({
            @Mapping(source = "id", target = "id")
    })
    PessoaDTO toDTO(Pessoa entity);

}
