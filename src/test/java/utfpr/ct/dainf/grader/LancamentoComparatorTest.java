package utfpr.ct.dainf.grader;

import utfpr.ct.dainf.if62c.avaliacao.*;
import java.util.Comparator;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class LancamentoComparatorTest implements Comparator<LancamentoTest> {
    
    @Override
    public int compare(LancamentoTest o1, LancamentoTest o2) {
        int comp = o1.getConta().compareTo(o2.getConta());
        if (comp == 0)
            comp = o1.getData().compareTo(o2.getData());
        return comp;
    }
    
}
