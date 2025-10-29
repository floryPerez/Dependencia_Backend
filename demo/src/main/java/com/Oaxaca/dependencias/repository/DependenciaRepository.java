package com.Oaxaca.dependencias.repository;


import com.Oaxaca.dependencias.model.Dependencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DependenciaRepository extends JpaRepository<Dependencia, Long> {

    // Buscar dependencias por nombre y correp con paginación
    Page<Dependencia> findByNombreContainingIgnoreCaseAndCorreoContainingIgnoreCase(
            String nombre, String correo, Pageable pageable);
    Page<Dependencia> findAll(Pageable pageable);


    boolean existsByCorreo(String correo);
}
