package com.Oaxaca.dependencias.controller;


import com.Oaxaca.dependencias.model.Dependencia;
import com.Oaxaca.dependencias.repository.DependenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/dependencias")

public class DependenciaController {

    @Autowired
    private DependenciaRepository repo;
    @GetMapping("/")
    public String home() {
        return "API de Dependencias funcionando!";
    }
    @GetMapping
    public Page<Dependencia> listar(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "activo") String sortBy){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy).descending());
        return repo.findAll(pageRequest);
    }
    @GetMapping("/buscar")
    public Page<Dependencia> search(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String correo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        nombre = (nombre == null) ? "" : nombre;
        correo = (correo == null) ? "" : correo;

        return repo.findByNombreContainingIgnoreCaseAndCorreoContainingIgnoreCase(nombre, correo, pageable);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Dependencia dep) {

        // verificar si ya existe el correo
        boolean existe = repo.existsByCorreo(dep.getCorreo());
        if (existe) {
            return ResponseEntity
                    .badRequest()
                    .body("Ya existe una dependencia con este correo: " + dep.getCorreo());
        }

        Dependencia nueva = repo.save(dep);
        return ResponseEntity.ok(nueva);
    }


    @PutMapping("/{id}")
    public Dependencia actualizar(@PathVariable Long id, @Validated @RequestBody Dependencia d) {
        Optional<Dependencia> existente = repo.findById(id);
        if (existente.isPresent()) {
            Dependencia dep = existente.get();
            dep.setNombre(d.getNombre());
            dep.setDireccion(d.getDireccion());
            dep.setTelefono(d.getTelefono());
            dep.setSiglas(d.getSiglas());
            dep.setCorreo(d.getCorreo());
            dep.setActivo(d.isActivo());
            return repo.save(dep);
        }
        throw new RuntimeException("Dependencia no encontrada");
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
