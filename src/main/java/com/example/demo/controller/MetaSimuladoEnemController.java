package com.example.demo.controller;

import com.example.demo.model.MetaSimuladoEnem;
import com.example.demo.repository.MetaSimuladoEnemRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/metas-enem")
public class MetaSimuladoEnemController {

    private final MetaSimuladoEnemRepository repository;

    public MetaSimuladoEnemController(MetaSimuladoEnemRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<MetaSimuladoEnem>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetaSimuladoEnem> buscar(@PathVariable Integer id) {
        MetaSimuladoEnem meta = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Simulado não encontrado."));
        return ResponseEntity.ok(meta);
    }

    @PostMapping
    public ResponseEntity<MetaSimuladoEnem> criar(@Valid @RequestBody MetaSimuladoEnem dados) {
        dados.setId(null);
        aplicarRegra(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(dados));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetaSimuladoEnem> atualizar(@PathVariable Integer id,
                                                      @Valid @RequestBody MetaSimuladoEnem dados) {
        MetaSimuladoEnem existente = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Simulado não encontrado."));

        existente.setNomeAluno(dados.getNomeAluno());
        existente.setCursoDesejado(dados.getCursoDesejado());
        existente.setNotaCorteAlvo(dados.getNotaCorteAlvo());
        existente.setNotaHumanas(dados.getNotaHumanas());
        existente.setNotaNatureza(dados.getNotaNatureza());
        existente.setNotaMatematica(dados.getNotaMatematica());
        existente.setNotaLinguagens(dados.getNotaLinguagens());
        existente.setNotaRedacao(dados.getNotaRedacao());

        aplicarRegra(existente);
        return ResponseEntity.ok(repository.save(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Simulado não encontrado.");
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Regra de negócio: valida a redação, calcula a média e define atingiuMeta
    private void aplicarRegra(MetaSimuladoEnem d) {
        if (d.getNotaRedacao() < 0 || d.getNotaRedacao() > 1000) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A nota de redação deve estar entre 0 e 1000.");
        }

        double media = (d.getNotaHumanas() + d.getNotaNatureza() + d.getNotaMatematica()
                + d.getNotaLinguagens() + d.getNotaRedacao()) / 5.0;

        d.setMediaFinal(Math.round(media * 100.0) / 100.0);
        d.setAtingiuMeta(d.getMediaFinal() >= d.getNotaCorteAlvo());
    }
}
