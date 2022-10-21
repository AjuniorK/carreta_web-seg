package com.carretas.carretas.carreta.controller;

import com.carretas.carretas.carreta.entity.Cliente;
import com.carretas.carretas.carreta.repository.Clientes;
import com.carretas.carretas.carreta.service.ClienteService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.NoSuchElementException;

//@RestController
//@RequestMapping("/cliente")
//@SecurityScheme(
//		name = "Bearer",
//		type = SecuritySchemeType.HTTP,
//		scheme = "bearer"
//)
//public class ClienteController {
//
//	@Autowired
//	ClienteService clienteService;
//
//	@PostMapping
//	public void salvar(@RequestBody Cliente cliente) {
//		clienteService.salvar(cliente);
//	}
//
//	@PutMapping("/{id}")
//	public void update(@RequestBody Cliente cliente, @PathVariable Integer id) {
//		System.out.println(id);
//		System.out.println(cliente.getCpf());
//		System.out.println(cliente.getNome());
//		System.out.println(cliente.getEmail());
//		clienteService.setClienteRepository(cliente.getCpf(), cliente.getNome(), cliente.getEmail(), id);
//
//	}
//
//	@GetMapping
//	public List<Cliente> listar() {
//		return clienteService.listar();
//	}
//
////    @GetMapping("/{id}")
////    public Carreta buscarPorId(@PathVariable("id") Integer id) {
////        return carretaService.buscarPorId(id);
////    }
//
//	@DeleteMapping("/{id}")
//	public void excluir(@PathVariable("id") Integer id) {
//		clienteService.excluir(id);
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<Cliente> buscarPorId(@PathVariable("id") Integer id) {
//		try {
//			Cliente cliente = clienteService.buscarPorId(id);
//			return new ResponseEntity<>(cliente, HttpStatus.OK);
//		} catch (NoSuchElementException ex) {
//			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//		}
//	}
//
//}

//public class ClienteController {
//
//	private Clientes clientes;
//
//	public ClienteController( Clientes clientes ) {
//		this.clientes = clientes;
//	}
//
//	@GetMapping("{id}")
//	public Cliente getClienteById( @PathVariable Integer id ){
//		return clientes
//				.findById(id)
//				.orElseThrow(() ->
//						new ResponseStatusException(HttpStatus.NOT_FOUND,
//								"Cliente não encontrado"));
//	}
//
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Cliente save( @RequestBody Cliente cliente ){
//		return clientes.save(cliente);
//	}
//
//	@DeleteMapping("{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void delete( @PathVariable Integer id ){
//		clientes.findById(id)
//				.map( cliente -> {
//					clientes.delete(cliente );
//					return cliente;
//				})
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//						"Cliente não encontrado") );
//
//	}
//
//	@PutMapping("{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void update( @PathVariable Integer id,
//						@RequestBody Cliente cliente ){
//		clientes
//				.findById(id)
//				.map( clienteExistente -> {
//					cliente.setId(clienteExistente.getId());
//					clientes.save(cliente);
//					return clienteExistente;
//				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//				"Cliente não encontrado") );
//	}
//
//	@GetMapping
//	public List<Cliente> find( Cliente filtro ){
//		ExampleMatcher matcher = ExampleMatcher
//				.matching()
//				.withIgnoreCase()
//				.withStringMatcher(
//						ExampleMatcher.StringMatcher.CONTAINING );
//
//		Example example = Example.of(filtro, matcher);
//		return clientes.findAll(example);
//	}
//
//}