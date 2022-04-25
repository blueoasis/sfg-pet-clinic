package guru.springframework.sfgpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PetController {
    
    @RequestMapping({"/pets","/pets/index", "/pets/index.html"})
    public String pets() {
        return "pets/index";
    }
}
