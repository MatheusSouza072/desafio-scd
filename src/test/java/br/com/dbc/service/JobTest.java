package br.com.dbc.service;

import br.com.dbc.MockData;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class JobTest {

    @InjectMocks
    private Job job;

    @Mock
    private Validador validador;

    @Mock
    private ContaReaderService contaReaderService;
    @Mock
    private ReceitaService receitaService;
    @Mock
    private ContaWriterService contaWriterService;


    @Test
    public void validarFluxoExecucao() throws Exception {
        String path = Paths.get("src/test/resources/csv/input.csv").toAbsolutePath().toString();

        when(validador.validar(any())).thenReturn(true);
        when(contaReaderService.read(path)).thenReturn(MockData.getData());
        when(receitaService.atualizarConta(anyString(), anyString(), anyDouble(), anyString())).thenReturn(Boolean.TRUE);
        when(contaWriterService.writer(any(), any())).thenReturn(path);

        job.run(path);

        assertDoesNotThrow(() -> job.run(path));
    }

    @Test
    public void validarFluxoExecucaoExcessao() throws Exception {
        String path = Paths.get("src/test/resources/csv/input.csv").toAbsolutePath().toString();

        when(validador.validar(any())).thenReturn(true);
        when(contaReaderService.read(path)).thenReturn(MockData.getData());
        when(receitaService.atualizarConta(anyString(), anyString(), anyDouble(), anyString())).thenThrow(new RuntimeException());
        when(contaWriterService.writer(any(), any())).thenReturn(path);

        job.run(path);

        assertDoesNotThrow(() -> job.run(path));
    }

    @Test()
    public void validarFluxoExecucaoExcessaoValidacao() throws Exception {
        String path = Paths.get("src/test/resources/csv/input.csv").toAbsolutePath().toString();

        when(validador.validar(any())).thenReturn(false);

        assertThrows(RuntimeException.class,() -> job.run(path));
    }

}