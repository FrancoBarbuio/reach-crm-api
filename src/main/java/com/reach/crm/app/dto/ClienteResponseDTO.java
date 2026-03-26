package com.reach.crm.app.dto;

public record ClienteResponseDTO(
        Long id,
        String nombreEmpresa,
        String sector,
        String emailContacto,
        String telefono
) {}