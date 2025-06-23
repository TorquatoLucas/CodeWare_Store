package CodeWare.store.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import CodeWare.store.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,UUID>{

    Optional<Cliente> findById(UUID id);

    @Query(value = "SELECT * FROM cliente WHERE login = :login AND senha = :senha", nativeQuery = true)
    Cliente findByLoginAndSenha(@Param("login") String login,@Param("senha") String senha);

}
