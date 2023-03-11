package br.com.estudos.notificationpattern.model.entity;

import br.com.estudos.notificationpattern.exception.BusinessException;
import br.com.fluentvalidator.context.ValidationResult;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.stream.Collectors;

@Log4j2
@AllArgsConstructor
public abstract class BaseEntity implements Serializable {

    public abstract void validation() throws BusinessException;

    public void validateResult(ValidationResult result) throws BusinessException {
        if (!result.isValid()) {
            var error = result.getErrors()
                            .stream()
                            .map(s -> s.getMessage())
                            .collect(Collectors.toList());

            throw new BusinessException(error);
        }
    }

}
