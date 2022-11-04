package com.carretas.carretas.carreta.repository;

import com.carretas.carretas.carreta.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    Optional<Usuario> findByLogin(String login);
}
