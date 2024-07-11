package domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import jakarta.persistence.*;

// A anotação @Entity indica que esta classe é uma entidade JPA.
@SuppressWarnings("serial")
@Entity
// @Table especifica a tabela que esta entidade será mapeada.
@Table(name = "FUNCIONARIOS")
public class Funcionario extends AbstractEntity<Long> {

    // @Column indica que este campo será mapeado para uma coluna na tabela.
    // nullable = false significa que esta coluna não pode ser nula.
    // unique = true significa que o valor desta coluna deve ser único na tabela.
    @Column(nullable = false, unique = true)
    private String nome;

    // columnDefinition especifica a definição da coluna no banco de dados.
    @NumberFormat(style = Style.CURRENCY , pattern = "# , ##0.00")
    @Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
    private BigDecimal salario;

    // columnDefinition especifica a definição da coluna no banco de dados.
    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "data_entrada", nullable = false, columnDefinition = "DATE")
    private LocalDate dataEntrada;

    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "data_saida", columnDefinition = "DATE")
    private LocalDate dataSaida;

    // @OneToOne indica um relacionamento um-para-um com a entidade Endereco.
    // cascade = CascadeType.ALL significa que todas as operações (persistir, mesclar, remover) 
    // realizadas em Funcionario também serão realizadas em Endereco.
    // @JoinColumn especifica a coluna de junção que mapeia este relacionamento.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id_fk")
    private Endereco endereco;

    // @ManyToOne indica um relacionamento muitos-para-um com a entidade Cargo.
    // @JoinColumn especifica a coluna de junção que mapeia este relacionamento.
    @ManyToOne
    @JoinColumn(name = "cargo_id_fk")
    private Cargo cargo;

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
