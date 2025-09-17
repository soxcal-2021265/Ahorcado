package com.sebastianflorian.ahorcado.repository;

import com.sebastianflorian.ahorcado.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Method to check if an email already exists
    boolean existsByCorreo(String correo);

    // Method to check if an email exists for a user other than the one being updated
    boolean existsByCorreoAndIdUsuarioNot(String correo, Integer idUsuario);
}