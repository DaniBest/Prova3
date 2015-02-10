package utfpr.ct.dainf.grader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utfpr.ct.dainf.if62c.avaliacao.Lancamento;
import utfpr.ct.dainf.if62c.avaliacao.LancamentoComparator;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class ProcessaLancamentosTest {
    private BufferedReader reader;

    public ProcessaLancamentosTest(File arquivo) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(arquivo));
    }

    public ProcessaLancamentosTest(String path) throws FileNotFoundException {
        this(new File(path));
    }
    
    public String getNextLine() throws IOException {
        return reader.readLine();
    }
    
    public LancamentoTest processaLinha(String linha) {
        Integer conta = Integer.valueOf(linha.substring(0, 6));
        GregorianCalendar gc = new GregorianCalendar(
                Integer.parseInt(linha.substring(6, 10)),
                Integer.parseInt(linha.substring(10, 12))-1,
                Integer.parseInt(linha.substring(12, 14)));
        Date data = gc.getTime();
        String descricao = linha.substring(14, 74).trim();
        Double valor = Long.valueOf(linha.substring(74, 86)) / 100.0;
        return new LancamentoTest(conta, data, descricao, valor);
    }
    
    public LancamentoTest getNextLancamento() throws IOException {
        String linha = getNextLine();
        return linha == null ? null : processaLinha(linha);
    }
    
    public List<LancamentoTest> getLancamentos() throws IOException {
        List<LancamentoTest> lancamentos = new ArrayList<>();
        LancamentoTest c;
        try {
            while ((c = getNextLancamento()) != null) {
                lancamentos.add(c);
            }
        } finally {
            reader.close();
        }
        return lancamentos;
    }
    
    public List<LancamentoTest> getUnorderedLancamentosList() throws IOException {
        List<LancamentoTest> lanctos = new ArrayList<>();
        LancamentoTest c;
        try {
            while ((c = getNextLancamento()) != null) {
                lanctos.add(c);
            }
        } finally {
            reader.close();
        }
        return lanctos;
    }
    
    public List<LancamentoTest> getOrderedLancamentosList() throws IOException {
        List<LancamentoTest> lanctos = new ArrayList<>();
        LancamentoTest c;
        try {
            while ((c = getNextLancamento()) != null) {
                lanctos.add(c);
            }
        } finally {
            reader.close();
        }
        Collections.sort(lanctos, new LancamentoComparatorTest());
        return lanctos;
    }
    
    public static boolean isSameLancamentosList(List<LancamentoTest> testList, List<Lancamento> lancList) {
        boolean same = testList.size() == lancList.size();
        int i = 0;
        while (same && i < testList.size()) {
            same = testList.get(i).equalsLenient(lancList.get(i));
            i++;
        }
        return same;
    }
    
}
