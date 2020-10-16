package com.jnsvera.osworks.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.jnsvera.osworks.api.domain.exception.NegocioExeption;
import com.jnsvera.osworks.domain.model.Cliente;
import com.jnsvera.osworks.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService{
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public List<Cliente> listar(){
		return clienteRepository.findAll();
	}
	
	public Cliente salvar(Cliente cliente) {

	  Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
	  
	  if(clienteExistente != null && !clienteExistente.equals(cliente))
		  throw new NegocioExeption("Já existe um cliente com este e-mail");
		
		return clienteRepository.save(cliente);
	}
	
	public Cliente update(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}
