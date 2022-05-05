package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/","/index", "/index.html"})
    public String owners(Model model) {
        model.addAttribute("owners",this.ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping({"/find"})
    public String findOwners(Model model) {
        model.addAttribute("owner",null);
        return "owners/findOwners";
    }

    @RequestMapping({"/list"})
    public String listOwners() {
        return "owners/ownersList";
    }

    @RequestMapping({"/details"})
    public String ownerDetails(Model model) {

        model.addAttribute("owner",ownerService.findById(1L));
        return "owners/ownerDetails";
    }
}
