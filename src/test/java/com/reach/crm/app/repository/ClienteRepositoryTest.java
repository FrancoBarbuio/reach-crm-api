package com.reach.crm.app.repository;

import com.reach.crm.app.model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClienteRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void debeGuardarYEncontrarUnClienteExitosamente() {
        Cliente nuevoCliente = new Cliente(
                "Empresa Noble",
                "Finanzas",
                "talento@noble.com",
                "0800-1234"
        );

        Cliente clienteGuardado = clienteRepository.save(nuevoCliente);
        assertThat(clienteGuardado.getId()).isNotNull();
        boolean existe = clienteRepository.existsByEmailContacto("talento@noble.com");
        assertThat(existe).isTrue();
    }
}