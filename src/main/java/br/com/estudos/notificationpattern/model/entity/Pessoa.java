package br.com.estudos.notificationpattern.model.entity;

import br.com.estudos.notificationpattern.exception.BusinessException;
import br.com.estudos.notificationpattern.util.validation.PessoaValidation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Pessoa extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nome;

    @Column
    private String telefone;

    @Column
    private Integer idade;

    @Column
    private String email;

    @Override
    public void validation() throws BusinessException {
        var result = new PessoaValidation();

        validateResult(result.startValidate(this));
    }
}
