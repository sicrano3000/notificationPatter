package br.com.estudos.notificationpattern.service.base;

import br.com.estudos.notificationpattern.model.dto.pagination.PaginationRequestDTO;
import br.com.estudos.notificationpattern.repository.BaseRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

/**
 * Classe para utilização de métodos padrões de consulta ao banco de dados, estrutura básica fornecida pela antiga
 * classe BaseService. Em essência possúi tudo que era utilizado, entretanto, necessita da injeção do
 * {@link br.com.estudos.notificationpattern.repository.BaseRepository}, e do {@link br.com.estudos.notificationpattern.service.base.RsqlService}
 * <p>
 * Recomenda-se a utilização do {@link br.com.estudos.notificationpattern.service.base.BaseQueryService} caso intuito seja apenas
 * realização de queries no banco de dados
 */
public sealed interface BaseQueryInterfaceService<E, ID> permits BaseQueryAndValidationService, BaseQueryService {


    /**
     * Método para implementação da captura do repositório, como o {@link BaseQueryInterfaceService} é uma interface,
     * não é possível a utilização de injeção automática dos serviços para tal fora feito estes métodos para que a
     * implementação default não tenha problemas
     */
    BaseRepository<E, ID> getRepository();

    /**
     * Método para implementação da captura do repositório, como o {@link BaseQueryInterfaceService} é uma interface,
     * não é possível a utilização de injeção automática dos serviços para tal fora feito estes métodos para que a
     * implementação default não tenha problemas
     */
    RsqlService getRsqlService();

    private PageRequest paginationToPageRequest(PaginationRequestDTO paginationRequestDTO) {
        if (Objects.isNull(paginationRequestDTO)) {
            return PageRequest.of(0, 10);
        }

        Integer pageIndex = Objects.requireNonNullElse(paginationRequestDTO.getPageIndex(), 0);
        Integer pageSize = Objects.requireNonNullElse(paginationRequestDTO.getPageSize(), 10);
        return PageRequest.of(pageIndex, pageSize);
    }

    default Page<E> findAll(PaginationRequestDTO paginationRequestDTO) {
        try {
            final var pageRequest = paginationToPageRequest(paginationRequestDTO);
            final var spec = getRsqlService().<E>getPaginationSpecification(paginationRequestDTO);

            return getRepository().findAll(spec, pageRequest);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("Erro ao realizar busca paginada. Verifique a query enviada: " + paginationRequestDTO.getQuery(), e);
        }
    }

    default E findByIdOrThrowNotFoundException(ID id) {
        return getRepository()
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidade com o id = " + id + " não encontrada!"));
    }

    default E save(E entity) {
        return getRepository().save(entity);
    }

    default List<E> saveAll(List<E> entities) {
        return getRepository().saveAll(entities);
    }

}