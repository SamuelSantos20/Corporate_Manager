package projeto.curso.com.br.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.Departamento;
import service.DepartamentoService;

@Controller
public class Departamento_Controller {

    @Autowired
    private DepartamentoService service;

    /**
     * Método para exibir a página de cadastro de departamentos.
     * 
     * @param departamento Objeto Departamento que será vinculado ao formulário.
     * @return ModelAndView objeto que contém a vista "departamento/cadastro".
     */
    @GetMapping("/departamentoscadastrar")
    public ModelAndView cadastrar(Departamento departamento) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("departamento/cadastro");
        return mv;
    }

    /**
     * Método para exibir a lista de todos os departamentos cadastrados.
     * 
     * @return ModelAndView objeto que contém a lista de departamentos e a vista "departamento/lista".
     */
    @GetMapping("/departamentoslistar")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("departamentos", service.listarTodos());
        mv.setViewName("departamento/lista");
        return mv;
    }

    /**
     * Método para excluir um departamento, desde que ele não possua cargos associados.
     * 
     * @param id ID do departamento a ser excluído.
     * @param redirectAttributes Atributos para mensagens de redirecionamento.
     * @return ModelAndView objeto que redireciona para a lista de departamentos.
     */
    @PostMapping("/departamentos/excluir")
    public ModelAndView excluir(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        if (!service.departamentoTemCargos(id)) {
            service.excluir(id);
            redirectAttributes.addFlashAttribute("successMessage", "Departamento excluído com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Departamento possui cargos associados e não pode ser excluído.");
        }
        mv.setViewName("redirect:/departamentoslistar");
        return mv;
    }

    /**
     * Método para salvar um novo departamento.
     * 
     * @param departamento Objeto Departamento a ser salvo.
     * @param redirectAttributes Atributos para mensagens de redirecionamento.
     * @return ModelAndView objeto que redireciona para a página de cadastro de departamentos.
     */
    @PostMapping("/salvar")
    public ModelAndView salvar(Departamento departamento, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            service.salvar(departamento);
            redirectAttributes.addFlashAttribute("successMessage", "Departamento cadastrado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cadastrar departamento: " + e.getMessage());
        }
        mv.setViewName("redirect:/departamentoscadastrar");
        return mv;
    }

    /**
     * Método para preparar a edição de um departamento existente.
     * 
     * @param id ID do departamento a ser editado.
     * @return ModelAndView objeto que contém o departamento a ser editado e a vista "departamento/cadastro".
     */
    @GetMapping("/departamentos/editar/{id}")
    public ModelAndView preEditar(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("departamento", service.listarPorId(id));
        mv.setViewName("departamento/cadastro");
        return mv;
    }

    /**
     * Método para editar um departamento existente.
     * 
     * @param departamento Objeto Departamento a ser editado.
     * @param redirectAttributes Atributos para mensagens de redirecionamento.
     * @return ModelAndView objeto que redireciona para a lista de departamentos.
     */
    @PostMapping("/departamentos/editar")
    public ModelAndView editar(Departamento departamento, RedirectAttributes redirectAttributes) {
        try {
            service.editar(departamento);
            redirectAttributes.addFlashAttribute("successMessage", "Departamento atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao atualizar departamento: " + e.getMessage());
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/departamentoslistar");
        return mv;
    }

    /**
     * Método para buscar a página de cadastro de departamentos a partir do arquivo Funcionario/listar.html.
     * 
     * @param departamento Objeto Departamento que será vinculado ao formulário.
     * @return ModelAndView objeto que contém a vista "departamento/cadastro".
     */
    @RequestMapping("/funcionarios/buscar/departamentoscadastrar")
    public ModelAndView BuscarPor___(Departamento departamento) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("departamento/cadastro");
        return mv;
    }

    /**
     * Método para buscar a lista de departamentos a partir do arquivo Funcionario/listar.html.
     * 
     * @return ModelAndView objeto que contém a lista de departamentos e a vista "departamento/lista".
     */
    @RequestMapping("/funcionarios/buscar/departamentoslistar")
    public ModelAndView BuscarPor_____() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("departamentos", service.listarTodos());
        mv.setViewName("departamento/lista");
        return mv;
    }

}
