package CodeWare.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

     @GetMapping("/")
    public String home() {
        return "redirect:/html/index.html";
    }

}
