package CodeWare.store.service;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import CodeWare.store.model.Jogo;
import CodeWare.store.repository.JogoRepository;

@ExtendWith(MockitoExtension.class)
public class JogoServicesTest {

    @InjectMocks
    private JogoService jogoService;

    @Mock
    private JogoRepository jogoRepository;

    @Test
    @DisplayName("Retorna uma lista de jogos.")
    void testListJogos() {
        Jogo jogo = new Jogo();
        Mockito.when(jogoRepository.findAll()).thenReturn(Collections.singletonList(jogo));
        List<Jogo> jogos = jogoService.listJogos();
        Assertions.assertEquals(1, jogos.size());

    }
}
