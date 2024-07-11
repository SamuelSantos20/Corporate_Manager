package projeto.curso.com.br.web.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Funcionario;
import domain.UF;
import service.CargoService;
import service.FuncionarioService;

@Controller
public class Funcionario_Controller {

    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    CargoService cargoService;

    /**
     * Exibe a página de cadastro de funcionários.
     * 
     * @return ModelAndView objeto que contém a vista "funcionario/cadastro".
     */
    @GetMapping("/funcionarioscadastrar")
    public ModelAndView cadastrar() {
        ModelAndView md = new ModelAndView("funcionario/cadastro");
        md.addObject("funcionario", new Funcionario());
        md.addObject("cargos", cargoService.Listartudo());
        md.addObject("ufs", getlistarUF());
        return md;
    }

    /**
     * Exibe a lista de todos os funcionários cadastrados.
     * 
     * @return ModelAndView objeto que contém a lista de funcionários e a vista "funcionario/lista".
     */
    @GetMapping("/funcionarioslistar")
    public ModelAndView listar() {
        ModelAndView md = new ModelAndView("funcionario/lista");
        md.addObject("funcionarios", funcionarioService.ListarTodos());
        md.addObject("cargos", cargoService.Listartudo());
        return md;
    }

    /**
     * Salva um novo funcionário.
     * 
     * @param funcionario Objeto Funcionario a ser salvo.
     * @return ModelAndView objeto que redireciona para a página de cadastro de funcionários.
     */
    @PostMapping("/funcionarios/salvar")
    public ModelAndView salvar(Funcionario funcionario) {
        ModelAndView md = new ModelAndView("funcionario/cadastro");
        try {
            funcionarioService.Salvar(funcionario);
            md.addObject("successMessage", "Funcionário salvo com sucesso!");
        } catch (Exception e) {
            md.addObject("errorMessage", "Erro ao salvar funcionário: " + e.getMessage());
        }
        md.addObject("funcionario", new Funcionario());
        md.addObject("cargos", cargoService.Listartudo());
        md.addObject("ufs", getlistarUF());
        return md;
    }

    /**
     * Prepara a edição de um funcionário existente.
     * 
     * @param id ID do funcionário a ser editado.
     * @return ModelAndView objeto que contém o funcionário a ser editado e a vista "funcionario/cadastro".
     */
    @GetMapping("/funcionarios/editar/{id}")
    public ModelAndView PreEditar(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("funcionario/cadastro");
        mv.addObject("funcionario", funcionarioService.ListarID(id));
        mv.addObject("cargos", cargoService.Listartudo());
        mv.addObject("ufs", getlistarUF());
        return mv;
    }

    /**
     * Edita um funcionário existente.
     * 
     * @param funcionario Objeto Funcionario a ser editado.
     * @return ModelAndView objeto que redireciona para a página de cadastro de funcionários.
     */
    @PostMapping("/funcionarios/editar")
    public ModelAndView Editar(Funcionario funcionario) {
       ModelAndView mv = new ModelAndView();
       funcionarioService.Edit(funcionario);
       mv.setViewName("funcionario/cadastro");
       mv.addObject("funcionario", funcionario);
       mv.addObject("cargos", cargoService.Listartudo());
       mv.addObject("ufs", getlistarUF());
       return mv;
    }

    /**
     * Exclui um funcionário.
     * 
     * @param id ID do funcionário a ser excluído.
     * @return ModelAndView objeto que redireciona para a lista de funcionários.
     */
    @PostMapping("/funcionario/excluir")
    public ModelAndView Excluir(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView("funcionario/lista");
        funcionarioService.Excluir(id);
        mv.addObject("funcionarios", funcionarioService.ListarTodos());
        return mv;
    }

    /**
     * Exibe a página de cadastro de funcionários.
     * 
     * @return ModelAndView objeto que contém a vista "funcionario/cadastro".
     */
    @GetMapping("/funcionarios/cadastro")
    public ModelAndView showCadastroForm() {
        ModelAndView model = new ModelAndView("funcionario/cadastro");
        model.addObject("funcionario", new Funcionario());
        model.addObject("cargos", cargoService.Listartudo());
        model.addObject("ufs", getlistarUF());
        return model;
    }

    /**
     * Busca funcionários por nome.
     * 
     * @param nome Nome do funcionário a ser buscado.
     * @return ModelAndView objeto que contém a lista de funcionários encontrados e a vista "funcionario/lista".
     */
    @GetMapping("/funcionarios/buscar/nome")
    public ModelAndView BuscarPorNome(@RequestParam("nome") String nome) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("funcionarios", funcionarioService.buscarPorNome(nome));
        mv.addObject("cargos", cargoService.Listartudo());
        mv.setViewName("funcionario/lista.html");
        return mv;
    }

    /**
     * Busca todos os funcionários.
     * 
     * @return ModelAndView objeto que contém a lista de todos os funcionários e a vista "funcionario/lista".
     */
    @GetMapping("/funcionarios/buscar/funcionarioslistar")
    public ModelAndView ConexaoBuscarPorNome() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("funcionarios", funcionarioService.ListarTodos());
        mv.addObject("cargos", cargoService.Listartudo());
        mv.setViewName("funcionario/lista.html");
        return mv;
    }

    /**
     * Busca funcionários por cargo.
     * 
     * @param id ID do cargo a ser buscado.
     * @return ModelAndView objeto que contém a lista de funcionários encontrados e a vista "funcionario/lista".
     */
    @GetMapping("/funcionarios/buscar/cargo")
    public ModelAndView BuscarPorCargo(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("funcionarios", funcionarioService.BuscarPorCargo(id));
        mv.addObject("cargos", cargoService.Listartudo());
        mv.setViewName("funcionario/lista.html");
        return mv ;
    }

    /**
     * Prepara a página de cadastro de funcionários.
     * 
     * @return ModelAndView objeto que contém a vista "funcionario/cadastro".
     */
    @GetMapping("/funcionarios/buscar/funcionarioscadastrar")
    public ModelAndView ConexaoBuscarPorCargo() {
        ModelAndView md = new ModelAndView("funcionario/cadastro");
        md.addObject("funcionario", new Funcionario());
        md.addObject("cargos", cargoService.Listartudo());
        md.addObject("ufs", getlistarUF());
        return md;
    }

    /**
     * Busca funcionários por datas de entrada e saída.
     * 
     * @param entrada Data de entrada do funcionário.
     * @param saida Data de saída do funcionário.
     * @return ModelAndView objeto que contém a lista de funcionários encontrados e a vista "funcionario/lista".
     */
    @GetMapping("/funcionarios/buscar/data")
    public ModelAndView getPorDatas(@RequestParam(name = "entrada", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
                                    @RequestParam(name = "saida", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("cargos", cargoService.Listartudo());
        mv.addObject("funcionarios", funcionarioService.buscarPorDatas(entrada, saida));
        mv.setViewName("funcionario/lista.html");
        return mv;
    }

    /**
     * Método utilitário para obter a lista de estados (UF).
     * 
     * @return Array de UF contendo todos os estados brasileiros.
     */
    public UF[] getlistarUF() {
        return UF.values();
    }
}
