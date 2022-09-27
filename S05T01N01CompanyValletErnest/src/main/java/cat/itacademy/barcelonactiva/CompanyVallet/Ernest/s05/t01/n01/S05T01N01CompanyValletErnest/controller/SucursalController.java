package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n01.S05T01N01CompanyValletErnest.controller;

import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n01.S05T01N01CompanyValletErnest.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n01.S05T01N01CompanyValletErnest.service.SucursalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SucursalController {

    @Autowired
    SucursalServiceImpl sucursalService;

    //Pàgina principal, mostra totes les sucursals
    @GetMapping(value = {"/", "/getAll"})
    public String viewHomePage(Model model, String keyword) {
        if (keyword != null) {
            model.addAttribute("listSucursals", sucursalService.findByKeyword(keyword));
        } else {
            model.addAttribute("listSucursals", sucursalService.getAll());
        }
        return "index";
    }

    //Formulari per crear una nova sucursal
    @GetMapping("/showNewSucursalForm")
    public String showNewSucursalForm(Model model) {
        model.addAttribute("sucursal", new SucursalDTO());
        return "new_sucursal";
    }

    //Mètode POST per afegir una nova sucursal a la BD i fer la validació
    @PostMapping("/add")
    public String saveSucursal(@Valid @ModelAttribute("sucursal") SucursalDTO sucursalDTO, BindingResult result, RedirectAttributes redAttrs) {
        if (result.hasErrors()) {
            return "new_sucursal";
        }
        sucursalService.saveSucursal(sucursalDTO);
        redAttrs.addFlashAttribute("msg", "Sucursal creada correctament");
        return "redirect:/";
    }

    //Obtenir una sucursal per id
    @GetMapping("/getOne")
    public String showSucursal(Integer id, Model model) {
        if (id != null) {
            model.addAttribute("listSucursals", sucursalService.getSucursalById(id));;
        } else {
            model.addAttribute("listSucursals", sucursalService.getAll());
        }
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteSucursal(@PathVariable("id") Integer id) {
        sucursalService.deleteSucursalById(id);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String showUpdateSucursalForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("sucursal", sucursalService.getSucursalById(id));
        return "update_sucursal";
    }

    @PostMapping("/updateSucursal")
    public String updateSucursal(@ModelAttribute SucursalDTO sucursalDTO) {
        sucursalService.updateSucursal(sucursalDTO.getId(), sucursalDTO);
        return "redirect:/";
    }

    //Per evitar que ens demane la icona
    @GetMapping("favicon.ico")
    @ResponseBody
    public void returnNoFavicon() {
    }
}
