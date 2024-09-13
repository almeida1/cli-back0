package com.fatec.sigvsback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fatec.sigvsback.model.Cliente;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Req02ConsultarClienteTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void ct01_consultar_todos_clientes_com_sucesso() {
		//dado que existem clientes cadastrados no banco
		String URLBase = "/api/v1/clientes/all";

		// quand consulto os detalhes do cliente
		ResponseEntity<Cliente[]> response = restTemplate.getForEntity(URLBase, Cliente[].class);

		// entao retorna os detalhes dos clientes cadastrados
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		Cliente[] clientes = response.getBody();
		assertThat(clientes).hasSize(3);
		assertThat(clientes[0].getNome()).isEqualTo("Jose da Silva");
		assertThat(clientes[1].getNome()).isEqualTo("Maria Souza");
	}
	
	@Test
    //@DisplayName("Deve buscar um cliente por ID com sucesso")
    void ct02_consultar_cliente_pelo_id_com_sucesso() {
        // dado que o cliente esta cadastrado
        Long clienteId = 1L;
        String URLBase = "/api/v1/clientes/";
        HttpHeaders headers = new HttpHeaders();
        // Define o tipo de conteúdo esperado
        headers.setContentType(MediaType.APPLICATION_JSON);  
        HttpEntity<String> entity = new HttpEntity<>(headers);
        // quando consulto o cliente pelo id
        ResponseEntity<Cliente> response = restTemplate.exchange(
            URLBase + clienteId, 
            HttpMethod.GET, 
            entity, 
            Cliente.class
        );

        // entao retorna as informacoes do cliente
        assertEquals(HttpStatus.OK, response.getStatusCode());  
        Cliente cliente = response.getBody();
        assertEquals(clienteId, cliente.getId());  
        assertEquals("Souza", cliente.getNome());  
        assertEquals("Av. Aguia de Haia", cliente.getEndereco());  
    }
}
