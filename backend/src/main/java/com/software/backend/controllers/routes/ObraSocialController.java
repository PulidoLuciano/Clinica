package com.software.backend.controllers.routes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.backend.models.ObraSocial;
import com.software.backend.services.interfaces.ObraSocialService;

@RestController
@RequestMapping("/obras-sociales")
public class ObraSocialController {
    
    @Autowired
    ObraSocialService obraSocialService;

    @GetMapping("")
    public List<ObraSocial> getAllObrasSociales() {
        return obraSocialService.getAllObrasSociales();
    }
    
    @GetMapping("/{code}")
    public ObraSocial getObraSocialByCode(@PathVariable("code") Integer code) {
        return obraSocialService.getObraSocialbyCode(code);
    }

}
