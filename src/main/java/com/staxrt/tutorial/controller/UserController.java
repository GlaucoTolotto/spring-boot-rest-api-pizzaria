package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Pedido;
import com.staxrt.tutorial.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PedidoController {

  @Autowired
  private PedidoRepository pedidoRepository;

  @GetMapping("/pedidos")
  public List<Pedido> getAllPedidos() {
    return pedidoRepository.findAll();
  }

  /**
   * Gets pedido by id.
   *
   * @param pedidoId the pedido id
   * @return the pedido by id
   * @throws ResourceNotFoundException the resource not found exception
   */
  @GetMapping("/pedidos/{id}")
  public ResponseEntity<Pedido> getPedidoById(@PathVariable(value = "id") Long pedidoId)
      throws ResourceNotFoundException {
    Pedido pedido =
      pedidoRepository
            .findById(pedidoId)
            .orElseThrow(() -> new ResourceNotFoundException("Pedido not found on :: " + pedidoId));
    return ResponseEntity.ok().body(pedido);
  }

  @PostMapping("/pedidos")
  public Pedido createPedido(@Valid @RequestBody Pedido pedido) {
    return pedidoRepository.save(pedido);
  }

  /**
   * Update pedido response entity.
   *
   * @param pedidoId the pedido id
   * @param pedidoDetails the pedido details
   * @return the response entity
   * @throws ResourceNotFoundException the resource not found exception
   */
  @PutMapping("/pedidos/{id}")
  public ResponseEntity<Pedido> updatePedido(
      @PathVariable(value = "id") Long pedidoId, @Valid @RequestBody Pedido pedidoDetails)
      throws ResourceNotFoundException {

      Pedido pedido =
        pedidoRepository
            .findById(pedidoId)
            .orElseThrow(() -> new ResourceNotFoundException("Pedido not found on :: " + pedidoId));

    pedido.setNomeProduto(pedidoDetails.getNomeProduto());
    pedido.setQtd(pedidoDetails.getQtd());
    final Pedido updatedPedido = pedidoRepository.save(pedido);
    return ResponseEntity.ok(updatedPedido);
  }

  /**
   * Delete pedido map.
   *
   * @param pedidoId the pedido id
   * @return the map
   * @throws Exception the exception
   */
  @DeleteMapping("/pedidos/{id}")
  public Map<String, Boolean> deletePedido(@PathVariable(value = "id") Long pedidoId) throws Exception {
    Pedido pedido =
      pedidoRepository
            .findById(pedidoId)
            .orElseThrow(() -> new ResourceNotFoundException("Pedido not found on :: " + pedidoId));

    pedidoRepository.delete(pedido);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
}
