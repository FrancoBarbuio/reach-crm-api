package com.reach.crm.app.dto;

import com.reach.crm.app.model.EtapaVenta;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OportunidadRegistroDTO(
    @NotBlank(message = "El título es obligatorio")
    String titulo,

    @NotNull(message = "El monto esperado es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    BigDecimal montoEsperado,

    @NotNull(message = "La etapa de venta es obligatoria")
    EtapaVenta etapa,

    @NotNull(message = "La fecha de cierre es obligatoria")
    @FutureOrPresent(message = "La fecha de cierre no puede ser en el pasado")
    LocalDate fechaCierreEstimada,

    @NotNull(message = "El ID del cliente es obligatorio")
    Long clienteId

){}

