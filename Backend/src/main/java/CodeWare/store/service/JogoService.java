package CodeWare.store.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CodeWare.store.dto.JogoDto;
import CodeWare.store.mapper.JogoMapper;
import CodeWare.store.model.Jogo;
import CodeWare.store.repository.JogoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JogoService {
    
    private final JogoRepository jogoRepository;

    private JogoMapper jogoMapper;

    @Transactional
    public Jogo saveJogo(JogoDto jogoDto){
        Jogo jogo = jogoMapper.toJogo(jogoDto);

        return jogoRepository.save(jogo);
    }

    @Transactional(readOnly = true)
    public List<Jogo> listJogos(){
        return jogoRepository.findAll();
    }

    @Transactional
    public boolean deleteJogo(Integer id){
        if(jogoRepository.existsById(id)){
            jogoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public Jogo updateJogo(Integer id, JogoDto jogoDto) {
        // Verifica se o jogo existe
        Jogo jogo = jogoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Jogo com ID " + id + " não encontrado"));

        // Atualiza os campos
        jogo.setNome( jogoDto.nome() );
        jogo.setDescricao( jogoDto.descricao() );
        jogo.setPreco( jogoDto.preco() );
        jogo.setLancamento( jogoDto.lancamento() );
        jogo.setEstudio( jogoDto.estudio() );
        jogo.setCapa_diretorio( jogoDto.capa_diretorio() );

        // Salva e retorna o jogo atualizado
        return jogoRepository.save(jogo);
    }

}
