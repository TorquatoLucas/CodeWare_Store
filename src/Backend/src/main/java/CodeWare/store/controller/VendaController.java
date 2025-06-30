package CodeWare.store.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CodeWare.store.dto.VendaDto;
import CodeWare.store.model.Venda;
import CodeWare.store.service.VendaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/venda")
@AllArgsConstructor
public class VendaController {

    
    private final VendaService vendaService;

    // Lembrar de usar @Valid dps
    @PostMapping
    public ResponseEntity<Venda> saveVenda(@Valid @RequestBody VendaDto vendaDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.saveVenda(vendaDto));
    }


    @GetMapping
    public ResponseEntity<List<Venda>> listVendas(){
        return ResponseEntity.ok(vendaService.listVendas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> listVenda(@PathVariable Integer id){
        return ResponseEntity.ok(vendaService.listVenda(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> editVenda(@PathVariable Integer id, @Valid @RequestBody VendaDto vendaDto){
        return ResponseEntity.ok(vendaService.updateVenda(id, vendaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenda(@PathVariable Integer id){
        if(vendaService.deleteVenda(id)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
