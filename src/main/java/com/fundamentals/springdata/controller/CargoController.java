package com.fundamentals.springdata.controller;

import com.fundamentals.springdata.entity.Cargo;
import com.fundamentals.springdata.entity.UF;
import com.fundamentals.springdata.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    CargoRepository cargoRepository;

    @GetMapping
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("cargo/home");

        modelAndView.addObject("cargos", cargoRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhe(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("cargo/detalhes");

        modelAndView.addObject("cargo", cargoRepository.getById(id));

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){
        ModelAndView modelAndView = new ModelAndView("cargo/formulario");

        modelAndView.addObject("cargo",new Cargo());

        return modelAndView;
    }
    @PostMapping({"/cadastrar", "/{id}/editar"})
    public String salvar(Cargo cargo){
        cargoRepository.save(cargo);

        return "redirect:/cargos";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id){

        ModelAndView modelAndView = new ModelAndView("cargo/formulario");
        modelAndView.addObject("cargo", cargoRepository.getById(id));
        modelAndView.addObject("ufs", UF.values());

        return modelAndView;
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id){
        cargoRepository.deleteById(id);

        return "redirect:/cargos";
    }

}
