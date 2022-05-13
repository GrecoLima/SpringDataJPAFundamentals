package com.fundamentals.springdata.controller;

import com.fundamentals.springdata.entity.Cliente;
import com.fundamentals.springdata.entity.Projeto;
import com.fundamentals.springdata.entity.UF;
import com.fundamentals.springdata.repository.ClienteRepository;
import com.fundamentals.springdata.repository.FuncionarioRepository;
import com.fundamentals.springdata.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {
    
    @Autowired
    ProjetoRepository projetoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @GetMapping
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("projeto/home");

        modelAndView.addObject("projetos", projetoRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhe(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("projeto/detalhes");

        modelAndView.addObject("projeto", projetoRepository.getById(id));

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){
        ModelAndView modelAndView = new ModelAndView("projeto/formulario");

        modelAndView.addObject("projeto",new Projeto());
        modelAndView.addObject("clientes", clienteRepository.findAll());
        modelAndView.addObject("lideres", funcionarioRepository.findByCargoNome("Gerente"));
        modelAndView.addObject("funcionarios",  funcionarioRepository.findByCargoNomeNot    ("Gerente"));

        return modelAndView;
    }
    @PostMapping({"/cadastrar", "/{id}/editar"})
    public String salvar(Projeto projeto){
        projetoRepository.save(projeto);

        return "redirect:/projetos";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id){

        ModelAndView modelAndView = new ModelAndView("projeto/formulario");
        modelAndView.addObject("projeto", projetoRepository.getById(id));
        modelAndView.addObject("clientes", clienteRepository.findAll());
        modelAndView.addObject("lideres", funcionarioRepository.findAll());
        modelAndView.addObject("funcionarios",  funcionarioRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id){
        projetoRepository.deleteById(id);

        return "redirect:/projetos";
    }

}
