package service;

import java.util.List;

import org.springframework.stereotype.Service;

import domain.Departamento;

@Service
public interface DepartamentoService {
    void salvar(Departamento departamento);
    void editar(Departamento departamento);
    void excluir(Long id);
    Departamento listarPorId(Long id);
    List<Departamento> listarTodos();
	boolean departamentoTemCargos(Long id);
}
