package org.example.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.validation.NotEmptyList;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    @NotNull(message = "Informe o codigo do cliente.")
    private Integer cliente;

    @NotNull(message = "O campo total do pedido é obrigatorio.")
    private BigDecimal total;

    @NotEmptyList(message = "Pedido nao pode ser realizado sem itens.")
    private List<ItemPedidoDTO> items;

}
