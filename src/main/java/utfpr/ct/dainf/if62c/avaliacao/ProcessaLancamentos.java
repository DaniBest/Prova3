package utfpr.ct.dainf.if62c.avaliacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * IF62C Fundamentos de Programação 2 Avaliação parcial.
 *
 * @author Daniel
 */
public class ProcessaLancamentos {

    private BufferedReader reader;

    public ProcessaLancamentos(File arquivo) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(arquivo));
        throw new UnsupportedOperationException("Não implementado");
    }

    public ProcessaLancamentos(String path) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(new File(path)));
        throw new UnsupportedOperationException("Não implementado");
    }

    private String getNextLine() throws IOException {
        String linha;
        linha = reader.readLine();
        /*if (linha == null) {
            throw new UnsupportedOperationException("Não implementado");
        }*/
        return linha;
    }

    private Lancamento processaLinha(String linha) {
        //      if(linha.length()==86) {
        GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(linha.substring(7, 11)), Integer.parseInt(linha.substring(11, 13)), Integer.parseInt(linha.substring(13, 15)));
        Lancamento lancamento = new Lancamento(Integer.parseInt(linha.substring(1, 7)), gc.getTime(), linha.substring(15, 75), Double.parseDouble(linha.substring(75)));
        return lancamento;
 //       }else
        //       throw new UnsupportedOperationException("Não implementado");
    }

    private Lancamento getNextLancamento() throws IOException {
        String linha = getNextLine();
        Lancamento lancamento = processaLinha(linha);
        return lancamento;
//        throw new UnsupportedOperationException("Não implementado");
    }

    public List<Lancamento> getLancamentos() throws IOException {
        ArrayList<Lancamento> lancamentos = new ArrayList<>();
        String linha = getNextLine();
        while (linha != null) {
            lancamentos.add(getNextLancamento());
            linha = getNextLine();
        }
        reader.close();
        lancamentos.sort(new LancamentoComparator());
        return lancamentos;
//        throw new UnsupportedOperationException("Não implementado");
    }

}
