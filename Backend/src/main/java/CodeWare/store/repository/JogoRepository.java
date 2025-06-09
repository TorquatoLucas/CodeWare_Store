package CodeWare.store.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import CodeWare.store.model.Jogo;

public interface JogoRepository extends JpaRepository<Jogo,UUID> {

    Jogo findJogoByNome(String nome);

    Optional<Jogo> findById(UUID id);

}
