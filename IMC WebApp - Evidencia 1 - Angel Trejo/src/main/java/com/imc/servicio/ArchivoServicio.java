package com.imc.servicio;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Collectors;

@Service
public class ArchivoServicio {
    public String crearArchivo(String ruta, String contenido) throws IOException {
        Path p = Paths.get(ruta);
        Files.createDirectories(p.getParent() != null ? p.getParent() : Paths.get("."));
        Files.writeString(p, contenido == null ? "" : contenido, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        return p.toAbsolutePath().toString();
    }
    public String listarDirectorio(String ruta) throws IOException {
        Path dir = Paths.get(ruta);
        if (!Files.exists(dir) || !Files.isDirectory(dir)) throw new IOException("Directorio inválido");
        return Files.list(dir).map(Path::toString).collect(Collectors.joining("\n"));
    }
}
