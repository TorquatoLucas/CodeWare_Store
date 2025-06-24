package CodeWare.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import CodeWare.store.model.Jogo;

public interface JogoRepository extends JpaRepository<Jogo,Integer> {

    Jogo findJogoByNome(String nome);

    Optional<Jogo> findById(Integer id);

    List<Jogo> findByNomeStartingWithIgnoreCase(String prefixo);

}
