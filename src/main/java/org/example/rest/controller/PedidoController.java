package org.example.rest.controller;

import org.example.domain.entity.ItemPedido;
import org.example.domain.entity.Pedido;
import org.example.domain.enums.StatusPedido;
import org.example.rest.dto.AtualizacaoStatusPedidoDTO;
import org.example.rest.dto.InformacaoItemPedidoDTO;
import org.example.rest.dto.InformacoesPedidoDTO;
import org.example.rest.dto.PedidoDTO;
import org.example.service.PedidoService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;


    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto) {
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }
    @GetMapping("{id}")
    public InformacoesPedidoDTO getById( @PathVariable Integer id ){
        return service
                .obterPedidoCompleto(id)
                .map(this::converter)
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }
    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto) {
        String novoStatus = dto.getNovoStatus();
        service.atualizarStatus(id, StatusPedido.valueOf(novoStatus));
    }


    private InformacoesPedidoDTO converter(Pedido pedido) {
        return InformacoesPedidoDTO.builder().codigo(pedido.getId()).dataPedido(pedido.getDataPedido().
                        format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).cpf(pedido.getCliente().getCpf()).
                nomeCliente(pedido.getCliente().getNome()).total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItens())).build();
    }


    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }
        return itens.stream().map(itemPedido -> InformacaoItemPedidoDTO.builder().
                descricaoProduto(itemPedido.getProduto().getDescricao()).
                precoUnitario(itemPedido.getProduto().getPreco()).quantidade(itemPedido.getQuantidade()).build()
        ).collect(Collectors.toList());
    }
}
