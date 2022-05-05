package guru.springframework.sfgpetclinic.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    @RequestMapping({"","/","index","index.html"})
    public String index() {
        return "welcome";
    }
}
