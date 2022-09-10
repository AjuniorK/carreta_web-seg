package com.carretas.carretas.carreta.controller;

import com.carretas.carretas.carreta.entity.Cliente;
import com.carretas.carretas.carreta.service.ClienteService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cliente")
@SecurityScheme(
		name = "Bearer",
		type = SecuritySchemeType.HTTP,
		scheme = "bearer"
)
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@PostMapping
	public void salvar(@RequestBody Cliente cliente) {
		clienteService.salvar(cliente);
	}

	@GetMapping
	public List<Cliente> listar() {
		return clienteService.listar();
	}

//    @GetMapping("/{id}")
//    public Carreta buscarPorId(@PathVariable("id") Integer id) {
//        return carretaService.buscarPorId(id);
//    }

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable("id") Integer id) {
		clienteService.excluir(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable("id") Integer id) {
		try {
			Cliente cliente = clienteService.buscarPorId(id);
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		} catch (NoSuchElementException ex) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}