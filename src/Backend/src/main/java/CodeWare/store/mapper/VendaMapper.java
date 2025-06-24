package CodeWare.store.mapper;

import org.mapstruct.Mapper;


@Mapper (componentModel = "spring")
public interface VendaMapper {

    // Não tem conversão automáca, pois vendaDto tem ID e venda-entidade tem O próprio jogo.

}
