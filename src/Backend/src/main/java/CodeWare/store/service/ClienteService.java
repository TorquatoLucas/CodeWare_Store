package CodeWare.store.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CodeWare.store.dto.ClienteDto;
import CodeWare.store.dto.ClienteResponseDto;
import CodeWare.store.dto.LoginDto;
import CodeWare.store.mapper.ClienteMapper;
import CodeWare.store.model.Cliente;
import CodeWare.store.repository.ClienteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    //private final VendaRepository vendaRepository;

    private ClienteMapper clienteMapper;

    @Transactional
    public Cliente saveCliente(ClienteDto clienteto){

        Cliente cliente = clienteMapper.toCliente(clienteto);

        /*
        cliente.setNome(clienteRecordDto.nome());
        cliente.setLogin(clienteRecordDto.login());
        cliente.setSenha(clienteRecordDto.senha());
        cliente.setCpf(clienteRecordDto.cpf());
        */

        return clienteRepository.save(cliente);
    }

    // @Transactional(readOnly = true)
    // public List<Jogo> listJogos(UUID clienteId) {
    //     List<Venda> vendas = vendaRepository.findVendaByClienteId(clienteId);
    //     return vendas.stream()
    //              .map(Venda::getJogo)
    //              .collect(Collectors.toList());
    // }

    @Transactional(readOnly = true)
    public List<Cliente> listClientes(){
        return clienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ClienteResponseDto verificarCliente(LoginDto loginDto){

        ClienteResponseDto cliente = clienteMapper.toResponseDto(clienteRepository.findByLoginAndSenha(loginDto.login(),loginDto.senha()));

        return cliente;

    }

}
