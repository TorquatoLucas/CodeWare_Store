package CodeWare.store.model;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // Entidade JPA
@Table(name = "VENDA") // Nome da tabela
// Serializable permite que objetos dessa classe sejam
// convertidos em uma sequência de bytes (serialização)
public class Venda implements Serializable { 
    
    // É um identificador de versão para controle de compatibilidade durante a serialização/desserialização
    private static final long serialVersionUID = 1L;

    @Id             
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tipo de geração automática
    private Integer id;                                

    @Column(nullable = false, unique = false)
    private String data;

    @Column(nullable = false, unique = false)
    private int valor;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    
    

}
