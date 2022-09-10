package com.carretas.carretas.carreta.repository;

import com.carretas.carretas.carreta.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
