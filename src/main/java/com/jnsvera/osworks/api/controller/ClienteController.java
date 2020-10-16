package com.jnsvera.osworks.api.controller;
 
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jnsvera.osworks.api.domain.service.CadastroClienteService;
import com.jnsvera.osworks.domain.model.Cliente;
import com.jnsvera.osworks.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	 
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CadastroClienteService cadastroCliente;
	
	@GetMapping 
	public List<Cliente> listar() {		
		
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar (@PathVariable Long clienteId) {
		java.util.Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		if(cliente.isPresent())
			return ResponseEntity.ok(cliente.get());	
		else
			return ResponseEntity.notFound().build();	
		
	}
	
	@PostMapping
	public Cliente addCliente(@Valid @RequestBody Cliente cliente) {
		
		return cadastroCliente.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> updateCliente(@Valid @PathVariable Long clienteId,@RequestBody Cliente cliente) {
	
		if(!clienteRepository.existsById(clienteId))
			return ResponseEntity.notFound().build();
		 	cliente.setId(clienteId);
			cliente = clienteRepository.save(cliente);
			return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> deleteCliente(@PathVariable Long clienteId) {
	
		if(!clienteRepository.existsById(clienteId))
			return ResponseEntity.notFound().build();
		
		cadastroCliente.excluir(clienteId);
		return ResponseEntity.noContent().build();
	
		 
	}
}
