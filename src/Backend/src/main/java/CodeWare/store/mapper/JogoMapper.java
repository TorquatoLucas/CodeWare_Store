package CodeWare.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import CodeWare.store.dto.JogoDto;
import CodeWare.store.model.Jogo;

@Mapper (componentModel = "spring")
public interface JogoMapper {

    
    JogoDto tDto(Jogo jogo);
    Jogo toJogo(JogoDto jogoDto);
    void updateJogoFromDto(JogoDto jogoDto, @MappingTarget Jogo jogo);


}
