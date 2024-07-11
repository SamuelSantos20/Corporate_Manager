package dao;

import org.springframework.stereotype.Repository;

import domain.Departamento;

@Repository
public class DepartamentoDaoImpl extends AbstractDao<Departamento, Long> implements DepartamentoDao {

    public DepartamentoDaoImpl() {
        super();
    }
}
