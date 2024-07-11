package domain;

import java.util.List;
import jakarta.persistence.*;

// A anotação @Entity indica que esta classe é uma entidade JPA.
@SuppressWarnings("serial")
@Entity
// @Table especifica a tabela que esta entidade será mapeada.
@Table(name = "CARGO")
public class Cargo extends AbstractEntity<Long> {

    // @Column indica que este campo será mapeado para uma coluna na tabela.
    // nullable = false significa que esta coluna não pode ser nula.
    // unique = true significa que o valor desta coluna deve ser único na tabela.
    // length = 200 define o tamanho máximo da coluna.
    @Column(name = "nome", nullable = false, unique = true, length = 200)
    private String nome;

    // @ManyToOne indica um relacionamento muitos-para-um com a entidade Departamento.
    // @JoinColumn especifica a coluna de junção que mapeia este relacionamento.
    @ManyToOne
    @JoinColumn(name = "id_departamento_fk")
    private Departamento departamento;

    // @OneToMany indica um relacionamento um-para-muitos com a entidade Funcionario.
    // mappedBy = "cargo" indica que o relacionamento é bidirecional e
    // é mapeado pelo atributo "cargo" na entidade Funcionario.
    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> funcionarios;

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
