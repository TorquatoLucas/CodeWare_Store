package CodeWare.store.dto;

// Lembrar de validar dps com @NotNull etc
public record VendaDto(

    Integer id, 
    
    String data, 

    int valor,
    
    Integer jogoId, 
    
    Integer clienteId
    
    ) {
}