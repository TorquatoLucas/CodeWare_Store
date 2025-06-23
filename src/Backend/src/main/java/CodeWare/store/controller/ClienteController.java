package CodeWare.store.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CodeWare.store.dto.ClienteDto;
import CodeWare.store.dto.ClienteResponseDto;
import CodeWare.store.dto.LoginDto;
import CodeWare.store.exception.CredenciaisInvalidasException;
import CodeWare.store.model.Cliente;
import CodeWare.store.service.ClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cliente")
@AllArgsConstructor
public class ClienteController {

    
    private final ClienteService clienteService;

    // Lembrar de usar @Valid dps
    @PostMapping
    public ResponseEntity<Cliente> saveCliente(@Valid @RequestBody ClienteDto clienteDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.saveCliente(clienteDto));
    }

    // @GetMapping("/{id}/jogos")
    // public ResponseEntity<List<Jogo>> listJogo(@PathVariable UUID id){
    //     return ResponseEntity.ok(clienteService.listJogos(id));
    // }

    @GetMapping
    public ResponseEntity<List<Cliente>> listCliente(){
        return ResponseEntity.ok(clienteService.listClientes());
    }

    @PostMapping("/login")
    public ResponseEntity<ClienteResponseDto> verificarCliente(@RequestBody LoginDto loginDto) {
        ClienteResponseDto cliente = clienteService.verificarCliente(loginDto);

        if (cliente == null) {
            // Login falhou
            throw new CredenciaisInvalidasException();
        }

        // Login ok
        return ResponseEntity.ok(cliente);
    }




}
