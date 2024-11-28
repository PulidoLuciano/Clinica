package com.software.backend.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.backend.models.ObraSocial;
import com.software.backend.persistence.repositories.interfaces.ApiSalud;
import com.software.backend.services.interfaces.ObraSocialService;

@Service
public class ObraSocialServiceImpl implements ObraSocialService{

    @Autowired
    ApiSalud apiSalud;
    
    @Override
    public ObraSocial getObraSocialbyCode(int code) {
        return apiSalud.getObraSocialbyCode(code);
    }

    @Override
    public List<ObraSocial> getAllObrasSociales() {
        return apiSalud.getAllObrasSociales();
    }
}
