package com.agencia.api.controller;

import com.agencia.api.model.Destino;
import com.agencia.api.service.DestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinos")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    @PostMapping
    public ResponseEntity<Destino> cadastrarDestino(@RequestBody Destino destino) {
        Destino novoDestino = destinoService.cadastrarDestino(destino);
        return ResponseEntity.ok(novoDestino);
    }

    @GetMapping
    public ResponseEntity<List<Destino>> listarDestinos() {
        return ResponseEntity.ok(destinoService.listarDestinos());
    }

    @GetMapping("/pesquisa")
    public ResponseEntity<List<Destino>> pesquisarDestinos(@RequestParam String termo) {
        return ResponseEntity.ok(destinoService.pesquisarDestinos(termo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> visualizarDestino(@PathVariable Long id) {
        return destinoService.visualizarDestino(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/avaliar")
    public ResponseEntity<Destino> avaliarDestino(@PathVariable Long id, @RequestParam int nota) {
        Destino destinoAvaliado = destinoService.avaliarDestino(id, nota);
        return destinoAvaliado != null ? ResponseEntity.ok(destinoAvaliado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDestino(@PathVariable Long id) {
        if (destinoService.excluirDestino(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}