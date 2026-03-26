package com.reach.crm.app.dto;

import com.reach.crm.app.model.EtapaVenta;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OportunidadResponseDTO(
        Long id,
        String titulo,
        BigDecimal montoEsperado,
        EtapaVenta etapa,
        LocalDate fechaCierreEstimada,
        Long clienteId,
        String nombreEmpresa
) {}
