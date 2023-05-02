package br.com.estudos.notificationpattern.model.dto.pessoa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PessoaDTO {

    @ApiModelProperty(value = "O id de Pessoa.", required = true)
    private Long id;

    @ApiModelProperty(value = "Nome.")
    private String nome;

    @ApiModelProperty(value = "Telefone.")
    private String telefone;

    @ApiModelProperty(value = "Idade.")
    private Integer idade;

    @ApiModelProperty(value = "Email.")
    private String email;

}
