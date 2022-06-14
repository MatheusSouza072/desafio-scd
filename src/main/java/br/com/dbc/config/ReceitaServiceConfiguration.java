package br.com.dbc.config;

import br.com.dbc.service.ReceitaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceitaServiceConfiguration {

    @Bean
    public ReceitaService receitaService() {
        return new ReceitaService();
    }
}
