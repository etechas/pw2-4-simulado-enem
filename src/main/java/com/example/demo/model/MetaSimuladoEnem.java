package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "meta_simulado_enem")
public class MetaSimuladoEnem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome do aluno é obrigatório.")
    @Column(name = "nome_aluno", nullable = false, length = 100)
    private String nomeAluno;

    @NotBlank(message = "O curso desejado é obrigatório.")
    @Column(name = "curso_desejado", nullable = false, length = 100)
    private String cursoDesejado;

    @NotNull(message = "A nota de corte alvo é obrigatória.")
    @DecimalMin(value = "0.0", message = "A nota de corte deve ser no mínimo 0.")
    @DecimalMax(value = "1000.0", message = "A nota de corte deve ser no máximo 1000.")
    @Column(name = "nota_corte_alvo", nullable = false)
    private Double notaCorteAlvo;

    @NotNull(message = "A nota de Humanas é obrigatória.")
    @DecimalMin(value = "0.0", message = "A nota de Humanas deve ser no mínimo 0.")
    @DecimalMax(value = "1000.0", message = "A nota de Humanas deve ser no máximo 1000.")
    @Column(name = "nota_humanas", nullable = false)
    private Double notaHumanas;

    @NotNull(message = "A nota de Natureza é obrigatória.")
    @DecimalMin(value = "0.0", message = "A nota de Natureza deve ser no mínimo 0.")
    @DecimalMax(value = "1000.0", message = "A nota de Natureza deve ser no máximo 1000.")
    @Column(name = "nota_natureza", nullable = false)
    private Double notaNatureza;

    @NotNull(message = "A nota de Matemática é obrigatória.")
    @DecimalMin(value = "0.0", message = "A nota de Matemática deve ser no mínimo 0.")
    @DecimalMax(value = "1000.0", message = "A nota de Matemática deve ser no máximo 1000.")
    @Column(name = "nota_matematica", nullable = false)
    private Double notaMatematica;

    @NotNull(message = "A nota de Linguagens é obrigatória.")
    @DecimalMin(value = "0.0", message = "A nota de Linguagens deve ser no mínimo 0.")
    @DecimalMax(value = "1000.0", message = "A nota de Linguagens deve ser no máximo 1000.")
    @Column(name = "nota_linguagens", nullable = false)
    private Double notaLinguagens;

    @NotNull(message = "A nota de Redação é obrigatória.")
    @DecimalMin(value = "0.0", message = "A nota de redação deve estar entre 0 e 1000.")
    @DecimalMax(value = "1000.0", message = "A nota de redação deve estar entre 0 e 1000.")
    @Column(name = "nota_redacao", nullable = false)
    private Double notaRedacao;

    // Calculados pelo servidor (regra de negócio)
    @Column(name = "media_final", nullable = false)
    private Double mediaFinal;

    // Guardado no banco como INT: 1 = atingiu a meta, 0 = não atingiu
    @Convert(converter = BooleanParaIntConverter.class)
    @Column(name = "atingiu_meta", nullable = false)
    private Boolean atingiuMeta;

    public MetaSimuladoEnem() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNomeAluno() { return nomeAluno; }
    public void setNomeAluno(String nomeAluno) { this.nomeAluno = nomeAluno; }

    public String getCursoDesejado() { return cursoDesejado; }
    public void setCursoDesejado(String cursoDesejado) { this.cursoDesejado = cursoDesejado; }

    public Double getNotaCorteAlvo() { return notaCorteAlvo; }
    public void setNotaCorteAlvo(Double notaCorteAlvo) { this.notaCorteAlvo = notaCorteAlvo; }

    public Double getNotaHumanas() { return notaHumanas; }
    public void setNotaHumanas(Double notaHumanas) { this.notaHumanas = notaHumanas; }

    public Double getNotaNatureza() { return notaNatureza; }
    public void setNotaNatureza(Double notaNatureza) { this.notaNatureza = notaNatureza; }

    public Double getNotaMatematica() { return notaMatematica; }
    public void setNotaMatematica(Double notaMatematica) { this.notaMatematica = notaMatematica; }

    public Double getNotaLinguagens() { return notaLinguagens; }
    public void setNotaLinguagens(Double notaLinguagens) { this.notaLinguagens = notaLinguagens; }

    public Double getNotaRedacao() { return notaRedacao; }
    public void setNotaRedacao(Double notaRedacao) { this.notaRedacao = notaRedacao; }

    public Double getMediaFinal() { return mediaFinal; }
    public void setMediaFinal(Double mediaFinal) { this.mediaFinal = mediaFinal; }

    public Boolean getAtingiuMeta() { return atingiuMeta; }
    public void setAtingiuMeta(Boolean atingiuMeta) { this.atingiuMeta = atingiuMeta; }
}
