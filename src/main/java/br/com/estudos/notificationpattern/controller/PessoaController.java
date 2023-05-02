package br.com.estudos.notificationpattern.controller;

import br.com.estudos.notificationpattern.exception.BusinessException;
import br.com.estudos.notificationpattern.exception.Response;
import br.com.estudos.notificationpattern.model.dto.pagination.PaginationRequestDTO;
import br.com.estudos.notificationpattern.model.dto.pagination.PaginationResponseDTO;
import br.com.estudos.notificationpattern.model.dto.pessoa.AtualizarPessoaDTO;
import br.com.estudos.notificationpattern.model.dto.pessoa.InserirPessoaDTO;
import br.com.estudos.notificationpattern.model.dto.pessoa.PessoaDTO;
import br.com.estudos.notificationpattern.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Response<PessoaDTO>> save(final @RequestBody InserirPessoaDTO dto) throws BusinessException {
        return responseCreated(pessoaService.save(dto));
    }

    @GetMapping
    @ApiOperation(value = "Retorna uma Pessoa.", notes = "Pessoa")
    public ResponseEntity<Response<PaginationResponseDTO<PessoaDTO>>> findAll(@RequestParam(value = "query", required = false) String query,
                                                                              @RequestParam(value = "pagesize", defaultValue = "10", required = false) Integer pageSize,
                                                                              @RequestParam(value = "pageindex", defaultValue = "0", required = false) Integer pageIndex,
                                                                              @RequestParam(value = "sort", defaultValue = "id, ASC", required = false) String sort) {
        var retorno = pessoaService.findAllPessoas(PaginationRequestDTO.of(query, pageSize, pageIndex, sort));

        return responseOk(retorno);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna uma Pessoa pelo ID.", notes = "Pessoa")
    public ResponseEntity<Response<PessoaDTO>> findById(final @PathVariable Long id) {

        var retorno = pessoaService.getById(id);
        return responseOk(retorno);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Atualiza uma Pessoa.", notes = "Pessoa")
    public ResponseEntity<Response<Void>> update(final @PathVariable Long id,
                                                 final @RequestBody AtualizarPessoaDTO dto) {
        pessoaService.update(id, dto);
        return responseNoContent();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta uma Pessoa.", notes = "Pessoa")
    public ResponseEntity<Response<Void>> delete(final @PathVariable Long id) {
        pessoaService.delete(id);

        return responseNoContent();
    }

}
