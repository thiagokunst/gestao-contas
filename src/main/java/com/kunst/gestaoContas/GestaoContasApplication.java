package com.kunst.gestaoContas;

import com.kunst.gestaoContas.entities.Conta;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Gestão de Contas", description = "API de Gestão de Contas - Desafio Dock"),
		servers = {
				@Server(url = "http://localhost:8080")
		}
)
public class GestaoContasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoContasApplication.class, args);
	}

}
