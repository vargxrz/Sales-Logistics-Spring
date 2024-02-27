package org.example.service;

import org.example.domain.entity.Pedido;
import org.example.domain.enums.StatusPedido;
import org.example.domain.repository.Pedidos;
import org.example.rest.dto.PedidoDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizarStatus(Integer id, StatusPedido statusPedido);

}
