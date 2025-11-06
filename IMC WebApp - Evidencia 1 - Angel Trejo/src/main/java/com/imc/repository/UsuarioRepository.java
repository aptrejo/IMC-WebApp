package com.imc.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.imc.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
