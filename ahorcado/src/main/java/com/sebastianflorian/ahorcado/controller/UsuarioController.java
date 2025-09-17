package com.sebastianflorian.ahorcado.controller;

import com.sebastianflorian.ahorcado.model.Usuario;
import com.sebastianflorian.ahorcado.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Integer idUsuario) {
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario savedUsuario = usuarioService.saveUsuario(usuario);
            return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable("id") Integer idUsuario, @RequestBody Usuario usuario) {
        try {
            Usuario updatedUsuario = usuarioService.updateUsuario(idUsuario, usuario);
            return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
        } catch (RuntimeException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUsuario(@PathVariable("id") Integer idUsuario) {
        try {
            usuarioService.deleteUsuario(idUsuario);
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("message", "El usuario con ID " + idUsuario + " se ha eliminado exitosamente.");
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } catch (RuntimeException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}