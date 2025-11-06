package com.imc.servicio;

import com.imc.modelo.Medicion;
import com.imc.modelo.Usuario;
import com.imc.repository.MedicionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicionServicio {
    private final MedicionRepository repo;
    public MedicionServicio(MedicionRepository repo){ this.repo = repo; }

    public Medicion guardar(Medicion m){ return repo.save(m); }
    public List<Medicion> historial(Usuario u){ return repo.findByUsuarioOrderByFechaHoraDesc(u); }
}
