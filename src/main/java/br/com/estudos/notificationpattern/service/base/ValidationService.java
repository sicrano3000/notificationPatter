package br.com.estudos.notificationpattern.service.base;

import br.com.fluentvalidator.context.Error;
import br.com.fluentvalidator.context.ValidationResult;
import br.com.estudos.notificationpattern.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Serviço de validação das regras de negócio, previamente implementado em cima de uma abstração que utilizava herança,
 * fora abstraido para um serviço de modo que possa ser injetado a cada request, removendo a necessidade de tratamento
 * da lista de erros e também para evitar conflitos entre theads.
 * <p>
 * O Serviço utiliza do fluent validation {@link br.com.fluentvalidator.AbstractValidator} para pegar os resultados e
 * tratá-los com erros de regra de negócio advindos da adição no serviço (pelo método {@link ValidationService#addError(String)}).
 * <p>
 * Injetado na classe abstrata {@link BaseQueryAndValidationService}
 */
@Service
@RequestScope
public class ValidationService {
    private final Collection<Error> errors = new LinkedList<>();

    /**
     * Método para tratamento dos erros das validações das entidades e erros dos serviços (regras de negócio)
     *
     * @param result resultados das validações advindas das implementações do {@link br.com.fluentvalidator.AbstractValidator#validate(Object)}
     */
    public void validateResult(ValidationResult result) {
        if (Objects.isNull(result)) {
            validateResult();
            return;
        }

        if (result.isValid() && errors.isEmpty()) {
            return;
        }

        final var serviceErros = errors.stream()
                .map(Error::getMessage);

        final var entityErrors = result.getErrors()
                .stream()
                .map(Error::getMessage);

        final var allErros = Stream.concat(serviceErros, entityErrors).collect(Collectors.toSet());

        throw new BusinessException(allErros);
    }

    /**
     * Método para tratamento de erros dos serviços (regras de negócio) adicionados através do método {@link ValidationService#addError(String)}
     */
    void validateResult() {
        if (errors.isEmpty()) {
            return;
        }

        final var serviceErros = errors.stream()
                .map(Error::getMessage)
                .collect(Collectors.toSet());

        throw new BusinessException(serviceErros);
    }

    /**
     * Método para adição de erros a serem apresentados para o utilizador da API.
     *
     * @param erros erros das regras de negócio dos serviços
     */
    public void addError(String erros) {
        errors.add(Error.create("", erros, "", null));
    }

}
