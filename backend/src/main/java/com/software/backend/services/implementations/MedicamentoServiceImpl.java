package com.software.backend.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.backend.models.Medicamento;
import com.software.backend.persistence.repositories.interfaces.ApiSalud;
import com.software.backend.services.interfaces.MedicamentoService;

@Service
public class MedicamentoServiceImpl implements MedicamentoService{

    @Autowired
    ApiSalud apiSalud;
    
    @Override
    public Medicamento getMedicamentobyCode(int code) {
        return apiSalud.getMedicamentobyCode(code);
    }

    @Override
    public List<Medicamento> getAllMedicamentos(int pagina, int limite) {
        return apiSalud.getAllMedicamentos(pagina, limite);
    }

    @Override
    public List<Medicamento> getMedicamentosByDescription(String cadena) {
        return apiSalud.getMedicamentosByDescription(cadena);
    }

}
