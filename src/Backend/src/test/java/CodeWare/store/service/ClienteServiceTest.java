package CodeWare.store.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import CodeWare.store.dto.ClienteDto;
import CodeWare.store.mapper.ClienteMapper;
import CodeWare.store.model.Cliente;
import CodeWare.store.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteMapper clienteMapper;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    @DisplayName("Verifica se o cliente está sendo salvo.")
    public void testSaveCliente() {
        ClienteDto clienteDto = new ClienteDto(null, "12345678901", "guilhermefreire@gmail.com", "Gui", "gui123");
        Cliente cliente = clienteMapper.toCliente(clienteDto);
        
        Mockito.when(clienteMapper.toCliente(clienteDto)).thenReturn(cliente);
        Mockito.when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente cliente1 = clienteService.saveCliente(clienteDto);
        Assertions.assertEquals(cliente, cliente1);
    }
}
