package com.software.backend.services.interfaces;

import java.util.List;

import com.software.backend.models.Medicamento;

public interface MedicamentoService {

    public Medicamento getMedicamentobyCode(int code);

    public List<Medicamento> getAllMedicamentos(int pagina, int limite);

    public List<Medicamento> getMedicamentosByDescription(String cadena);
}
