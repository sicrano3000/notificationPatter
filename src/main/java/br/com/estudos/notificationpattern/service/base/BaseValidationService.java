package br.com.estudos.notificationpattern.service.base;

import br.com.fluentvalidator.AbstractValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * Serviço que trabalha em cima das validações das entidades e adição dos erros dentro do serviço de validação, de modo
 * a abstrair a responsabilidade de injeção dos serviços de validação tanto de entidades quanto regras de negócio. Visto
 * seu foco exclusivo neste gerenciamento, não contempla questões de queries no banco de dados, para tal utilize a
 * abstração {@link BaseQueryAndValidationService}
 */
public sealed abstract class BaseValidationService<E> permits BaseQueryAndValidationService {

    @Autowired
    private AbstractValidator<E> abstractValidation;
    @Autowired
    private ValidationService validationService;

    /**
     * Valida entidade com base na especificação da entidade que o serviço visa gerenciar a persistência em banco de dados
     *
     * @throws br.com.estudos.notificationpattern.exception.BusinessException caso exista erros nas validações das regras de
     * entidades e/ou nas regras de negócio adicionadas previamente pelo método {@link BaseValidationService#addError(String)}
     */
    public void validation(E obj) {
        if (Objects.isNull(obj)) {
            validation();
            return;
        }
        final var validationResult = abstractValidation.validate(obj);
        validationService.validateResult(validationResult);
    }

    /**
     * Como era comumente a utilização do método {@link BaseValidationService#validation(E)} com a passagem de null,
     * existe esta sobreescrição do método para realizar a validação de modo a não precisar da pasagem de valores.
     *
     * @throws br.com.estudos.notificationpattern.exception.BusinessException caso exista erros nas validações das regras de
     * entidades e/ou nas regras de negócio adicionadas previamente pelo método {@link BaseValidationService#addError(String)}
     */
    public void validation() {
        validationService.validateResult();
    }

    /**
     * Método para passagem dos erros nas regras de negócio, mantendo implementação prévia das regras de negócio.
     *
     * @param erros Mensagem de erro que deve ser apresentada nos casos de erro de negócio.
     */
    public void addError(String erros) {
        validationService.addError(erros);
    }

}