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

    @Column(name = "cCertApolc")
    private Long cCertApolc;

    @Column(name = "nPpstaSegur")
    private Long nPpstaSegur;

    @Column(name = "iProdtApolc")
    private String iProdtApolc;

    @Column(name = "iSegrdApolc")
    private String iSegrdApolc;

    @Column(name = "cCpfCnpj")
    private String cCpfCnpj;

    @Column(name = "cCtrlCpfCnpj")
    private String cCtrlCpfCnpj;

    @Column(name = "rFoneSegrd")
    private String rFoneSegrd;

    @Column(name = "eEmailSegrd")
    private String eEmailSegrd;

    @Column(name = "cSucurApolc")
    private Integer cSucurApolc;

    @Column(name = "cAgBcria")
    private Integer cAgBcria;

    @Column(name = "cCrrtrSegurVida")
    private Integer cCrrtrSegurVida;

    @Column(name = "cFormaPgtoFat")
    private Integer cFormaPgtoFat;

    @Column(name = "cPerdcPgtoFat")
    private Integer cPerdcPgtoFat;



    @Column(name = "dRenovApolc")
    private LocalDate dRenovApolc;

    @Column(name = "dEmisApolc")
    private LocalDate dEmisApolc;

    @Column(name = "dInicVgciaApolc")
    private LocalDate dInicVgciaApolc;

    @Column(name = "dFimVgciaApolc")
    private LocalDate dFimVgciaApolc;

    @Column(name = "vPrmioLiq")
    private BigDecimal vPrmioLiq;

    @Column(name = "vIofApolcCober")
    private BigDecimal vIofApolcCober;

    @Column(name = "vCaptlSegrd")
    private BigDecimal vCaptlSegrd;

    @Column(name = "vCaptlSegrdPpsto")
    private BigDecimal vCaptlSegrdPpsto;

    @Column(name = "cSitRenovApolc")
    private byte cSitRenovApolc;


}
