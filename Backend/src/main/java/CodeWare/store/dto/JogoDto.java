package CodeWare.store.dto;

import java.util.UUID;

public record JogoDto(

    UUID id, 
    
    String descricao, 
    
    String nome,

    String lancamento,

    int preco,

    String capa_diretorio,

    String estudio

    
    ) {
}