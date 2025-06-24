package CodeWare.store.dto;


import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;


// Lembrar de validar dps com @NotNull etc
public record ClienteDto(

    Integer id, 
    
    @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 dígitos")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas números")
    String cpf,

    String login, 

    String nome, 

    String senha
    
    ) {
}