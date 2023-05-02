package br.com.estudos.notificationpattern.service.base;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Implementação base das interfaces {@link BaseValidationService} e {@link BaseQueryInterfaceService} para utilização
 * dos sistemas de validação de regras de negócio, entidades e sistemas básicos de query dos sistemas, com {@link RsqlService}
 * <p>
 * É necessária a implementação do service de validação fazendo uma extensão da classe {@link br.com.fluentvalidator.AbstractValidator}, tal como {@link br.com.estudos.notificationpattern.service.validation.PessoaValidation}
 * <p>
 * Deve se observar que os métodos padrões de persistência dos objetos no banco de dados são sobreescritos de modo a
 * sempre fazer a validação das entidades previamente a query no banco de dados
 *
 * @param <E> Entidade a ser gerenciada, sendo para validações e/ou gestão no banco de dados
 */
public abstract non-sealed class BaseQueryAndValidationService<E> extends BaseValidationService<E> implements BaseQueryInterfaceService<E, Long> {

    @Autowired
    private RsqlService rsqlService;

    @Override
    public RsqlService getRsqlService() {
        return rsqlService;
    }

    public E validateAndSave(E entity) {
        validation(entity);
        return BaseQueryInterfaceService.super.save(entity);
    }

    public E noValidateAndSave(E entity) {
        return BaseQueryInterfaceService.super.save(entity);
    }

    @Override
    public E save(E entity) {
        return validateAndSave(entity);
    }

    @Override
    public List<E> saveAll(List<E> entities) {
        entities.forEach(entity -> {
            validation(entity);
        });

        return getRepository().saveAll(entities);
    }

}