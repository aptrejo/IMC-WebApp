package com.imc.servicio;

import com.imc.modelo.Usuario;
import com.imc.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioServicio {
    private final UsuarioRepository repo;
    public UsuarioServicio(UsuarioRepository repo){ this.repo = repo; }

    public Optional<Usuario> buscarPorNombreUsuario(String u){ return repo.findByNombreUsuario(u); }
    public Usuario guardar(Usuario u){ return repo.save(u); }
}
