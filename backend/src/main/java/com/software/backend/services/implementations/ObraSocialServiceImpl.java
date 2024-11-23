package com.software.backend.services.implementations;

import org.springframework.stereotype.Service;

import com.software.backend.models.ObraSocial;
import com.software.backend.persistence.repositories.interfaces.ObraSocialRepository;
import com.software.backend.services.interfaces.ObraSocialService;

@Service
public class ObraSocialServiceImpl extends GenericServiceImpl<ObraSocial, String, ObraSocialRepository> implements ObraSocialService{

}
