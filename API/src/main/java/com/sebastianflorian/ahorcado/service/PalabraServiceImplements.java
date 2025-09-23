package com.sebastianflorian.ahorcado.service;

import com.sebastianflorian.ahorcado.model.Palabra;
import com.sebastianflorian.ahorcado.repository.PalabraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PalabraServiceImplements implements PalabraService {

    private final PalabraRepository palabraRepository;

    public PalabraServiceImplements(PalabraRepository palabraRepository) {
        this.palabraRepository = palabraRepository;
    }

    @Override
    public List<Palabra> getAllPalabras() {
        return palabraRepository.findAll();
    }

    @Override
    public Palabra getPalabraById(Integer id) {
        return palabraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Palabra no encontrada con ID: " + id));
    }

    @Override
    public Palabra savePalabra(Palabra palabra) {
        validatePalabra(palabra);
        if (palabraRepository.existsByPalabra(palabra.getPalabra())) {
            throw new RuntimeException("La palabra ya existe en el diccionario");
        }
        return palabraRepository.save(palabra);
    }

    @Override
    public Palabra updatePalabra(Integer idPalabra, Palabra palabra) {
        validatePalabra(palabra);
        Palabra existingPalabra = palabraRepository.findById(idPalabra)
                .orElseThrow(() -> new RuntimeException("Palabra no encontrada con ID: " + idPalabra));

        if (palabraRepository.existsByPalabraAndIdPalabraNot(palabra.getPalabra(), idPalabra)) {
            throw new RuntimeException("La palabra ya está en uso por otra entrada");
        }

        existingPalabra.setPalabra(palabra.getPalabra());
        existingPalabra.setPistaUno(palabra.getPistaUno());
        existingPalabra.setPistaDos(palabra.getPistaDos());
        existingPalabra.setPistaTres(palabra.getPistaTres());

        return palabraRepository.save(existingPalabra);
    }

    @Override
    public void deletePalabra(Integer idPalabra) {
        if (!palabraRepository.existsById(idPalabra)) {
            throw new RuntimeException("La palabra no fue encontrada");
        }
        palabraRepository.deleteById(idPalabra);
    }

    private void validatePalabra(Palabra palabra) {
        if (palabra.getPalabra() == null || palabra.getPalabra().trim().isEmpty()) {
            throw new RuntimeException("La palabra no puede estar vacía");
        }
        if (!palabra.getPalabra().matches("^[a-zA-Z]+$")) {
            throw new RuntimeException("La palabra solo puede contener letras");
        }
        if (palabra.getPistaUno() == null || palabra.getPistaUno().trim().isEmpty()) {
            throw new RuntimeException("La pista 1 no puede estar vacía");
        }
        if (palabra.getPistaDos() == null || palabra.getPistaDos().trim().isEmpty()) {
            throw new RuntimeException("La pista 2 no puede estar vacía");
        }
        if (palabra.getPistaTres() == null || palabra.getPistaTres().trim().isEmpty()) {
            throw new RuntimeException("La pista 3 no puede estar vacía");
        }
    }
}