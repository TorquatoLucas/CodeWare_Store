package CodeWare.store.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> listJogo(@PathVariable Integer id){
        return ResponseEntity.ok(jogoService.listJogo(id));
    }


    @GetMapping
    public ResponseEntity<List<Jogo>> listJogos(){
        return ResponseEntity.ok(jogoService.listJogos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJogo(@PathVariable Integer id){
        if(jogoService.deleteJogo(id)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogo> editJogo(@PathVariable Integer id, @Valid @RequestBody JogoDto jogoDto){
        return ResponseEntity.ok(jogoService.updateJogo(id, jogoDto));
    }

    @PostMapping("/upload-imagem")
    public ResponseEntity<String> uploadImagem(@RequestParam("imagem") MultipartFile imagem) {
        try {
            // Cria a pasta se ainda não existir
            File pasta = new File(System.getProperty("user.dir") + "/uploads/imagens/");
            pasta.mkdirs(); // garante que as pastas existam

            // Gera nome único para a imagem
            String nomeImagem = UUID.randomUUID() + "-" + imagem.getOriginalFilename();

            // Define o caminho de destino
            Path destino = Paths.get(pasta.getAbsolutePath(), nomeImagem);

            // Salva a imagem
            Files.copy(imagem.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

            // Retorna o caminho relativo para usar no frontend
            return ResponseEntity.ok("uploads/imagens/" + nomeImagem);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar imagem.");
        }
    }   
}
