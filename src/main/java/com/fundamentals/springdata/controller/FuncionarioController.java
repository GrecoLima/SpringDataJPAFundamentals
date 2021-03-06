package com.fundamentals.springdata.controller;

import com.fundamentals.springdata.entity.Funcionario;
import com.fundamentals.springdata.entity.UF;
import com.fundamentals.springdata.repository.CargoRepository;
import com.fundamentals.springdata.repository.FuncionarioRepository;
import com.fundamentals.springdata.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    public static final String FUNCIONARIO = "funcionario";
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @GetMapping
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("funcionario/home");

        modelAndView.addObject("funcionarios",funcionarioRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhe(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("funcionario/detalhes");

        modelAndView.addObject(FUNCIONARIO, funcionarioRepository.getById(id));
        modelAndView.addObject("projetos", projetoRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){
        ModelAndView modelAndView = new ModelAndView("funcionario/formulario");

        modelAndView.addObject(FUNCIONARIO,new Funcionario());
        modelAndView.addObject("ufs", UF.values());
        modelAndView.addObject("cargos", cargoRepository.findAll());

        return modelAndView;
    }
    @PostMapping({"/cadastrar", "/{id}/editar"})
    public String salvar(Funcionario funcionario){
        funcionarioRepository.save(funcionario);

        return "redirect:/funcionarios";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id){

        ModelAndView modelAndView = new ModelAndView("funcionario/formulario");
        modelAndView.addObject(FUNCIONARIO, funcionarioRepository.getById(id));
        modelAndView.addObject("ufs", UF.values());
        modelAndView.addObject("cargos", cargoRepository.findAll());


        return modelAndView;
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id){
        funcionarioRepository.deleteById(id);

        return "redirect:/funcionarios";
    }

}
