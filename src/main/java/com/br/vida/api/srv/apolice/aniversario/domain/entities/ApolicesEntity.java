package com.br.vida.api.srv.apolice.aniversario.domain.entities;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Apolices")
@Data
public class ApolicesEntity {
    @Id
    @Column(name = "handle")
    private Long handle;

    @Column(name = "cdCertificadoApolice")
    private Long cdCertificadoApolice;

    @Column(name = "nuPropostaSeguro")
    private Long nuPropostaSeguro;

    @Column(name = "nmProdutoApolice")
    private String nmProdutoApolice;

    @Column(name = "nmSeguradoApolice")
    private String nmSeguradoApolice;

    @Column(name = "cdCpfCnpj")
    private String cdCpfCnpj;

    @Column(name = "cdControleCpfCnpj")
    private String cdControleCpfCnpj;

    @Column(name = "dsTelefoneSegurado")
    private String dsTelefoneSegurado;

    @Column(name = "enEmailSegurado")
    private String enEmailSegurado;

    @Column(name = "cdSucursalApolice")
    private Integer cdSucursalApolice;

    @Column(name = "cdAgenciaBancariaSegurado")
    private Integer cdAgenciaBancariaSegurado;

    @Column(name = "cdCorretorSeguroVida")
    private Integer cdCorretorSeguroVida;

    @Column(name = "cdFormaPagamentoFatura")
    private Integer cdFormaPagamentoFatura;

    @Column(name = "cdPeriodicidadePagamentoFatura")
    private Integer cdPeriodicidadePagamentoFatura;

    @Column(name = "dtRenovacaoApolice")
    private LocalDate dtRenovacaoApolice;

    @Column(name = "dtEmissaoApolice")
    private LocalDate dtEmissaoApolice;

    @Column(name = "dtInicioVigenciaApolice")
    private LocalDate dtInicioVigenciaApolice;

    @Column(name = "dtFimVigenciaApolice")
    private LocalDate dtFimVigenciaApolice;

    @Column(name = "vrPremioLiquido")
    private BigDecimal vrPremioLiquido;

    @Column(name = "vrIofApoliceCobertura")
    private BigDecimal vrIofApoliceCobertura;

    @Column(name = "vrCapitalSegurado")
    private BigDecimal vrCapitalSegurado;

    @Column(name = "vrCapitalSeguradoProposto")
    private BigDecimal vrCapitalSeguradoProposto;

    @Column(name = "cdSituacaoRenovacaoApolice")
    private Byte cdSituacaoRenovacaoApolice;


}
