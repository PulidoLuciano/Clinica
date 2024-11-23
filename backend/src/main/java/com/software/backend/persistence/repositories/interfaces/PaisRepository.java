package com.software.backend.persistence.repositories.interfaces;

import java.util.Optional;

import com.software.backend.models.Pais;
import com.software.backend.models.Provincia;
import com.software.backend.persistence.base.BaseRepository;

public interface PaisRepository extends BaseRepository<Pais, String> {


    public Optional<Provincia> findProvincia(String id,String nombreProvincia);

}
