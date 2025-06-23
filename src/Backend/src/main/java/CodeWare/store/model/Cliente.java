package CodeWare.store.model;
import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // Entidade JPA
@Table(name = "CLIENTE") // Nome da tabela
// Serializable permite que objetos dessa classe sejam
// convertidos em uma sequência de bytes (serialização)
public class Cliente implements Serializable { 
    
    // É um identificador de versão para controle de compatibilidade durante a serialização/desserialização
    private static final long serialVersionUID = 1L;


    @Id             
    @GeneratedValue(strategy = GenerationType.AUTO) // Tipo de geração automática
    private UUID id;                                // UUID: Identificador único universal

    @Column(nullable = false, unique = false)
    private String nome;
    @Column(columnDefinition = "CHAR(11)", nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false, unique =  false)
    private String senha;

    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // @OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    // private Set<Venda> vendas = new HashSet<>(); 



}
