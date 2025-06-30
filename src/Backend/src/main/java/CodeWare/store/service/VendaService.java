package CodeWare.store.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CodeWare.store.dto.VendaDto;
import CodeWare.store.model.Jogo;
import CodeWare.store.model.Venda;
import CodeWare.store.repository.ClienteRepository;
import CodeWare.store.repository.JogoRepository;
import CodeWare.store.repository.VendaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VendaService {
    
    private final JogoRepository jogoRepository;
    private final ClienteRepository clienteRepository;
    private final VendaRepository vendaRepository;



    @Transactional
    public Venda saveVenda(VendaDto vendaDto){
        Venda venda = new Venda();

        Jogo jogo = jogoRepository.findById(vendaDto.jogoId()).get();

        venda.setData(vendaDto.data());
        venda.setValor(jogo.getPreco());
        venda.setJogo(jogo);
        venda.setCliente(clienteRepository.findById(vendaDto.clienteId()).get());
        

        return vendaRepository.save(venda);
    }

    @Transactional(readOnly = true)
    public List<Venda> listVendas(){
        return vendaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Venda listVenda(Integer id){
        return vendaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Venda com ID " + id + " não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Venda> listVendaByClienteId(Integer id){
        return vendaRepository.findVendaByClienteId(id);
    }

   @Transactional
    public Venda updateVenda(Integer id, VendaDto vendaDto) {
        Venda venda = vendaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Venda com ID " + id + " não encontrada"));

        venda.setData(vendaDto.data());
        venda.setCliente(clienteRepository.findById(vendaDto.clienteId())
            .orElseThrow(() -> new IllegalArgumentException("Cliente com ID " + vendaDto.clienteId() + " não encontrado")));
        venda.setJogo(jogoRepository.findById(vendaDto.jogoId())
            .orElseThrow(() -> new IllegalArgumentException("Jogo com ID " + vendaDto.jogoId() + " não encontrado")));
        venda.setValor(vendaDto.valor());

        return vendaRepository.save(venda);
}


    @Transactional
    public boolean deleteVenda(Integer id){
        if(vendaRepository.existsById(id)){
            vendaRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

}
