package CodeWare.store.mapper;

import CodeWare.store.dto.JogoDto;
import CodeWare.store.model.Jogo;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-10T14:58:08-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class JogoMapperImpl implements JogoMapper {

    @Override
    public JogoDto tDto(Jogo jogo) {
        if ( jogo == null ) {
            return null;
        }

        UUID id = null;
        String descricao = null;
        String nome = null;
        String lancamento = null;
        int preco = 0;
        String capa_diretorio = null;
        String estudio = null;

        id = jogo.getId();
        descricao = jogo.getDescricao();
        nome = jogo.getNome();
        lancamento = jogo.getLancamento();
        preco = jogo.getPreco();
        capa_diretorio = jogo.getCapa_diretorio();
        estudio = jogo.getEstudio();

        JogoDto jogoDto = new JogoDto( id, descricao, nome, lancamento, preco, capa_diretorio, estudio );

        return jogoDto;
    }

    @Override
    public Jogo toJogo(JogoDto jogoDto) {
        if ( jogoDto == null ) {
            return null;
        }

        Jogo jogo = new Jogo();

        jogo.setCapa_diretorio( jogoDto.capa_diretorio() );
        jogo.setDescricao( jogoDto.descricao() );
        jogo.setEstudio( jogoDto.estudio() );
        jogo.setId( jogoDto.id() );
        jogo.setLancamento( jogoDto.lancamento() );
        jogo.setNome( jogoDto.nome() );
        jogo.setPreco( jogoDto.preco() );

        return jogo;
    }

    @Override
    public void updateJogoFromDto(JogoDto jogoDto, Jogo jogo) {
        if ( jogoDto == null ) {
            return;
        }

        jogo.setCapa_diretorio( jogoDto.capa_diretorio() );
        jogo.setDescricao( jogoDto.descricao() );
        jogo.setEstudio( jogoDto.estudio() );
        jogo.setId( jogoDto.id() );
        jogo.setLancamento( jogoDto.lancamento() );
        jogo.setNome( jogoDto.nome() );
        jogo.setPreco( jogoDto.preco() );
    }
}
