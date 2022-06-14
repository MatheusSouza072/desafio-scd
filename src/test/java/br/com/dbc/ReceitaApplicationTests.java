package br.com.dbc;

import br.com.dbc.service.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@SpringBootTest(classes = Receita.class)

class ReceitaApplicationTests {

    @MockBean
    private Job job;

    @BeforeEach
    public void before() throws Exception {

        doNothing().when(job).run(any());
    }

    @Test
    void contextLoads() {
    }

}
