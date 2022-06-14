package br.com.dbc.model;

import lombok.Data;

@Data
public class Conta {

    private String agencia;
    private String conta;
    private Double saldo;
    private StatusConta status;
    private Boolean processado;

}
