package utfpr.ct.dainf.grader;

import java.text.SimpleDateFormat;
import java.util.Date;
import utfpr.ct.dainf.if62c.avaliacao.Lancamento;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class LancamentoTest {
    private Integer conta;
    private Date data;
    private String descricao;
    private Double valor;
    
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public LancamentoTest(Integer conta, Date data, String descricao, Double valor) {
        this.conta = conta;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.format("%06d %s %-60s %20f", conta, dateFormat.format(data),
                descricao, valor);
    }    

    public boolean equals(Lancamento lanc) {
        return conta.equals(lanc.getConta())
                && descricao.equals(lanc.getDescricao())
                && valor.equals(lanc.getValor())
                && data.equals(lanc.getData());
    }

    public boolean equalsLenient(Lancamento lanc) {
        return conta.equals(lanc.getConta())
                && descricao.startsWith(lanc.getDescricao())
                && valor.equals(lanc.getValor())
                && data.equals(lanc.getData());
    }

}
