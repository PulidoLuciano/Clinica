package com.software.backend.services.interfaces;

import java.util.Optional;

import com.software.backend.models.Pais;
import com.software.backend.models.Provincia;

public interface PaisService extends GenericService<Pais, String> {
     public Optional<Provincia> getProvincia(String id,String nombreProvincia);
}
