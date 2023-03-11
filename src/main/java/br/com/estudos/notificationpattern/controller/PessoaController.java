package br.com.estudos.notificationpattern.controller;

import br.com.estudos.notificationpattern.exception.BusinessException;
import br.com.estudos.notificationpattern.model.entity.Pessoa;
import br.com.estudos.notificationpattern.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Controller - Pessoa")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/pessoa", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class PessoaController extends BaseController {

    private final PessoaService pessoaService;

    @PostMapping
    @ApiOperation(value = "Salva uma Pessoa.", notes = "Pessoa")
    public ResponseEntity<?> save(@RequestBody Pessoa dto) throws BusinessException {
//        dto.validation();

        var retorno = pessoaService.save(dto);

        return responseEntity(HttpStatus.CREATED, retorno);
    }

    @GetMapping
    @ApiOperation(value = "Retorna uma Pessoa.", notes = "Pessoa")
    public ResponseEntity<?> find() {
        var retorno = pessoaService.findAll();

        return responseEntity(HttpStatus.OK, retorno);
    }

}
