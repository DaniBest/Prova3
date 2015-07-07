
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.in;
import static java.lang.System.out;
import java.util.List;
import utfpr.ct.dainf.if62c.avaliacao.Lancamento;
import java.util.Scanner;

/**
 * IF62C Fundamentos de Programação 2 Avaliação parcial.
 *
 * @author Daniel
 */
public class Avaliacao3 {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        Scanner scanner = new Scanner(in);
        out.print("Digite o caminho do arquivo de lançamentos: ");

        
    }

    public static void exibeLancamentosConta(List<Lancamento> lancamentos, Integer conta) {

        if (lancamentos.indexOf(conta) != -1) {
            int essaConta = lancamentos.indexOf(conta);
            while (lancamentos.get(essaConta).getConta() == conta) {
                out.println();
                essaConta++;
            }
        }
    }

}
