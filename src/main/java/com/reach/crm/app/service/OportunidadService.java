package com.reach.crm.app.service;

import com.reach.crm.app.dto.OportunidadRegistroDTO;
import com.reach.crm.app.dto.OportunidadResponseDTO;
import com.reach.crm.app.model.Cliente;
import com.reach.crm.app.model.EtapaVenta;
import com.reach.crm.app.model.Oportunidad;
import com.reach.crm.app.repository.ClienteRepository;
import com.reach.crm.app.repository.OportunidadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OportunidadService {
    private final OportunidadRepository oportunidadRepository;
    private final ClienteRepository clienteRepository;

    public OportunidadService(OportunidadRepository oportunidadRepository, ClienteRepository clienteRepository) {
        this.oportunidadRepository = oportunidadRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public OportunidadResponseDTO registrarOportunidad(OportunidadRegistroDTO dto){
        Cliente clienteAsignado = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new IllegalArgumentException("No existe un cliente con el ID: " +dto.clienteId()));

        Oportunidad nuevaOportunidad = new Oportunidad(
                dto.titulo(),
                dto.montoEsperado(),
                dto.etapa(),
                dto.fechaCierreEstimada(),
                clienteAsignado
        );

        Oportunidad oportunidadGuardada = oportunidadRepository.save(nuevaOportunidad);
        return mapToDTO(oportunidadGuardada);
    }
    public List<OportunidadResponseDTO> obtenerTodasLasOportunidades() {
        return oportunidadRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Transactional
    public OportunidadResponseDTO actualizarEtapa(Long id, EtapaVenta nuevaEtapa) {
        Oportunidad oportunidad = oportunidadRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe la oportunidad con ID: " + id));
        oportunidad.setEtapa(nuevaEtapa);

        Oportunidad actualizada = oportunidadRepository.save(oportunidad);
        return mapToDTO(actualizada);
    }

    private OportunidadResponseDTO mapToDTO(Oportunidad oportunidad) {
        return new OportunidadResponseDTO(
                oportunidad.getId(),
                oportunidad.getTitulo(),
                oportunidad.getMontoEsperado(),
                oportunidad.getEtapa(),
                oportunidad.getFechaCierreEstimada(),
                oportunidad.getCliente().getId(),
                oportunidad.getCliente().getNombreEmpresa()
        );
    }
}
