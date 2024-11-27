package com.software.backend.persistence.repositories.implementations;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.software.backend.models.Medicamento;
import com.software.backend.models.ObraSocial;
import com.software.backend.persistence.repositories.interfaces.ApiSalud;

@Component
public class ApiSaludImpl implements ApiSalud{

    @Override
    public Medicamento getMedicamentobyCode(int code) {
        try {
            String uri = API_URL + "/api/servicio-salud/medicamentos/" + code;
            RestTemplate restTemplate = new RestTemplate();  
            ResponseEntity<Medicamento> response = restTemplate.getForEntity(URI.create(uri), Medicamento.class);

            Medicamento medicamento = response.getBody();

            return medicamento;
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("El código " + code + " no existe entre los recursos medicamentos de la API Salud");
        }
    }

    @Override
    public ObraSocial getObraSocialbyCode(int code) {
        try {
            String uri = API_URL + "/api/servicio-salud/obras-sociales/" + code;
            RestTemplate restTemplate = new RestTemplate();  
            ResponseEntity<ObraSocial> response = restTemplate.getForEntity(URI.create(uri), ObraSocial.class);

            ObraSocial obraSocial = response.getBody();

            return obraSocial;
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("El código " + code + " no existe entre los recursos obras-sociales de la API Salud");
        }
    }
}
