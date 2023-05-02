package br.com.estudos.notificationpattern.service.base;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementação abstrata para a “interface” {@link BaseQueryInterfaceService} para extensão dela nas classes de modo que
 * evite a injeção e sobreescrição do método {@link BaseQueryInterfaceService#getRsqlService()}.
 * <p>
 * Não fazendo a injeção do repositório devido à necessidade de utilização de métodos específicos dentro dos
 * repositories, por isto sendo necessário sobreescrita dentro do serviço concreto.
 */
public abstract non-sealed class BaseQueryService<E> implements BaseQueryInterfaceService<E, Long> {

    @Autowired
    private RsqlService rsqlService;

    @Override
    public RsqlService getRsqlService() {
        return rsqlService;
    }

}