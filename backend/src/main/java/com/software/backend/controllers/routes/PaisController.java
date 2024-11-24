package com.software.backend.controllers.routes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.backend.controllers.dtos.PaisConProvinciaDTO;
import com.software.backend.controllers.dtos.mappers.PaisConProvinciaMapper;
import com.software.backend.models.Pais;
import com.software.backend.services.interfaces.PaisService;
import com.software.backend.controllers.dtos.mappers.ProvinciaConLocalidadMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.software.backend.controllers.dtos.PaisSinProvinciaDTO;
import com.software.backend.controllers.dtos.ProvinciaConLocalidadDTO;
import com.software.backend.controllers.dtos.mappers.PaisSinProvinciaMapper;
import com.software.backend.models.Provincia;


@RestController
@RequestMapping("/paises")
public class PaisController
        extends GenericController<Pais, String, PaisService, PaisSinProvinciaDTO, PaisSinProvinciaMapper> {

    @Autowired
    private ProvinciaConLocalidadMapper ProvinciaConLocalidadMapper;

    @Autowired
    private PaisConProvinciaMapper PaisConProvinciaMapper;



    @GetMapping("/{nombre}/provincias")
    public ResponseEntity<PaisConProvinciaDTO> getPaisConProvincias(@PathVariable("nombre") String nombrePais) {
        Optional<Pais> pais = super.getServicio().getById(nombrePais);
        if (pais.isEmpty())
            throw new IllegalArgumentException("No existe un pais con ese nombre");
        
        PaisConProvinciaDTO paisDto = PaisConProvinciaMapper.toDTO(pais.get());
        return ResponseEntity.ok(paisDto);
    }

    @GetMapping("/{nombre}/{nombreProvincia}")
    public ResponseEntity<ProvinciaConLocalidadDTO> getProvinciaConLocalidades(@PathVariable("nombre") String nombrePais,@PathVariable("nombreProvincia") String nombreProvincia) {
        Optional<Provincia> provincia = super.getServicio().getProvincia(nombrePais, nombreProvincia);
        if (provincia.isEmpty())
            throw new IllegalArgumentException("No existe una provincia con ese con ese nombre");

        ProvinciaConLocalidadDTO provinciaConLocalidadDTO = ProvinciaConLocalidadMapper.toDTO(provincia.get());
        return ResponseEntity.ok(provinciaConLocalidadDTO);
    }


}
