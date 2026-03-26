package com.reach.crm.app.service;

import com.reach.crm.app.dto.ClienteRegistroDTO;
import com.reach.crm.app.dto.ClienteResponseDTO;
import com.reach.crm.app.model.Cliente;
import com.reach.crm.app.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public ClienteResponseDTO registrarCliente(ClienteRegistroDTO dto) {
        if (clienteRepository.existsByEmailContacto(dto.emailContacto())) {
            throw new IllegalArgumentException("Ya existe un cliente registrado con ese email.");
        }

        Cliente nuevoCliente = new Cliente(
                dto.nombreEmpresa(),
                dto.sector(),
                dto.emailContacto(),
                dto.telefono()
        );

        Cliente clienteGuardado = clienteRepository.save(nuevoCliente);

        return mapToDTO(clienteGuardado);
    }

    public List<ClienteResponseDTO> obtenerTodosLosClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Transactional
    public ClienteResponseDTO actualizarCliente(Long id, ClienteRegistroDTO dto) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El cliente con ID " + id + " no existe."));

        if (!clienteExistente.getEmailContacto().equals(dto.emailContacto()) &&
                clienteRepository.existsByEmailContacto(dto.emailContacto())) {
            throw new IllegalArgumentException("Ya existe otro cliente con ese email.");
        }

        clienteExistente.setNombreEmpresa(dto.nombreEmpresa());
        clienteExistente.setSector(dto.sector());
        clienteExistente.setEmailContacto(dto.emailContacto());
        clienteExistente.setTelefono(dto.telefono());

        Cliente clienteActualizado = clienteRepository.save(clienteExistente);

        return mapToDTO(clienteActualizado);
    }

    @Transactional
    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("El cliente con ID " + id + " no existe.");
        }
        clienteRepository.deleteById(id);
    }

    private ClienteResponseDTO mapToDTO(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNombreEmpresa(),
                cliente.getSector(),
                cliente.getEmailContacto(),
                cliente.getTelefono()
        );
    }
}