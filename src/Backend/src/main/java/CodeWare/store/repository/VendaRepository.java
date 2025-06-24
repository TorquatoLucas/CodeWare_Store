package CodeWare.store.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import CodeWare.store.model.Venda;

public interface VendaRepository extends JpaRepository<Venda,Integer>{


    Venda findVendaById(Integer id);
    
    @Query(value = "SELECT * FROM venda WHERE jogo_id = :id", nativeQuery = true)
    List<Venda> findVendaByJogoId(@Param("id") Integer id);

    @Query(value = "SELECT * FROM venda WHERE cliente_id = :id", nativeQuery = true)
    List<Venda> findVendaByClienteId(@Param("id") Integer id);

    @Query(value = "SELECT jogo_id FROM venda WHERE venda_id = :id", nativeQuery = true)
    UUID findJogoIdByVendaId(@Param("id") Integer id);

    
}
