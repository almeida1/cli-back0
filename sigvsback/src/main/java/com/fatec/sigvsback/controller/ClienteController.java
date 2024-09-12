  package com.fatec.sigvsback.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.sigvsback.model.Cliente;
import com.fatec.sigvsback.servico.ClienteServico;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/clientes")
public class ClienteController {
	// REST API para manipular requisicoes do tipo get
	// http://localhost:8080/cliente
	@Autowired
	ClienteServico servico;
	@GetMapping
	public Cliente getCliente() {
		Cliente cliente = new Cliente("Jose", "Av. Paulista");
		return cliente;
	}

	// http://localhost:8080/api/v1/clientes/all
	@GetMapping("/all")
	public List<Cliente> getAll() {
		return servico.consultaTodos();
	}

	// Anotação path variable usada em um argumento de método para vinculá-lo ao
	// valor
	// de uma variável da URL
	// http://localhost:8080/api/v1/clientes/1
	@GetMapping("/{id}")
	public Cliente getClientePorId(@PathVariable("id") Long clienteId) {
		return new Cliente(clienteId, "Souza", "Av. Aguia de Haia");
	}

	// http://localhost:8080/api/v1/clientes/consulta?id=1
	@GetMapping("/consulta")
	public Cliente getClienteRequestVariable(@RequestParam Long id) {
		return new Cliente(id, "Carlos", "Av. Aguia de Haia");
	}
	//Rest API para manipular requisicoes do tipo post
	//http://localhost:8080/api/v1/clientes
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente cadastraCliente(@RequestBody Cliente cliente) {
		List<Cliente> listaDeClientes = new ArrayList<>();
		Cliente novoCliente = new Cliente (1L,cliente.getNome(),cliente.getEndereco());
		listaDeClientes.add(novoCliente);
		return listaDeClientes.get(0);
	}
	// REST API que maipula requisicoes do tipo put
	// http://localhost:8080/api/v1/clientes/1/update
	@PutMapping("/{id}/update")
	public Cliente atualizaCliente (@RequestBody Cliente cliente, @PathVariable("id") Long id) {
		Cliente clienteAtualizado = new Cliente (id, cliente.getNome(), cliente.getEndereco());
		return clienteAtualizado;
	}
	// REST API que maipula requisicoes do tipo delete
	// http://localhost:8080/clientes/1/delete
	@DeleteMapping("/{id}/delete")
	public String deleteCliente (@PathVariable("id") int id) {
		return "Cliente excluido com sucesso id=> " + id;
	}
	//REST API para manipular requisicoes do tipo get
	//http://localhost:8080/cliente
	@GetMapping("/cliente")
	public ResponseEntity<Cliente> getCliente2() {
	Cliente cliente = new Cliente (1L, "Jose", "Av. Paulista");
		//return new ResponseEntity<>(cliente, HttpStatus.OK);
		return ResponseEntity.ok(cliente); //estatico
	}



}
