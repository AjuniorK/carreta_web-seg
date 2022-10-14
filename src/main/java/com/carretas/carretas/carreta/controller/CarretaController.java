package com.carretas.carretas.carreta.controller;

import com.carretas.carretas.carreta.entity.Carreta;
import com.carretas.carretas.carreta.service.CarretaService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Qualifier;

@RestController
@RequestMapping("/carreta")
@SecurityScheme(
        name = "Bearer",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
public class CarretaController {

    @Autowired
    CarretaService carretaService;

    @Value("${application.name}")
    private String applicationName;

    @PostMapping
    public void salvar(@RequestBody Carreta carreta) {
        carretaService.salvar(carreta);
    }

    @GetMapping
    public List<Carreta> listar() {
        return carretaService.listar();
    }

    @GetMapping("/hello")
    public String helloWorld(){
        return applicationName;
    }

//    @GetMapping("/{id}")
//    public Carreta buscarPorId(@PathVariable("id") Integer id) {
//        return carretaService.buscarPorId(id);
//    }

    @PutMapping("/{id}")
    public void update(@RequestBody Carreta carreta, @PathVariable Integer id) {
        System.out.println(id);
        System.out.println(carreta.getPlaca());
        System.out.println(carreta.getTipo());
        System.out.println(carreta.getVolume());
        carretaService.setCarretaInfoById(carreta.getPlaca(), carreta.getTipo(), carreta.getVolume(), id);

    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") Integer id) {
        carretaService.excluir(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carreta> buscarPorId(@PathVariable("id") Integer id) {
        try {
            Carreta carreta = carretaService.buscarPorId(id);
            return new ResponseEntity<>(carreta, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}