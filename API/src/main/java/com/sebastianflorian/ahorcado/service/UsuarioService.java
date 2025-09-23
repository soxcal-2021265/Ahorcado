package com.sebastianflorian.ahorcado.service;

import com.sebastianflorian.ahorcado.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> getAllUsuarios();
    Usuario getUsuarioById(Integer idUsuario);
    Usuario saveUsuario(Usuario usuario);
    Usuario updateUsuario(Integer idUsuario, Usuario usuario);
    void deleteUsuario(Integer idUsuario);
}