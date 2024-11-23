package com.software.backend.persistence.repositories.implementations;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.software.backend.models.Pais;
import com.software.backend.models.Provincia;
import com.software.backend.persistence.base.BaseRepositoryImpl;
import com.software.backend.persistence.repositories.interfaces.PaisRepository;

@Repository
public class PaisRepositoryImpl extends BaseRepositoryImpl<Pais, String> implements PaisRepository {

    @Override
    public Optional<Provincia> findProvincia(String id, String nombreProvincia) {
        Optional<Pais> pais = findById(id);
        if (pais.isEmpty())
            throw new IllegalArgumentException("No existe un pais con ese nombre");
        Optional<Provincia> provincia = pais.get().getProvincias().stream()
                .filter(entity -> entity.getId().equals(nombreProvincia)).findFirst();
        return provincia;
    }
}
