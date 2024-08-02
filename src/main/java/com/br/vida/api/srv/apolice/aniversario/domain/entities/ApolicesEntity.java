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



    @Column(name = "cCertApolc")
    private short cCertApolc;

    @Column(name = "dRenovApolc")
    private BigDecimal dRenovApolc;

    @Column(name = "dEmisApolc")
    private LocalDate dEmisApolc;

    @Column(name = "dInicVgciaApolc")
    private Long dInicVgciaApolc;

    @Column(name = "dFimVgciaApolc")
    private String dFimVgciaApolc;

    @Column(name = "vPrmioLiq")
    private byte vPrmioLiq;

    @Column(name = "vIofApolcCober")
    private short vIofApolcCober;

    @Column(name = "vCaptlSegrd")
    private short vCaptlSegrd;

    @Column(name = "vCaptlSegrdPpsto")
    private short vCaptlSegrdPpsto;

    @Column(name = "qDiaCanctApolc")
    private short qDiaCanctApolc;


}
