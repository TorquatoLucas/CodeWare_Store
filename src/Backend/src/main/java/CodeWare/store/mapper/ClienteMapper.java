package CodeWare.store.mapper;

import org.mapstruct.Mapper;

import CodeWare.store.dto.ClienteDto;
import CodeWare.store.dto.ClienteResponseDto;
import CodeWare.store.model.Cliente;

@Mapper (componentModel = "spring")
public interface ClienteMapper {

    
    ClienteDto tDto(Cliente cliente);
    Cliente toCliente(ClienteDto clienteDto);
    ClienteResponseDto toResponseDto(Cliente cliente);

}
