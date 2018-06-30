package br.com.teste.spring_angular_back.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.teste.spring_angular_back.domain.Cliente;
import br.com.teste.spring_angular_back.domain.enums.TipoRisco;
import br.com.teste.spring_angular_back.dto.ClienteDTO;
import br.com.teste.spring_angular_back.dto.ClienteInsertDTO;
import br.com.teste.spring_angular_back.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(new ClienteDTO(clienteService.findById(id)));
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteInsertDTO clienteInsertDTO) {
		Cliente cliente = clienteService.fromDto(clienteInsertDTO);
		cliente = clienteService.insert(cliente);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(cliente.getId())).build();
	}

	@GetMapping(value = "/riscos")
	public ResponseEntity<List<String>> getAllRiscos() {
		return ResponseEntity.ok(TipoRisco.getAll());
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		return ResponseEntity.ok(clienteService.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList()));
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<List<ClienteDTO>> findPage(
		@RequestParam(name = "page", defaultValue = "0") Integer page,
		@RequestParam(name = "linesPage", defaultValue = "6") Integer linesPage,
		@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
		@RequestParam(name = "direction", defaultValue = "ASC") String direction
	) {
		return ResponseEntity.ok(clienteService.findPage(page, linesPage, orderBy, direction).map(ClienteDTO::new));
	}
	

}