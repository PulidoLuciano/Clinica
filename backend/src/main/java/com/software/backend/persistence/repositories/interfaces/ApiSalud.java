package com.software.backend.persistence.repositories.interfaces;

import java.util.List;

import com.software.backend.models.Medicamento;
import com.software.backend.models.ObraSocial;

public interface ApiSalud {
    final String API_URL = "https://istp1service.azurewebsites.net";
    public Medicamento getMedicamentobyCode(int code);
    public ObraSocial getObraSocialbyCode(int code);
    public List<Medicamento> getAllMedicamentos(int pagina, int limite);
    public List<ObraSocial> getAllObrasSociales();
    public List<Medicamento> getMedicamentosByDescription(String cadena);
}
