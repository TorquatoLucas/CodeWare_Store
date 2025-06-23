package CodeWare.store.dto;

// Lembrar de validar dps com @NotNull etc
public record LoginDto(

    String login, 

    String senha
    
    ) {
}