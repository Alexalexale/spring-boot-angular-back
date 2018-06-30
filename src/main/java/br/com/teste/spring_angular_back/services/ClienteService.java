package br.com.teste.spring_angular_back.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.teste.spring_angular_back.domain.Cliente;
import br.com.teste.spring_angular_back.dto.ClienteInsertDTO;
import br.com.teste.spring_angular_back.repository.ClienteRepository;
import br.com.teste.spring_angular_back.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente insert(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente fromDto(@Valid ClienteInsertDTO dto) {
		return new Cliente.Builder().nome(dto.getNome()).limite(dto.getLimiteCredito()).risco(dto.getRisco()).build();
	}

	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado."));
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}

}