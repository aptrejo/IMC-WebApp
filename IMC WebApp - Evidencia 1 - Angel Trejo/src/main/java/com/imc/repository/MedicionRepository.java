package com.imc.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.imc.modelo.Medicion;
import com.imc.modelo.Usuario;

public interface MedicionRepository extends JpaRepository<Medicion, Long> {
    List<Medicion> findByUsuarioOrderByFechaHoraDesc(Usuario usuario);
}
