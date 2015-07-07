package utfpr.ct.dainf.if62c.avaliacao;

import java.util.Comparator;

/**
 * IF62C Fundamentos de Programação 2 Avaliação parcial.
 *
 * @author Daniel
 */
public class LancamentoComparator implements Comparator<Lancamento> {

    @Override
    public int compare(Lancamento l1, Lancamento l2) {
        if (l1.getConta() != l2.getConta()) {
            return l1.getConta().compareTo(l2.getConta());
        } else {
            return l1.getData().compareTo(l2.getData());
        }
    }
}
