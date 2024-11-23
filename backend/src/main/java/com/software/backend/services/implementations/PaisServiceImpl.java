package com.software.backend.services.implementations;

import java.util.Optional;

import com.software.backend.persistence.repositories.interfaces.PaisRepository;
import com.software.backend.services.interfaces.PaisService;

import org.springframework.stereotype.Service;

import com.software.backend.models.Pais;
import com.software.backend.models.Provincia;

@Service
public class PaisServiceImpl extends GenericServiceImpl<Pais, String, PaisRepository> implements PaisService {

    @Override
    public Optional<Provincia> getProvincia(String id,String nombreProvincia){

        return super.getRepositorio().findProvincia(id, nombreProvincia);

    }

}
