package CodeWare.store.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import CodeWare.store.repository.VendaRepository;

@ExtendWith(MockitoExtension.class)
public class VendaServiceTest {

    @InjectMocks
    private VendaService vendaService;

    @Mock
    private VendaRepository vendaRepository;

    @Test
    @DisplayName("Testando se é possível deletar uma venda.")
    void testDeleteVenda() {
        Mockito.when(vendaRepository.existsById(1)).thenReturn(true);

        boolean del = vendaService.deleteVenda(1);
        Assertions.assertEquals(true, del);
    }

    @Test
    @DisplayName("Testando se ocorreu um erro para deletar uma venda.")
    void testNotDeleteVenda() {
        Mockito.when(vendaRepository.existsById(1)).thenReturn(false);

        boolean del = vendaService.deleteVenda(1);
        Assertions.assertEquals(false, del);
    }
}
