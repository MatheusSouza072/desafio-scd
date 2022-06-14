package br.com.dbc.service;

import br.com.dbc.model.Conta;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

@Service
public class ContaWriterService {

    private final String splitter = ";";

    public String writer(List<Conta> dadosConta, String path) throws IOException {

        String resultPath = path.replaceAll("\\.csv", "") + "_result.csv";
        BufferedWriter writer = new BufferedWriter(new FileWriter(resultPath));
        writer.write("agencia;conta;saldo;status;processado");
        nextLine(writer);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        for (Conta dado : dadosConta
        ) {

            writerSplit(writer, dado.getAgencia());
            writerSplit(writer, dado.getConta().substring(0, 5) + "-" + dado.getConta().charAt(5));
            writerSplit(writer, decimalFormat.format(dado.getSaldo()).replace(".", ","));
            writerSplit(writer, dado.getStatus().name());
            writerFinish(writer, dado.getProcessado());
        }

        writer.close();

        return resultPath;
    }

    private void writerFinish(BufferedWriter writer, Boolean text) throws IOException {
        writer.write(text.toString());
        nextLine(writer);
    }

    private void writerSplit(BufferedWriter writer, String text) throws IOException {
        writer.write(text + splitter);
    }

    private void nextLine(BufferedWriter writer) throws IOException {
        writer.write("\n");
    }


}
