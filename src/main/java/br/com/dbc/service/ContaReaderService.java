package br.com.dbc.service;

import br.com.dbc.model.Conta;
import br.com.dbc.model.StatusConta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContaReaderService {

    private static final Logger logger = LoggerFactory.getLogger(ContaReaderService.class);

    private final String splitter = ";";

    public List<Conta> read(String path) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(path));

        skipHeader(reader);

        List<Conta> contas = new ArrayList<>();
        
        while (reader.ready()) {
            
            String[] data = reader.readLine().split(splitter);
            Conta conta = mapDadoConta(data);
            contas.add(conta);
        }
        reader.close();

        return contas;
    }

    private String skipHeader(BufferedReader reader) throws IOException {
        return reader.readLine();
    }

    private Conta mapDadoConta(String[] data) {
        try {
            Conta conta = new Conta();
            conta.setAgencia(data[0]);
            conta.setConta(data[1].replaceAll("[^\\d.]", ""));
            conta.setSaldo(Double.parseDouble(data[2].replace(",", ".")));
            conta.setStatus(StatusConta.valueOf(data[3]));
            return conta;
        }catch (Exception ex) {
            logger.error("Erro de leitura do arquivo: ");
            logger.warn(
                    "Siga o formato:\n" +
                    "\tagencia;conta;saldo;status\n" +
                    "\t0101;12225-6;100,00;A\n" +
                    "\t0101;12226-8;3200,50;A\n" +
                    "\t3202;40011-1;-35,12;I\n" +
                    "\t3202;54001-2;0,00;P\n" +
                    "\t3202;00321-2;34500,00;B");
            throw ex;
        }
    }
}
