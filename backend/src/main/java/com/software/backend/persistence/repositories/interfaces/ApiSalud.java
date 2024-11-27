package com.software.backend.persistence.repositories.interfaces;

import com.software.backend.models.Medicamento;
import com.software.backend.models.ObraSocial;

public interface ApiSalud {

    final String API_URL = "https://istp1service.azurewebsites.net";
    public Medicamento getMedicamentobyCode(int code);
    public ObraSocial getObraSocialbyCode(int code);
}
