package com.sebastianflorian.ahorcado.service;

import com.sebastianflorian.ahorcado.model.Palabra;

import java.util.List;


public interface PalabraService {
    List<Palabra> getAllPalabras();
    Palabra getPalabraById(Integer idPalabra);
    Palabra savePalabra(Palabra palabra);
    Palabra updatePalabra(Integer idPalabra, Palabra palabra);
    void deletePalabra(Integer idPalabra);
}
