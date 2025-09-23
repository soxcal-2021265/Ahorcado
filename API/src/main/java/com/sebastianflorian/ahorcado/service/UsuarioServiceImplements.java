package com.sebastianflorian.ahorcado.service;

import com.sebastianflorian.ahorcado.model.Usuario;
import com.sebastianflorian.ahorcado.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImplements implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Lista de dominios de correo electrónico permitidos
    private final List<String> dominiosPermitidos = List.of(
            "@gmail.com",
            "@outlook.com",
            "@yahoo.com",
            "@kinal.edu.gt",
            "@icloud.com"
    );

    public UsuarioServiceImplements(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuarioById(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        // Validación del dominio del correo
        if (!esDominioValido(usuario.getCorreo())) {
            throw new RuntimeException("El correo debe tener un dominio válido (@gmail.com, @outlook.com, @yahoo.com, @kinal.edu.gt, @icloud.com)");
        }

        // Validación de existencia del correo
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya existe en la base de datos");
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Integer idUsuario, Usuario usuario) {
        Usuario existingUsuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));

        // Validación del dominio del correo para la actualización
        if (!esDominioValido(usuario.getCorreo())) {
            throw new RuntimeException("El correo debe tener un dominio válido (@gmail.com, @outlook.com, @yahoo.com, @kinal.edu.gt, @icloud.com)");
        }

        // Validación de existencia de correo en otros usuarios
        if (usuarioRepository.existsByCorreoAndIdUsuarioNot(usuario.getCorreo(), idUsuario)) {
            throw new RuntimeException("El correo ya está en uso por otra cuenta");
        }

        // Actualización de los datos del usuario
        existingUsuario.setCorreo(usuario.getCorreo());
        existingUsuario.setContrasena(usuario.getContrasena());

        return usuarioRepository.save(existingUsuario);
    }

    @Override
    public void deleteUsuario(Integer idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new RuntimeException("El usuario no existe");
        }
        usuarioRepository.deleteById(idUsuario);
        System.out.println("El usuario se ha eliminado");
    }
    private boolean esDominioValido(String correo) {
        return dominiosPermitidos.stream().anyMatch(correo::endsWith);
    }
}