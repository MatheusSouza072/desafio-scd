package br.com.dbc.service;

import br.com.dbc.MockData;
import br.com.dbc.model.Conta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ContaWriterServiceTest {

    @InjectMocks
    private ContaWriterService service;

    @Test
    @DisplayName("validar escrita de csv com sucesso")
    public void validarEscritaArquivoSucesso() throws IOException {

        Path path = Paths.get("src/test/resources/csv/input.csv");
        List<Conta> dadosConta = MockData.getData();
        String resultPath = service.writer(dadosConta, path.toAbsolutePath().toString());

        File result = new File(resultPath);
        boolean isFile = result.isFile();
        result.deleteOnExit();

        assertTrue(isFile);
    }


}