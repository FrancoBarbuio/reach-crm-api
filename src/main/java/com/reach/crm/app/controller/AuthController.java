package com.reach.crm.app.controller;

import com.reach.crm.app.dto.AuthRequestDTO;
import com.reach.crm.app.dto.AuthResponseDTO;
import com.reach.crm.app.model.Usuario;
import com.reach.crm.app.repository.UsuarioRepository;
import com.reach.crm.app.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService,
                          UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registrarEmpleado(@Valid @RequestBody AuthRequestDTO request) {
        if (usuarioRepository.findByUsername(request.username()).isPresent()) {
            return ResponseEntity.badRequest().body("El usuario ya existe.");
        }

        Usuario nuevoUsuario = new Usuario(
                request.username(),
                passwordEncoder.encode(request.password())
        );
        usuarioRepository.save(nuevoUsuario);

        return ResponseEntity.ok("Empleado registrado con éxito.");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        String token = jwtService.generarToken(authentication.getName());

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}