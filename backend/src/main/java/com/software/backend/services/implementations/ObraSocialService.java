package com.software.backend.services.implementations;
import org.springframework.stereotype.Service;

import com.software.backend.models.ObraSocial;
import com.software.backend.persistence.repositories.IObraSocialRepository;
import com.software.backend.services.interfaces.IObraSocialService;

@Service
public class ObraSocialService extends GenericService<ObraSocial, Long, IObraSocialRepository> implements IObraSocialService{
}
