package com.software.backend.controllers.routes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.software.backend.models.Medicamento;
import com.software.backend.services.interfaces.MedicamentoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    MedicamentoService medicamentoService;

    @GetMapping("/todos")
    public List<Medicamento> getAllMedicamentos(@RequestParam("pagina") int pagina, @RequestParam("limite") int limite) {
        return medicamentoService.getAllMedicamentos(pagina, limite);
    }
    
    @GetMapping("/{code}")
    public Medicamento getMethodName(@PathVariable("code") Integer code) {
        return medicamentoService.getMedicamentobyCode(code);
    }

    @GetMapping("")
    public List<Medicamento> getMethodName(@RequestParam("descripcion") String descripcion) {
        return medicamentoService.getMedicamentosByDescription(descripcion);
    }
    
}
