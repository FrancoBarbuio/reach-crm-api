package com.reach.crm.app.controller;

import com.reach.crm.app.dto.OportunidadRegistroDTO;
import com.reach.crm.app.dto.OportunidadResponseDTO;
import com.reach.crm.app.model.EtapaVenta;
import com.reach.crm.app.service.OportunidadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oportunidades")
public class OportunidadController {

    private final OportunidadService oportunidadService;

    public OportunidadController(OportunidadService oportunidadService) {
        this.oportunidadService = oportunidadService;
    }

    @PostMapping
    public ResponseEntity<OportunidadResponseDTO> registrarOportunidad(@Valid @RequestBody OportunidadRegistroDTO dto) {
        OportunidadResponseDTO nuevaOportunidad = oportunidadService.registrarOportunidad(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaOportunidad);
    }

    @GetMapping
    public ResponseEntity<List<OportunidadResponseDTO>> listarOportunidades() {
        return ResponseEntity.ok(oportunidadService.obtenerTodasLasOportunidades());
    }

    @PutMapping("/{id}/etapa")
    public ResponseEntity<OportunidadResponseDTO> actualizarEtapa(
            @PathVariable Long id,
            @RequestParam EtapaVenta etapa) {

        OportunidadResponseDTO oportunidadActualizada = oportunidadService.actualizarEtapa(id, etapa);
        return ResponseEntity.ok(oportunidadActualizada);
    }
}