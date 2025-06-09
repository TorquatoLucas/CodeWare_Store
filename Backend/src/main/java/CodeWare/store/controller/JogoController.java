package CodeWare.store.controller;

import java.util.List;
import java.util.UUID;

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

import CodeWare.store.dto.JogoDto;
import CodeWare.store.model.Jogo;
import CodeWare.store.service.JogoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/jogo")
@AllArgsConstructor
public class JogoController {

    
    private final JogoService jogoService;

    @PostMapping
    public ResponseEntity<Jogo> saveJogo(@Valid @RequestBody JogoDto jogoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(jogoService.saveJogo(jogoDto));
    }


    @GetMapping
    public ResponseEntity<List<Jogo>> listJogos(){
        return ResponseEntity.ok(jogoService.listJogos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJogo(@PathVariable UUID id){
        if(jogoService.deleteJogo(id)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogo> editJogo(@PathVariable UUID id, @Valid @RequestBody JogoDto jogoDto){
        return ResponseEntity.ok(jogoService.updateJogo(id, jogoDto));
    }

}
