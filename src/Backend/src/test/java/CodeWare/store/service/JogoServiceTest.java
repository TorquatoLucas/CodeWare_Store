package CodeWare.store.service;

import java.util.ArrayList;
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
public class JogoServiceTest {

    @InjectMocks
    private JogoService jogoService;

    @Mock
    private JogoRepository jogoRepository;

    @Test
    @DisplayName("Retorna uma lista de jogos.")
    void testListJogos() {
        Jogo jogo = new Jogo();
        List<Jogo> lista = new ArrayList<>();
        lista.add(jogo);
        Mockito.when(jogoRepository.findAll()).thenReturn(lista);
        List<Jogo> jogos = jogoService.listJogos();
        Assertions.assertEquals(1, jogos.size());

    }
}
