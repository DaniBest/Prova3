package utfpr.ct.dainf.grader;

import java.util.Comparator;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class CredorComparatorTest implements Comparator<LancamentoTest> {
    
    @Override
    public int compare(LancamentoTest o1, LancamentoTest o2) {
        return o1.getData().compareTo(o2.getData());
    }
    
}
