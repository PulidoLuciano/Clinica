package com.software.backend.services.interfaces;

import java.util.List;

import com.software.backend.models.ObraSocial;

public interface ObraSocialService {

    public ObraSocial getObraSocialbyCode(int code);

    public List<ObraSocial> getAllObrasSociales();
}
