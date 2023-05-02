package br.com.estudos.notificationpattern.service.validation;

import br.com.estudos.notificationpattern.model.entity.Pessoa;
import br.com.fluentvalidator.AbstractValidator;
import org.springframework.stereotype.Service;

import static br.com.fluentvalidator.predicate.ComparablePredicate.greaterThanOrEqual;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringContains;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;

@Service
public class PessoaValidation extends AbstractValidator<Pessoa> {

    @Override
    public void rules() {
        ruleFor(Pessoa::getNome)
                .must((not(stringEmptyOrNull())))
                .withMessage("Nome não pode ser vazio ou nulo!")
                .withAttempedValue(Pessoa::getNome);

        ruleFor(Pessoa::getTelefone)
                .must((not(stringEmptyOrNull())))
                .withMessage("Telefone não pode ser vazio ou nulo!")
                .withAttempedValue(Pessoa::getTelefone);

        ruleFor(Pessoa::getIdade)
                .must(not(nullValue()))
                .withMessage("Idade não pode ser nulo!")
                .must(greaterThanOrEqual(18))
                .withMessage("Deve ser maior de 18 anos!")
                .withAttempedValue(Pessoa::getIdade);

        ruleFor(Pessoa::getEmail)
                .must((not(stringEmptyOrNull())))
                .withMessage("Email não pode ser vazio ou nulo!")
                .must(stringContains("@"))
                .withMessage("O email não tem um formato válido!")
                .must(stringContains(".com").or(stringContains(".com.br")))
                .withMessage("O email deve conter um domínio!")
                .withAttempedValue(Pessoa::getEmail);
    }

}
