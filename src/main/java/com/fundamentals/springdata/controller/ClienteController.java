package com.fundamentals.springdata.controller;

import ch.qos.logback.core.net.server.Client;
import com.fundamentals.springdata.entity.Cliente;
import com.fundamentals.springdata.entity.UF;
import com.fundamentals.springdata.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("cliente/home");

        modelAndView.addObject("clientes",clienteRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhe(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("cliente/detalhes");

        modelAndView.addObject("cliente", clienteRepository.getById(id));

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){
        ModelAndView modelAndView = new ModelAndView("cliente/formulario");

        modelAndView.addObject("cliente",new Cliente());
        modelAndView.addObject("ufs", UF.values());

        return modelAndView;
    }
    @PostMapping({"/cadastrar", "/{id}/editar"})
    public String salvar(Cliente cliente){
        clienteRepository.save(cliente);

        return "redirect:/clientes";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id){

        ModelAndView modelAndView = new ModelAndView("cliente/formulario");
        modelAndView.addObject("cliente", clienteRepository.getById(id));
        modelAndView.addObject("ufs", UF.values());

        return modelAndView;
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id){
        clienteRepository.deleteById(id);

        return "redirect:/clientes";
    }
}
