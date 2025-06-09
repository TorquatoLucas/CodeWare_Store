package CodeWare.store.model;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity // Entidade JPA
@Table(name = "JOGO") // Nome da tabela
// Serializable permite que objetos dessa classe sejam
// convertidos em uma sequência de bytes (serialização)
public class Jogo implements Serializable { 
    
    // É um identificador de versão para controle de compatibilidade durante a serialização/desserialização
    private static final long serialVersionUID = 1L;

    @Id             
    @GeneratedValue(strategy = GenerationType.AUTO) // Tipo de geração automática
    private UUID id;                                // UUID: Identificador único universal

    @Column(nullable = false, unique = true)
    private String nome;
    @Column(nullable = false, unique =  false)
    private String descricao;
    @Column(nullable = false, unique =  false)
    private int preco;
    @Column(nullable = false, unique =  false)
    private String lancamento;
    @Column(nullable = false, unique = false)
    private String estudio;
    @Column(nullable = false, unique = false)
    private String capa_diretorio;


    // Será tirado de comentário quando implementar a tabela VENDA
    /* 
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "jogo",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Venda> vendas = new HashSet<>(); 
    */

}
