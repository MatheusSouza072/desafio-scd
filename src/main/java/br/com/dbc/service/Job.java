package br.com.dbc.service;

import br.com.dbc.model.Conta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(prefix = "receita.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class Job implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Job.class);


    public Job(Validador validador, ContaReaderService contaReaderService, ReceitaService receitaService, ContaWriterService contaWriterService) {
        this.validador = validador;
        this.contaReaderService = contaReaderService;
        this.receitaService = receitaService;
        this.contaWriterService = contaWriterService;
    }

    private final Validador validador;
    private final ContaReaderService contaReaderService;
    private final ReceitaService receitaService;
    private final ContaWriterService contaWriterService;

    @Override
    public void run(String... args) throws Exception {

        logger.info("validando arquivo de entrada");
        if( !validador.validar(args)) {
            throw new RuntimeException("parametros de entrada invalido !!!");
        }
        String path = args[0];

        logger.info("extraindos dados do arquivo: {}", path);
        List<Conta> dadosConta = contaReaderService.read(path);

        logger.info("iniciando processamento.");
        dadosConta = processBatch(dadosConta);

        logger.info("Montando arquivo de saida.");
        String resultPath = contaWriterService.writer(dadosConta, path);

        logger.info("Processo realizado com sucesso: \n Arquivo de resultado: {}", resultPath);

    }

    private List<Conta> processBatch(List<Conta> dadosConta) {
        return dadosConta
                .stream()
                .peek(dadoConta -> {
                    boolean processado = atualizarDadosReceita(dadoConta);
                    dadoConta.setProcessado(processado);
                }).collect(Collectors.toList());
    }

    private boolean atualizarDadosReceita(Conta dadoConta) {
        boolean processado;
        try {
            processado = receitaService.atualizarConta(
                    dadoConta.getAgencia(),
                    dadoConta.getConta(),
                    dadoConta.getSaldo(),
                    dadoConta.getStatus().name()
            );
        } catch (InterruptedException | RuntimeException e) {
            logger.error("Erro de comunicacao com a Receita.  agencia: {} conta: {}", dadoConta.getAgencia(), dadoConta.getConta());
            processado = false;
        }

        return processado;
    }


}

