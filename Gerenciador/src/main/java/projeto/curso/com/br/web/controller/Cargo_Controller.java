package projeto.curso.com.br.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.Cargo;
import domain.Departamento;
import service.CargoService;
import service.DepartamentoService;

@RestController
public class Cargo_Controller {

    @Autowired
    private CargoService service;

    @Autowired
    private DepartamentoService departamentoService;

    /**
     * Método para exibir a página de cadastro de cargos.
     * 
     * @return ModelAndView objeto que contém a vista "cargo/cadastro" e os objetos necessários para o formulário.
     */
    @GetMapping("/cargoscadastrar")
    public ModelAndView cadastrar() {
        ModelAndView md = new ModelAndView();
        md.setViewName("cargo/cadastro");
        md.addObject("cargo", new Cargo());
        md.addObject("departamento", departamentoService.listarTodos());
        return md;
    }

    /**
     * Método para exibir a lista de todos os cargos cadastrados.
     * 
     * @return ModelAndView objeto que contém a vista "cargo/lista" e a lista de cargos.
     */
    @GetMapping("/cargoslistar")
    public ModelAndView listar() {
        ModelAndView md = new ModelAndView();
        md.setViewName("cargo/lista");
        md.addObject("cargo", service.Listartudo());
        return md;
    }

    /**
     * Método para preparar a edição de um cargo existente.
     * 
     * @param id ID do cargo a ser editado.
     * @return ModelAndView objeto que contém a vista "cargo/cadastro" e os dados do cargo a ser editado.
     */
    @GetMapping("/cargos_editar/{id}")
    public ModelAndView preEditar(@PathVariable("id") Long id) {
        ModelAndView md = new ModelAndView();
        md.addObject("departamento", departamentoService.listarTodos());
        md.addObject("cargo", service.ListarID(id));
        md.setViewName("cargo/cadastro.html");
        return md;
    }

    /**
     * Método para editar um cargo existente.
     * 
     * @param cargo Objeto Cargo a ser editado.
     * @param redirectAttributes Atributos para mensagens de redirecionamento.
     * @return ModelAndView objeto que redireciona para a página de cadastro de cargos.
     */
    @PostMapping("/cargos/editar")
    public ModelAndView editar(Cargo cargo, RedirectAttributes redirectAttributes) {
        try {
            service.Edit(cargo);
            redirectAttributes.addFlashAttribute("successMessage", "Cargo atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao atualizar cargo: " + e.getMessage());
        }
        ModelAndView md = new ModelAndView("redirect:/cargoscadastrar");
        return md;
    }

    /**
     * Método para excluir um cargo, desde que ele não possua funcionários associados.
     * 
     * @param id ID do cargo a ser excluído.
     * @param redirectAttributes Atributos para mensagens de redirecionamento.
     * @return ModelAndView objeto que redireciona para a lista de cargos.
     */
    @PostMapping("/cargos/excluir")
    public ModelAndView excluir(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        if (service.temFuncionario(id)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cargo possui funcionários associados e não pode ser excluído.");
            mv.setViewName("redirect:/cargoslistar");
        } else {
            service.Excluir(id);
            redirectAttributes.addFlashAttribute("successMessage", "Cargo excluído com sucesso!");
            mv.setViewName("redirect:/cargoslistar");
        }
        return mv;
    }

    /**
     * Método para salvar um novo cargo.
     * 
     * @param cargo Objeto Cargo a ser salvo.
     * @param redirectAttributes Atributos para mensagens de redirecionamento.
     * @return ModelAndView objeto que redireciona para a página de cadastro de cargos.
     */
    @PostMapping("/cargos/salvar")
    public ModelAndView salvar(Cargo cargo, RedirectAttributes redirectAttributes) {
        try {
            service.Salvar(cargo);
            redirectAttributes.addFlashAttribute("successMessage", "Cargo cadastrado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cadastrar cargo: " + e.getMessage());
        }
        return new ModelAndView("redirect:/cargoscadastrar");
    }

    /**
     * Método para listar todos os departamentos.
     * 
     * @return Lista de departamentos.
     */
    @ModelAttribute("departamento")
    public List<Departamento> listarDepartamentos() {
        return departamentoService.listarTodos();
    }

    /**
     * Método para buscar a página de lista de cargos a partir do arquivo Funcionario/listar.html.
     * 
     * @return ModelAndView objeto que contém a vista "cargo/lista" e a lista de cargos.
     */
    @GetMapping("/funcionarios/buscar/cargoslistar")
    public ModelAndView ConexaoBuscarPor___() {
        ModelAndView md = new ModelAndView();
        md.setViewName("cargo/lista");
        md.addObject("cargo", service.Listartudo());
        return md;
    }

    /**
     * Método para buscar a página de cadastro de cargos a partir do arquivo Funcionario/listar.html.
     * 
     * @return ModelAndView objeto que contém a vista "cargo/cadastro" e os objetos necessários para o formulário.
     */
    @GetMapping("/funcionarios/buscar/cargoscadastrar")
    public ModelAndView ConexaobuscarPor__() {
        ModelAndView md = new ModelAndView();
        md.setViewName("cargo/cadastro");
        md.addObject("cargo", new Cargo());
        md.addObject("departamento", departamentoService.listarTodos());
        return md;
    }
}
