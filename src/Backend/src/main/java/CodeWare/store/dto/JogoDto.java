package CodeWare.store.dto;


public record JogoDto(

    Integer id, 
    
    String descricao, 
    
    String nome,

    String lancamento,

    int preco,

    String capa_diretorio,

    String estudio

    
    ) {
}