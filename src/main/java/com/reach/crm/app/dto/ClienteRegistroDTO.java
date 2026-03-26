package com.reach.crm.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRegistroDTO(
        @NotBlank(message = "El nombre de la empresa es obligatorio")
        String nombreEmpresa,

        String sector,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El formato del email no es válido")
        String emailContacto,

        String telefono
) {}