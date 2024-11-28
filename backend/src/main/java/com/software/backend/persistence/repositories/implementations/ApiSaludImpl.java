package com.software.backend.persistence.repositories.implementations;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

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

    @Override
    public List<Medicamento> getAllMedicamentos(int pagina, int limite) {
        try {
            if(pagina < 1 || limite < 1) throw new IllegalArgumentException("La página y el límite no pueden ser menor que uno");
            String uri = API_URL + "/api/servicio-salud/medicamentos/todos?pagina=" + pagina + "&limite=" + limite;
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Medicamento[]> response = restTemplate.getForEntity(URI.create(uri), Medicamento[].class);
    
            return Arrays.asList(response.getBody());
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("Error al obtener los medicamentos de la API Salud: " + e.getMessage());
        }
    }

    @Override
    public List<ObraSocial> getAllObrasSociales() {
        try {
            String uri = API_URL + "/api/servicio-salud/obras-sociales";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ObraSocial[]> response = restTemplate.getForEntity(URI.create(uri), ObraSocial[].class);
    
            return Arrays.asList(response.getBody());
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("Error al obtener las obras sociales de la API Salud: " + e.getMessage());
        }
    }

    @Override
    public List<Medicamento> getMedicamentosByDescription(String cadena) {
        if(cadena.length() < 3) throw new IllegalArgumentException("La cadena de busqueda debe tener mínimo tres caracteres");
        try {
            String uri = API_URL + "/api/servicio-salud/medicamentos?descripcion=" + cadena;
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Medicamento[]> response = restTemplate.getForEntity(URI.create(uri), Medicamento[].class);
    
            return Arrays.asList(response.getBody());
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("Error al obtener los medicamentos de la API Salud: " + e.getMessage());
        }
    }
}
