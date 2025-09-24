package com.sebastianflorian.ahorcado.repository;

import com.sebastianflorian.ahorcado.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByCorreo(String correo);

    boolean existsByCorreoAndIdUsuarioNot(String correo, Integer idUsuario);
}