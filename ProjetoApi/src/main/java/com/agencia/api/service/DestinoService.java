package com.agencia.api.service;

import com.agencia.api.model.Destino;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DestinoService {

    private List<Destino> destinos = new ArrayList<>();
    private long idCounter = 1;

    public Destino cadastrarDestino(Destino destino) {
        destino.setId(idCounter++);
        destino.setMediaAvaliacao(0.0);
        destino.setTotalAvaliacoes(0);
        destinos.add(destino);
        return destino;
    }

    public List<Destino> listarDestinos() {
        return destinos;
    }

    public List<Destino> pesquisarDestinos(String termo) {
        List<Destino> resultados = new ArrayList<>();
        for (Destino destino : destinos) {
            if (destino.getNome().toLowerCase().contains(termo.toLowerCase()) || 
                destino.getLocalizacao().toLowerCase().contains(termo.toLowerCase())) {
                resultados.add(destino);
            }
        }
        return resultados;
    }

    public Optional<Destino> visualizarDestino(Long id) {
        return destinos.stream().filter(d -> d.getId().equals(id)).findFirst();
    }

    public Destino avaliarDestino(Long id, int nota) {
        Optional<Destino> destinoOptional = visualizarDestino(id);
        if (destinoOptional.isPresent()) {
            Destino destino = destinoOptional.get();
            double novaMedia = ((destino.getMediaAvaliacao() * destino.getTotalAvaliacoes()) + nota) 
                                / (destino.getTotalAvaliacoes() + 1);
            destino.setMediaAvaliacao(novaMedia);
            destino.setTotalAvaliacoes(destino.getTotalAvaliacoes() + 1);
            return destino;
        }
        return null;
    }

    public boolean excluirDestino(Long id) {
        return destinos.removeIf(destino -> destino.getId().equals(id));
    }
}