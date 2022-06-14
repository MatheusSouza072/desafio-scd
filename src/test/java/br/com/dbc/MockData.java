package br.com.dbc;

import br.com.dbc.model.Conta;
import br.com.dbc.model.StatusConta;

import java.util.Arrays;
import java.util.List;

public class MockData {
    public static List<Conta> getData() {
        Conta conta1 = new Conta();
        Conta conta2 = new Conta();

        conta1.setAgencia("0101");
        conta1.setConta("122256");
        conta1.setSaldo(100.00);
        conta1.setStatus(StatusConta.A);
        conta1.setProcessado(Boolean.TRUE);

        conta2.setAgencia("0101");
        conta2.setConta("122784");
        conta2.setSaldo(122.50);
        conta2.setStatus(StatusConta.B);
        conta2.setProcessado(Boolean.FALSE);

        return Arrays.asList(conta1, conta2);
    }
}
