package com.software.backend.services.implementations;

import com.software.backend.persistence.repositories.interfaces.PaisRepository;
import com.software.backend.services.interfaces.PaisService;

import org.springframework.stereotype.Service;

import com.software.backend.models.Pais;

@Service
public class PaisServiceImpl extends GenericServiceImpl<Pais, String, PaisRepository> implements PaisService {

}
