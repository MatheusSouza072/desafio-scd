package br.com.dbc.service;

import br.com.dbc.model.Conta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ContaReaderServiceTest {

    @InjectMocks
    private ContaReaderService contaReaderService;

    @Test
    @DisplayName("validar leitura e mapeamento de csv com sucesso")
    public void validarleituraArquivoSucesso() throws IOException {

        Path path = Paths.get("src/test/resources/csv/input.csv");
        List<Conta> dadoConta = contaReaderService.read(path.toAbsolutePath().toString());

        assertTrue(dadoConta.size() > 0);
        assertNotNull(dadoConta);
        assertFalse(dadoConta.isEmpty());
    }


    @Test
    @DisplayName("validar leitura e mapeamento de csv com erro")
    public void validarleituraArquivoError() {

        Path path = Paths.get("src/test/resources/csv/input_fail.csv");

        assertThrows(Exception.class,() ->contaReaderService.read(path.toAbsolutePath().toString()));
    }
}